/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.retiros;

import banco.data.ClienteDao;
import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Movimiento;
import banco.logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controllerCajeroRetiros", urlPatterns = {"/presentation/cajero/retiros/show", "/cajero/retiros/buscarCliente", "/cajero/retiros/buscarCuenta", "/cajero/retiros/ingresar", "/cajero/retiros/seleccionar", "/cajero/retiros/confirmar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cajero.retiros.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/retiros/show":
                viewUrl = this.show(request);
                break;
            case "/cajero/retiros/buscarCliente":
                viewUrl = this.buscarCliente(request);
                break;
            case "/cajero/retiros/buscarCuenta":
                viewUrl = this.buscarCuenta(request);
                break;
            case "/cajero/retiros/ingresar":
                viewUrl = this.ingresar(request);
                break;
            case "/cajero/retiros/confirmar":
                viewUrl = this.confirmar(request);
                break;
            case "/cajero/retiros/seleccionar":
                viewUrl = this.seleccionar(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String buscarCliente(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        banco.presentacion.cajero.retiros.Model model = (banco.presentacion.cajero.retiros.Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("clienteABuscar"));

        try {

            Cliente clienteBuscar = banco.data.ClienteDao.buscarPorCliente(ID);
            request.setAttribute("clienteBuscar", String.valueOf(clienteBuscar.getIdCliente()));
            model.setClienteBuscar(clienteBuscar);
            model.setCuentas(banco.data.CuentaDao.getCuentasCliente(clienteBuscar.getUsuarioIdUsuario().getIdUsuario()));

            request.setAttribute("model", model);

            return ("/presentation/cajero/retiros/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/retiros/View.jsp");
        }

    }

    public String buscarCuenta(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        banco.presentacion.cajero.retiros.Model model = (banco.presentacion.cajero.retiros.Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("cuentaABuscar"));

        try {

            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
            model.setSeleccionada(seleccionada);

            return ("/presentation/cajero/retiros/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/retiros/View.jsp");
        }

    }

    public String ingresar(HttpServletRequest request) {

        String motivo = request.getParameter("motivo");
        String nombreDepositante = request.getParameter("nombreDepositante");
        String monto = request.getParameter("monto");

        banco.presentacion.cajero.retiros.Model model = (banco.presentacion.cajero.retiros.Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("cuentaABuscar"));

        try {
            request.setAttribute("motivo", motivo);
            request.setAttribute("nombreDepositante", nombreDepositante);
            request.setAttribute("monto", monto);

            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
            model.setSeleccionada(seleccionada);

            request.setAttribute("mensaje", "Se han ingresado los datos correctamente");

            return ("/presentation/cajero/retiros/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/retiros/View.jsp");
        }

    }

    public String confirmar(HttpServletRequest request) {

        String monto = (String) request.getParameter("montoConf");
        Integer ID = Integer.parseInt(request.getParameter("cuentaConf"));
        java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {

            Movimiento movimiento = new Movimiento();
            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
            if (Integer.valueOf(monto) < seleccionada.getSaldoFinal()) {
                movimiento.setCuenta(seleccionada);
                movimiento.setAplicado((short) 1);
                movimiento.setMonto(Integer.valueOf(monto));

                movimiento.setFecha(fecha);

                seleccionada.setSaldoFinal(seleccionada.getSaldoFinal() - Integer.valueOf(monto));

                banco.data.CuentaDao.insertarMovimiento(movimiento, seleccionada);
                banco.data.CuentaDao.updateSaldo(seleccionada);

                request.setAttribute("mensaje", "El depósito ha sido éxitoso");
            }

            return ("/presentation/cajero/retiros/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/retiros/View.jsp");
        }

    }

    public String seleccionar(HttpServletRequest request) {

        banco.presentacion.cajero.retiros.Model model = (banco.presentacion.cajero.retiros.Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("idCuenta"));

        try {
            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
            model.setSeleccionada(seleccionada);

            return ("/presentation/cajero/retiros/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/retiros/View.jsp");
        }

    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());
        return "/presentation/cajero/retiros/View.jsp";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

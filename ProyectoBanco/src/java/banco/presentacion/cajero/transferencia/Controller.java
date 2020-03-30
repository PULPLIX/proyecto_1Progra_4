/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.transferencia;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Transferencia;
import banco.logica.Usuario;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author david
 */
@WebServlet(name = "controllerTransferenciaCajero", urlPatterns = {"/presentation/cajero/transferencia/show", "/transferir/cajero/confirmar", "/cajero/transferencia/buscar", "/cajero/transferencia/seleccionar"})

public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cajero.transferencia.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/transferencia/show":
                viewUrl = this.show(request);
                break;
            case "/transferir/confirmar":
                viewUrl = this.transferir(request);
                break;
            case "/cajero/transferencia/buscar":
                viewUrl = this.buscar(request);
                break;
            case "/cajero/transferencia/seleccionar":
                viewUrl = this.seleccionar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String transferir(HttpServletRequest request) {
        banco.presentacion.cliente.transferencia.Model model = (banco.presentacion.cliente.transferencia.Model) request.getAttribute("model");

        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        try {
            Transferencia t = new Transferencia();
            String numCuentaO = (String) request.getParameter("cuentaOrigenConf");
            String numCuentaD = (String) request.getParameter("cuentaDestinoConf");
            String monto = (String) request.getParameter("montoConf");
            if (!numCuentaO.equals("vacío") && !numCuentaD.equals("vacío") && !monto.equals("vacío")) {
                t.setCuenta(banco.data.CuentaDao.getCuenta(cliente.getUsuarioIdUsuario().getIdUsuario(), numCuentaO).get(0));

                t.setCuenta_Destino(banco.data.CuentaDao.getCuenta(cliente.getUsuarioIdUsuario().getIdUsuario(), numCuentaD).get(0));

                t.setAplicado(Short.parseShort("1"));

                t.setMonto(monto);
                Calendar calendar = java.util.Calendar.getInstance();
                t.setFecha(calendar.getTime());
                if (banco.data.movimientosDao.registrarTransferencia(t)) {
                    double montoFinal = t.getCuenta().getSaldoFinal() - Double.parseDouble(monto);
                    double montoTransferencia = t.getCuenta().getLimiteTransferenciaDiaria() - Double.parseDouble(monto);
                    t.getCuenta().setSaldoFinal(montoFinal);
                    t.getCuenta().setLimiteTransferenciaDiaria(montoFinal);

                    banco.data.CuentaDao.updateSaldo(t.getCuenta());
                }
            }
            return "/presentation/cliente/transferencia/View.jsp";
        } catch (Exception ex) {
            return "/errorCajeroTransferencia";
        }
    }

    public void buscarCliente(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {

            Cliente clienteBuscar = banco.data.ClienteDao.buscarPorCliente(Integer.parseInt(request.getParameter("clienteABuscar")));
            request.setAttribute("clienteBuscar", String.valueOf(clienteBuscar.getIdCliente()));
            model.setClienteBuscar(clienteBuscar);
            model.setCuentas(banco.data.CuentaDao.getCuentasCliente(clienteBuscar.getUsuarioIdUsuario().getIdUsuario()));

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void buscarCuenta(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {

            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaABuscar")));
            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
            model.setSeleccionada(seleccionada);

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void buscarClienteDestino(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {
            Cliente clienteBuscarDestino = banco.data.ClienteDao.buscarPorCliente(Integer.parseInt(request.getParameter("clienteABuscarDestino")));
            request.setAttribute("clienteBuscarDestino", String.valueOf(clienteBuscarDestino.getIdCliente()));
            model.setClienteBuscarDestino(clienteBuscarDestino);
            model.setCuentasDestino(banco.data.CuentaDao.getCuentasCliente(clienteBuscarDestino.getUsuarioIdUsuario().getIdUsuario()));

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void buscarCuentaDestino(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {
            Cuenta seleccionadaDestino = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaABuscarDestino")));
            request.setAttribute("cuentaSeleccionadaDestino", seleccionadaDestino.getNumCuenta().toString());
            model.setSeleccionadaDestino(seleccionadaDestino);

        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public String buscar(HttpServletRequest request) {
        banco.presentacion.cajero.transferencia.Model model = (banco.presentacion.cajero.transferencia.Model) request.getAttribute("model");

        this.buscarCliente(request, model);
            this.buscarClienteDestino(request, model);
            this.buscarCuenta(request, model);
            this.buscarCuentaDestino(request, model);
            model.setMonto(Integer.parseInt(String.valueOf(request.getParameter("monto"))));
            request.setAttribute("model", model);
            
        return ("/presentation/cajero/transferencia/View.jsp");

    }

    public String seleccionar(HttpServletRequest request) {

        banco.presentacion.cajero.depositos.Model model = (banco.presentacion.cajero.depositos.Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("idCuenta"));

        try {
            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
            model.setSeleccionada(seleccionada);

            return ("/presentation/cajero/depositos/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/depositos/View.jsp");
        }

    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());
        return "/presentation/cajero/transferencia/View.jsp";
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

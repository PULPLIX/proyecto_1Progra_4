/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.transferencia;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Transferencia;
import banco.logica.Usuario;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ESCINF
 */
@WebServlet(name = "controllerTransferencia", urlPatterns = {"/presentation/login/transferencia/show", "/transferir/busca/cuentas", "/transferir/confirmar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/login/transferencia/show":
                viewUrl = this.show(request);
                break;
            case "/transferir/busca/cuentas":
                viewUrl = this.buscaCuentas(request);
                break;
            case "/transferir/confirmar":
                viewUrl = this.transferir(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String transferir(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");

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
            return "/presentation/cliente/transferencia/View.jsp";
        }
    }

    public String buscaCuentas(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
       

        try {
             Cliente cliente = banco.data.ClienteDao.find(usuario.getIdUsuario());
             session.setAttribute("cliente", cliente);
             
            String numCuentaO = (String) request.getParameter("cuentaOrigen");
            String numCuentaD = (String) request.getParameter("cuentaDestino");
            String monto = (String) request.getParameter("monto");

            if (!(numCuentaO).equals(numCuentaD)) {
                model.setSeleccionado(banco.data.CuentaDao.getCuenta(cliente.getUsuarioIdUsuario().getIdUsuario(), numCuentaO).get(0));
                request.setAttribute("numCuentaO", numCuentaO);
                model.setaTransferir(banco.data.CuentaDao.getCuenta(cliente.getUsuarioIdUsuario().getIdUsuario(), numCuentaD).get(0));
                request.setAttribute("numCuentaD", numCuentaD);
                if (Double.parseDouble(monto) < model.getSeleccionado().getLimiteTransferenciaDiaria()) {
                    request.setAttribute("monto", monto);
                }

            }

            return "/presentation/cliente/transferencia/View.jsp";
        } catch (Exception ex) {
            return "/presentation/cliente/transferencia/View.jsp";
        }
    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());
        return "/presentation/cliente/transferencia/View.jsp";
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
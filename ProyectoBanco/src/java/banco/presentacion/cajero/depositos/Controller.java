/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.depositos;

import banco.data.ClienteDao;
import banco.logica.Cliente;
import banco.logica.Usuario;
import banco.logica.Cuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "controllerCajeroDepositos", urlPatterns = {"/presentation/cajero/depositos/show", "/cajero/depositos/buscarCliente", "/cajero/depositos/buscarCuenta", "/cajero/depositos/ingresar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/depositos/show":
                viewUrl = this.show(request);
                break;
            case "/cajero/depositos/buscarCliente":
                viewUrl = this.buscarCliente(request);
                break;
            case "/cajero/depositos/buscarCuenta":
                viewUrl = this.buscarCuenta(request);
                break;

            case "/cajero/depositos/ingresar":
                viewUrl = this.ingresar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String buscarCliente(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Model model = (Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("clienteABuscar"));

        try {

            Cliente clienteBuscar = banco.data.ClienteDao.findID(ID);
            request.setAttribute("clienteBuscar", clienteBuscar.getIdCliente().toString());
            model.setClienteBuscar(clienteBuscar);
            model.setCuentas(banco.data.CuentaDao.getCuentasCliente(clienteBuscar.getUsuarioIdUsuario().getIdUsuario()));

            return ("/presentation/cajero/depositos/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/infoPersonal/View.jsp");
        }

    }

    public String buscarCuenta(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Model model = (Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("cuentaABuscar"));

        try {

            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
            model.setSeleccionada(seleccionada);

            return ("/presentation/cajero/depositos/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/infoPersonal/View.jsp");
        }

    }

    public String ingresar(HttpServletRequest request) {

//        HttpSession session = request.getSession(true);
//        Model model = (Model) request.getAttribute("model");
//        Integer ID = Integer.parseInt(request.getParameter("cuentaABuscar"));

        try {
//
//            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
//            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
//            model.setSeleccionada(seleccionada);

            return ("/presentation/cajero/depositos/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/infoPersonal/View.jsp");
        }

    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());
        return "/presentation/cajero/depositos/View.jsp";
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

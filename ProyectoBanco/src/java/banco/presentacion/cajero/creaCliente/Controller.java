/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.creaCliente;

import banco.logica.Cliente;
import banco.logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/presentation/login/crearCliente", "/registrar/cliente"})

public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cajero.infoPersonal.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/login/crearCliente":
                viewUrl = this.show(request);
                break;
            case "/registrar/cliente":
                viewUrl = this.registrar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String registrar(HttpServletRequest request) {
        if(!validar(request)){
        return registrarAction(request);
        }
        return "/presentation/login/crearCliente";
    }

    public String registrarAction(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario((String) request.getParameter("usuario"));
        Cliente cliente = new Cliente();
        cliente.setNombre((String) request.getParameter("nombre"));
        cliente.setApellidos((String) request.getParameter("apellidos"));
        cliente.setTelefono((String) request.getParameter("telefono"));
        cliente.setIdCliente(Integer.parseInt(request.getParameter("id")));
        cliente.setUsuarioIdUsuario(usuario);

        try {
            banco.data.UsuarioDao1.registrar(usuario);
            banco.data.ClienteDao.registrar(cliente);
        } catch (Exception ex) {
            return "";
        }
        return "/presentation/cajero/crearCliente/View.jsp";
    }

    public String show(HttpServletRequest request) {

        return "/presentation/cajero/crearCliente/View.jsp";
    }

    public boolean validar(HttpServletRequest request) {
        boolean error = false;

        if (request.getParameter("usuario").isEmpty()) {
            request.setAttribute("errorUsuario", "errorTxt");
            error = true;
        }
        if (request.getParameter("nombre").isEmpty()) {
            request.setAttribute("errorNombre", "errorTxt");
            error = true;
        }
        if (request.getParameter("apellidos").isEmpty()) {
            request.setAttribute("errorApellidos", "errorTxt");
            error = true;
        }
        if (request.getParameter("id").isEmpty()) {
            request.setAttribute("errorCedula", "errorTxt");
            error = true;
        }
        if (request.getParameter("telefono").isEmpty()) {
            request.setAttribute("errorTelefono", "errorTxt");
            error = true;
        }
        return error;
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

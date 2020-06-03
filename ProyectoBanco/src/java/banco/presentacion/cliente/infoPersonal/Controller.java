/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.infoPersonal;

import banco.data.ClienteDao;
import banco.logica.Cliente;
import banco.logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controllerInfoPersonal", urlPatterns = {"/presentation/login/infoPersonal/show", "/infoPersonal/actualizar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cliente.infoPersonal.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/login/infoPersonal/show":
                viewUrl = this.show(request);
                break;
            case "/infoPersonal/actualizar":
                viewUrl = this.actualizar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String actualizar(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        //Model model= (Model) request.getAttribute("model");

        try {
            String nombreE = (String) request.getParameter("nombreE");
            String apellidosE = (String) request.getParameter("apellidosE");
            String telefonoE = (String) request.getParameter("telefonoE");

            if (ClienteDao.actualizar(nombreE, apellidosE, telefonoE, cliente.getUsuarioIdUsuario().getIdUsuario())) {
                cliente.setTelefono(telefonoE);
                cliente.setNombre(nombreE);
                cliente.setApellidos(apellidosE);
                session.setAttribute("cliente", cliente);
            }

            // Usuario real = banco.data.UsuarioDao.find(model.getCurrent().getIdUsuario(),model.getCurrent().getClaveAcceso());
            //session.setAttribute("usuario", real);
            return ("/presentation/login/infoPersonal/show");
        } catch (Exception ex) {
            return ("/presentation/cliente/infoPersonal/View.jsp");
        }

    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());
        request.setAttribute("clienteTelefono", cliente.getTelefono());
        return "/presentation/cliente/infoPersonal/View.jsp";
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

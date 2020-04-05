/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.infoPersonal;

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

/**
 *
 * @author Ivan
 */
@WebServlet(name = "controllerCajeroInfoPersonal", urlPatterns = {"/presentation/cajero/infoPersonal/show", "/cajero/infoPersonal/actualizar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cajero.infoPersonal.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/infoPersonal/show":
                viewUrl = this.show(request);
                break;
            case "/cajero/infoPersonal/actualizar":
                viewUrl = this.actualizar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public boolean validar(HttpServletRequest request) {
        boolean error = false;
        if (request.getParameter("nombreE").isEmpty()) {
            request.setAttribute("errorNombre", "El nombre no puede ser vacio");
            error = true;
        }
        if (request.getParameter("apellidosE").isEmpty()) {
            request.setAttribute("errorApellidos", "Los apellidos no puede ser vacio");
            error = true;
        }
        if (request.getParameter("telefonoE").isEmpty()) {
            request.setAttribute("errorTelefono", "El telefono no puede ser vacio");
            error = true;
        }
        String telefono = request.getParameter("telefonoE");
        for (int i = 0; i < telefono.length(); i++) {
            if (!(telefono.charAt(i) >= 47 && telefono.charAt(i) <= 57 || telefono.charAt(i) == '-' || telefono.charAt(i) == '(' || telefono.charAt(i) == ')' || telefono.charAt(i) == '+'|| telefono.charAt(i) == ' ')) {
                request.setAttribute("errorTelefonoInvalido", "El formato del telefono no es valido");
                error = true;

            }
        }
        return error;
    }

    public String actualizar(HttpServletRequest request) {

        if (!validar(request)) {
            HttpSession session = request.getSession(true);
            Cliente cliente = (Cliente) session.getAttribute("cliente");

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

                return ("/presentation/cajero/infoPersonal/show");
            } catch (Exception ex) {
                return ("/presentation/cajero/infoPersonal/View.jsp");
            }
        } else {
            return this.show(request);
        }
//        HttpSession session = request.getSession(true);
//        Cliente cliente = (Cliente) session.getAttribute("cliente");
//
//        try {
//            String nombreE = (String) request.getParameter("nombreE");
//            String apellidosE = (String) request.getParameter("apellidosE");
//            String telefonoE = (String) request.getParameter("telefonoE");
//
//            if (ClienteDao.actualizar(nombreE, apellidosE, telefonoE, cliente.getUsuarioIdUsuario().getIdUsuario())) {
//                cliente.setTelefono(telefonoE);
//                cliente.setNombre(nombreE);
//                cliente.setApellidos(apellidosE);
//                session.setAttribute("cliente", cliente);
//            }
//
//            return ("/presentation/cajero/infoPersonal/show");
//        } catch (Exception ex) {
//            return ("/presentation/cajero/infoPersonal/View.jsp");
//        }

    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Model model = (Model) request.getAttribute("model");

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = null;

        try {
            cliente = banco.data.ClienteDao.find(usuario.getIdUsuario());
            model.setCliente(cliente);

            request.setAttribute("clienteNombre", cliente.getNombre());
            request.setAttribute("clienteApellidos", cliente.getApellidos());
            request.setAttribute("clienteTelefono", cliente.getTelefono());

            request.setAttribute("model", model);
            session.setAttribute("cliente", cliente);

        } catch (Exception ex) {
            System.out.println(ex);
            cliente = null;
        }

        return "/presentation/cajero/infoPersonal/View.jsp";
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

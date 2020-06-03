/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.login;

import banco.data.Coneccion;
import banco.logica.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/show", "/presentation/login/login", "/login/logout"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/login/show":
                viewUrl = this.show(request);
                break;
            case "/presentation/login/login":
                viewUrl = this.login(request);
                break;
            case "/login/logout":
                viewUrl = this.logout(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    private String login(HttpServletRequest request) {
        try {
            if (!this.validar(request)) {
                this.updateModel(request);
                return this.loginAction(request);
            }
            return "/presentation/login/View.jsp";

        } catch (Exception e) {
            request.setAttribute("errorRegistrar", "* Clave o Usuario erroneo o inexistente.");
            return "/presentation/login/View.jsp";
        }
    }

    boolean validar(HttpServletRequest request) {
        boolean error = false;

        if (request.getParameter("cedulaFld").isEmpty()) {
            request.setAttribute("errorCedula", "errorTxt");
            error = true;
        }

        if (request.getParameter("claveFld").isEmpty()) {
            request.setAttribute("errorClave", "errorTxt");
            error = true;
        }
        return error;
    }

    void updateModel(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");

        model.getCurrent().setIdUsuario(request.getParameter("cedulaFld"));
        model.getCurrent().setClaveAcceso(request.getParameter("claveFld"));
    }

    public String loginAction(HttpServletRequest request) {

        Model model = (Model) request.getAttribute("model");
        HttpSession session = request.getSession(true);
        try {

            Usuario real = banco.data.UsuarioDao1.find(model.getCurrent().getIdUsuario(), model.getCurrent().getClaveAcceso());
            if (real == null) {
                throw new Exception();
            }
            session.setAttribute("usuario", real);
            String viewUrl = "";
            switch (real.getRol()) {
                case 1:
                    viewUrl = "/presentation/cliente/datos/show";
                    break;
                case 2:
                    viewUrl = "/presentation/cajero/infoPersonal/show";
                    break;
            }

            return viewUrl;
        } catch (Exception ex) {
            request.setAttribute("errorRegistrar", "* Clave o Usuario erroneo o inexistente.");
            return "/presentation/login/View.jsp";
        }
    }

    public String logout(HttpServletRequest request) {
        return this.logoutAction(request);
    }

    public String logoutAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.removeAttribute("cliente");
        session.invalidate();
        return "/presentation/Index/Index.jsp";
    }

    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }

    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        model.getCurrent().setIdUsuario("");
        model.getCurrent().setClaveAcceso("");

        request.setAttribute("mensaje", "BIENVENIDO AL HIMALAYA");
        return "/presentation/login/View.jsp";
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

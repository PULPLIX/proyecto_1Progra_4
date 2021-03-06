/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.movimientos;

import banco.data.ClienteDao;
import banco.logica.Cliente;
import banco.logica.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controllerMovimientos", urlPatterns = {"/presentation/login/movimientos/show", "/movimientos/actualizar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cliente.movimientos.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/login/movimientos/show":
                viewUrl = this.show(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }

    public String showAction(HttpServletRequest request) {
        banco.presentacion.cliente.movimientos.Model model = (banco.presentacion.cliente.movimientos.Model) request.getAttribute("model");

        HttpSession session = request.getSession(true);

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        
        try {
            cliente = banco.data.ClienteDao.find(cliente.getUsuarioIdUsuario().getIdUsuario());
            model.setCurrent(cliente);

            request.setAttribute("clienteNombre", cliente.getNombre());
            request.setAttribute("clienteApellidos", cliente.getApellidos());

            model.setCuentas(banco.data.CuentaDao.getCuenta(cliente.getUsuarioIdUsuario().getIdUsuario(),(String)request.getParameter("idCuenta")));

            for (int i = 0; i < model.getCuentas().size(); i++) {
                for (int j = 0; j < model.getCuentas().get(i).getMovimientoCollection().size(); j++) {
                    model.getMovimientos().add(model.getCuentas().get(i).getMovimientoCollection().get(j));
                }
            }
            for (int i = 0; i < model.getCuentas().size(); i++) {
                for (int j = 0; j < model.getCuentas().get(i).getTransferenciaCollection().size(); j++) {
                    model.getTransferencia().add(model.getCuentas().get(i).getTransferenciaCollection().get(j));
                }
            }
            request.setAttribute("model", model);
            session.setAttribute("cliente", cliente);
            return "/presentation/cliente/movimientos/View.jsp";
        } catch (Exception ex) {
            return "";
        }
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

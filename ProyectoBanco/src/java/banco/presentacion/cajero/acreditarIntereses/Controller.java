/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.acreditarIntereses;

import banco.data.ClienteDao;
import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Moneda;
import banco.logica.TipoCuenta;
import banco.logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controlleracreditarIntereses", urlPatterns = {"/presentation/cajero/acreditarIntereses/show", "/cajero/acreditarIntereses/actualizar", "/presentation/cajero/acreditarIntereses/acreditar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cajero.acreditarIntereses.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/acreditarIntereses/show":
                viewUrl = this.show(request);
                break;
            case "/cajero/acreditarIntereses/actualizar":
                viewUrl = this.actualizar(request);
                break;
            case "/presentation/cajero/acreditarIntereses/acreditar":
                viewUrl = this.acreditar(request);

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String actualizar(HttpServletRequest request) {

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

            return ("/presentation/cajero/acreditarIntereses/show");
        } catch (Exception ex) {
            return ("/presentation/cajero/acreditarIntereses/View.jsp");
        }

    }

    public String acreditar(HttpServletRequest request) {
        banco.presentacion.cajero.acreditarIntereses.Model model = (banco.presentacion.cajero.acreditarIntereses.Model) request.getAttribute("model");
        try {
            ArrayList<Cuenta> cuentas = banco.data.CuentaDao.listarTodo();
            model.setCuentas(cuentas);
            for (int i = 0; i < model.getCuentas().size(); i++) {
                Cuenta seleccionada = banco.data.CuentaDao.getCuenta(model.getCuentas().get(i).getNumCuenta());
                seleccionada.setSaldoFinal(model.getCuentas().get(i).getSaldoFinal() + ((model.getCuentas().get(i).getSaldoFinal()) * (model.getCuentas().get(i).getMonedaNombre().getTasaIntereses())));
                banco.data.CuentaDao.updateSaldo(seleccionada);
            }
            request.setAttribute("mensaje", "✅ Se han acreditado los intereses correctamente");
            return ("/presentation/cajero/acreditarIntereses/show");

        } catch (Exception ex) {
            request.setAttribute("error", "⚠ Hubo un error en acreditar todos los intereses");

            return ("/presentation/cajero/acreditarIntereses/show");
        }
    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        banco.presentacion.cajero.acreditarIntereses.Model model = (banco.presentacion.cajero.acreditarIntereses.Model) request.getAttribute("model");

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = null;

        try {

            ArrayList<Moneda> listaM = banco.data.MonedaDao.listar();
            model.setListaMonedas(listaM);
            cliente = banco.data.ClienteDao.find(usuario.getIdUsuario());
            model.setCliente(cliente);
            ArrayList<Cuenta> cuentas = banco.data.CuentaDao.listarTodo();
            model.setCuentas(cuentas);

            request.setAttribute("clienteNombre", cliente.getNombre());
            request.setAttribute("clienteApellidos", cliente.getApellidos());
            request.setAttribute("clienteTelefono", cliente.getTelefono());

            request.setAttribute("model", model);
            session.setAttribute("cliente", cliente);

        } catch (Exception ex) {
            System.out.println(ex);
            cliente = null;
        }

        return "/presentation/cajero/acreditarIntereses/View.jsp";
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

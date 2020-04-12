/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.cuentas;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Usuario;
import java.io.IOException;
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
@WebServlet(name = "controllerCuentasShow", urlPatterns = {"/presentation/cliente/datos/show", "/presentation/login/transferencia", "/presentation/login/infoPersonal", "/presentation/login/movimientos", "/presentation/cliente/cuentasFav", "/cliente/cuentas/favoritas", "/cuentas/favoritas/eliminar", "/favoritas/buscar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cliente/datos/show":
                viewUrl = this.show(request);
                break;
            case "/presentation/login/transferencia":
                viewUrl = this.transferencia(request);
                break;
            case "/presentation/login/infoPersonal":
                viewUrl = this.infoPersonal(request);
                break;
            case "/presentation/login/movimientos":
                viewUrl = this.movimientos(request);
                break;
            case "/presentation/cliente/cuentasFav":
                viewUrl = this.listadoCuentasFavoritas(request);
                break;
            case "/cliente/cuentas/favoritas":
                viewUrl = this.registrarFavorita(request);
                break;
            case "/cuentas/favoritas/eliminar":
                viewUrl = this.eliminarFavorita(request);
                break;
            case "/favoritas/buscar":
                viewUrl = this.buscarFavorita(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String buscarFavorita(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        try {
            if (!request.getParameter("buscaCuenta").isEmpty()) {
                String cuentaVincular = (String) request.getParameter("buscaCuenta");
                if (banco.data.CuentaDao.getCuentaUnica(cuentaVincular) != null) {
                    Cuenta cuenta = banco.data.CuentaDao.getCuentaUnica(cuentaVincular);
                    String propietario = cuenta.getClienteIdCliente().getNombre()+" "+cuenta.getClienteIdCliente().getApellidos();
                    request.setAttribute("nombre",propietario);
                    request.setAttribute("idCuenta", cuenta.getNumCuenta());
                } else {
                    request.setAttribute("noExiste", "errorTxt");
                }
            }
            return "/presentation/cliente/cuentasFav";
        } catch (Exception ex) {
            return "/presentation/cliente/cuentasFav";
        }

    }

    public String registrarFavorita(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        try {
            if (!request.getParameter("cuentaVincular").isEmpty()) {
                String cuentaVincular = (String) request.getParameter("cuentaVincular");
                if (banco.data.CuentaDao.getCuentaUnica(cuentaVincular) != null) {
                    banco.data.FavoritaDao.agregarFavorita(Integer.parseInt(cuentaVincular), cliente.getUsuarioIdUsuario().getIdUsuario());
                } else {
                    request.setAttribute("noExiste", "error");
                }
            }
            return "/presentation/cliente/cuentasFav";
        } catch (Exception ex) {
            return "errorVincular";
        }

    }

    public String eliminarFavorita(HttpServletRequest request) {
        try {
            String idCuenta = (String) request.getParameter("idCuenta");
            banco.data.FavoritaDao.eliminar(Integer.parseInt(idCuenta));
            return "/presentation/cliente/cuentasFav";

        } catch (NumberFormatException ex) {
            return "errorVincular";
        }
    }

    public String listadoCuentasFavoritas(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");

        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (request.getAttribute("noExiste") != null) {
            request.setAttribute("noExiste", "error");
        }
        try {
            if (cliente != null) {
                cliente = banco.data.ClienteDao.find(cliente.getUsuarioIdUsuario().getIdUsuario());
                session.setAttribute("cliente", cliente);
                model.setCurrent(cliente);
                request.setAttribute("model", model);
                return "/presentation/cliente/infoPersonal/favoritas/View.jsp";
            }
            return "errorVicularCliente";
        } catch (Exception ex) {
            System.out.print(ex);
            return "errorVicularCliente";
        }
    }

    public String transferencia(HttpServletRequest request) {

        return "/presentation/login/transferencia/show";
    }

    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }

    public String infoPersonal(HttpServletRequest request) {

        return "/presentation/login/infoPersonal/show";
    }

    public String movimientos(HttpServletRequest request) {

        return "/presentation/login/movimientos/show";
    }

    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");

        HttpSession session = request.getSession(true);

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = null;
        try {
            cliente = banco.data.ClienteDao.find(usuario.getIdUsuario());
            model.setCurrent(cliente);

            request.setAttribute("clienteNombre", cliente.getNombre());
            request.setAttribute("clienteApellidos", cliente.getApellidos());

        } catch (Exception ex) {
            System.out.println(ex);
            cliente = null;
        }
        try {
            model.setCuentas(banco.data.CuentaDao.getCuentasCliente(cliente.getUsuarioIdUsuario().getIdUsuario()));
            request.setAttribute("model", model);
            session.setAttribute("cliente", cliente);
            return "/presentation/cliente/datos/View.jsp";
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
        return "Controlador de la pagina principal del cliente";
    }// </editor-fold>

}

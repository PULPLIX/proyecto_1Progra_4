/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.creaCuenta;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Moneda;
import banco.logica.TipoCuenta;
import banco.logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
@WebServlet(urlPatterns = {"/presentation/cajero/crearCuenta", "/registrar/cuenta"})

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
        request.setAttribute("model", new banco.presentacion.cajero.creaCuenta.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/crearCuenta":
                viewUrl = this.show(request);
                break;
            case "/registrar/cuenta":
                viewUrl = this.registrar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String registrar(HttpServletRequest request) {

        Cliente cliente = new Cliente();
        
        try {
            cliente = banco.data.ClienteDao.buscarPorCliente(Integer.parseInt(request.getParameter("usuario")));
        if(cliente ==null){
           return "/presentation/cajero/crearCuenta/solicitudUsuario.jsp";
        }else{
            Moneda moneda = banco.data.MonedaDao.find(Integer.parseInt(request.getParameter("moneda")));
            TipoCuenta tipoCuenta = banco.data.TipoCuentaDao.find(Integer.parseInt(request.getParameter("tipoCuenta")));
            Cuenta cuenta = new Cuenta();
            Calendar calendar = java.util.Calendar.getInstance();
            cuenta.setFechaCreacion(calendar.getTime());
            cuenta.setLimiteTransferenciaDiaria(Double.parseDouble(request.getParameter("limiteTran")));
            cuenta.setFechaUltimaAplicacion(calendar.getTime());
            cuenta.setMonedaNombre(moneda);
            cuenta.setIdTipoCuenta(tipoCuenta);
            cuenta.setClienteIdCliente(cliente);
            if(banco.data.CuentaDao.registrar(cuenta)){
            return "/presentation/cajero/crearCuenta/View.jsp";
            }
        }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/presentation/cajero/crearCuenta/solicitudUsuario.jsp";
    }

    public String show(HttpServletRequest request) {
        banco.presentacion.cajero.creaCuenta.Model model = (banco.presentacion.cajero.creaCuenta.Model) request.getAttribute("model");
        try {
            model.setTipoMoneda(banco.data.MonedaDao.listar());
            model.setTipoCuenta(banco.data.TipoCuentaDao.listar());
            request.setAttribute("model", model);
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return "/presentation/cajero/crearCuenta/View.jsp";
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

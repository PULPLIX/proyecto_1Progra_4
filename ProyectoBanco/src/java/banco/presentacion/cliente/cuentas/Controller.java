/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.cuentas;

import banco.logica.Cliente;
import banco.logica.Usuario;
import java.io.IOException;
import banco.data.ClienteDao;
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
@WebServlet(name = "Controller", urlPatterns = {"/presentation/cliente/datos/show"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {
      
        request.setAttribute("model", new Model()); 
        
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/cliente/datos/show":
                viewUrl=this.show(request);
                break;           
 
        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

   
    public String show(HttpServletRequest request){
        return this.showAction(request);
    }
    
     
    public String showAction(HttpServletRequest request){
        Model model = (Model) request.getAttribute("model");

 HttpSession session = request.getSession(true);
 
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = null;
        try {
            cliente = banco.data.ClienteDao.find(usuario.getIdUsuario());
        } catch (Exception ex) {
            System.out.println(ex);
            cliente=null;
        }
        try {        
            model.setCuentas(banco.data.CuentaDao.getCuentasCliente(cliente.getIdCliente()));
            request.setAttribute("model", model);
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
        return "Short description";
    }// </editor-fold>

}

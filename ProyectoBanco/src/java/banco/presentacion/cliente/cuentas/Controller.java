/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.cuentas;

import banco.logica.Cliente;
import banco.logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "Controller", urlPatterns = {"/presentation/cliente/datos/show","/Guia/presentation/datos/update"})
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
            case "/Guia/presentation/datos/update":
                viewUrl=this.update(request);
        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    
    void updateModel(HttpServletRequest request){
       banco.presentacion.login.Model model= (banco.presentacion.login.Model) request.getAttribute("model");
       
        model.getCurrent().setIdUsuario(request.getParameter("cedulaFld"));
        model.getCurrent().setClaveAcceso(request.getParameter("claveFld"));
   }

    public String show(HttpServletRequest request){
        return this.showAction(request);
    }
    
     public String update(HttpServletRequest request){
        return this.updateAction(request);
    }
     
     public String updateAction(HttpServletRequest request){
         return "";
     }
        
    public String showAction(HttpServletRequest request){
        return "/presentation/cliente/datos/View.jsp"; 
    }    

        Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        if (request.getParameter("cedulaFld").isEmpty()){
            errores.put("cedulaFld","Cedula requerida");
        }

        if (request.getParameter("claveFld").isEmpty()){
            errores.put("claveFld","Clave requerida");
        }
        return errores;
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

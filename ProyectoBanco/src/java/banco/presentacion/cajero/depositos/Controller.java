/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.depositos;

import banco.data.ClienteDao;
import banco.logica.Cliente;
import banco.logica.Usuario;
import banco.logica.Cuenta;
import banco.logica.Movimiento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
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
@WebServlet(name = "controllerCajeroDepositos", urlPatterns = {"/presentation/cajero/depositos/show", "/cajero/depositos/buscarCliente", "/cajero/depositos/buscarCuenta", "/cajero/depositos/ingresar", "/cajero/depositos/seleccionar", "/cajero/depositos/confirmar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cajero.depositos.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/depositos/show":
                viewUrl = this.show(request);
                break;
            case "/cajero/depositos/buscarCliente":
                viewUrl = this.buscarCliente(request);
                break;
            case "/cajero/depositos/buscarCuenta":
                viewUrl = this.buscarCuenta(request);
                break;
            case "/cajero/depositos/ingresar":
                viewUrl = this.ingresar(request);
                break;
            case "/cajero/depositos/confirmar":
                viewUrl = this.confirmar(request);
                break;
            case "/cajero/depositos/seleccionar":
                viewUrl = this.seleccionar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public boolean validarCliente(HttpServletRequest request) {
        boolean error = false;
        if (request.getParameter("clienteABuscar").isEmpty()) {
            request.setAttribute("errorClienteVacio", "El campo de la cedula del cliente esta vacio");
            error = true;
        }
        String cedula = request.getParameter("clienteABuscar");
        for (int i = 0; i < cedula.length(); i++) {
            if (!(cedula.charAt(i) >= 47 && cedula.charAt(i) <= 57)) {
                request.setAttribute("errorClienteInvalido", "El formato de la cedula no es valido");
                error = true;

            }
        }
        return error;
    }

    public boolean validarCuenta(HttpServletRequest request) {
        boolean error = false;

        if (request.getParameter("cuentaABuscar").isEmpty()) {
            request.setAttribute("errorCuentaVacia", "El campo del nuemero de cuenta esta vacio");
            error = true;
        }
                String cedula = request.getParameter("cuentaABuscar");
        for (int i = 0; i < cedula.length(); i++) {
            if (!(cedula.charAt(i) >= 47 && cedula.charAt(i) <= 57)) {
                request.setAttribute("errorCuentaInvalido", "El formato del ID de la cuenta no es valido");
                error = true;

            }
        }
        
        return error;
    }

    public boolean validarIngresar(HttpServletRequest request) {
        boolean error = false;
        if (request.getParameter("monto").isEmpty()) {
            request.setAttribute("errorMontoVacio", "El monto no puede ser vacio");
            error = true;
        }
        if (request.getParameter("motivo").isEmpty()) {
            request.setAttribute("errorMotivoVacio", "El motivo no puede ser vacio");
            error = true;
        }
        if (request.getParameter("nombreDepositante").isEmpty()) {
            request.setAttribute("errorNombreDepositanteVacio", "El nombre del depositante no puede ser vacio");
            error = true;
        }
                String montoAux = request.getParameter("monto");
        for (int i = 0; i < montoAux.length(); i++) {
            if (!(montoAux.charAt(i) >= 47 && montoAux.charAt(i) <= 57)) {
                request.setAttribute("errorMontoInvalido", "En el campo del monto solo deben ir numeros");
                error = true;

            }
        }
        return error;
    }
    
    
    public boolean validarConfirmar(HttpServletRequest request) {
        boolean error = false;
       
        String cuenta = request.getParameter("cuentaConf");
        for (int i = 0; i < cuenta.length(); i++) {
            if (!(cuenta.charAt(i) >= 47 && cuenta.charAt(i) <= 57)) {
                request.setAttribute("errorCuentaInvalidoConfirmar", "El formato del ID de la cuenta no es valido");
                error = true;
            }
        }      
        String monto = request.getParameter("montoConf");
        for (int i = 0; i < cuenta.length(); i++) {
            if (!(monto.charAt(i) >= 47 && monto.charAt(i) <= 57)) {
                request.setAttribute("errorMontoInvalidoConfirmar", "El formato del monto ingresado no es valido");
                error = true;
            }
        }   
        String motivo = request.getParameter("motivoConf");
        if (motivo.equals("Vacio")) {
            request.setAttribute("errorMotivoVacioConfirmar", "El motivo no puede ser vacio");
            error = true;
        }
        String nomDep = request.getParameter("nombreDepositanteConf");
        if (nomDep.equals("Vacio")) {
            request.setAttribute("errorNombreDepositanteVacioConfirmar", "El nombre del depositante no puede ser vacio");
            error = true;
        }
        return error;
    }
    
    
    
    
    public String buscarCliente(HttpServletRequest request) {
        if (!validarCliente(request)) {
            HttpSession session = request.getSession(true);
            banco.presentacion.cajero.depositos.Model model = (banco.presentacion.cajero.depositos.Model) request.getAttribute("model");
            Integer ID = Integer.parseInt(request.getParameter("clienteABuscar"));

            try {

                Cliente clienteBuscar = banco.data.ClienteDao.buscarPorCliente(ID);
                request.setAttribute("clienteBuscar", String.valueOf(clienteBuscar.getIdCliente()));
                model.setClienteBuscar(clienteBuscar);
                model.setCuentas(banco.data.CuentaDao.getCuentasCliente(clienteBuscar.getUsuarioIdUsuario().getIdUsuario()));

                request.setAttribute("model", model);

                return ("/presentation/cajero/depositos/View.jsp");

            } catch (Exception ex) {
                
                request.setAttribute("errorClienteIndefinido", "No se encontro el cliente en la base de datos");
                return ("/presentation/cajero/depositos/View.jsp");
            }

        } else {
            return this.show(request);
        }
    }

    public String buscarCuenta(HttpServletRequest request) {
        if (!validarCuenta(request)) {
            HttpSession session = request.getSession(true);
            banco.presentacion.cajero.depositos.Model model = (banco.presentacion.cajero.depositos.Model) request.getAttribute("model");
            Integer ID = Integer.parseInt(request.getParameter("cuentaABuscar"));

            try {

                Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
                request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
                model.setSeleccionada(seleccionada);

                return ("/presentation/cajero/depositos/View.jsp");

            } catch (Exception ex) {
                request.setAttribute("errorCuentaIndefinido", "No se encontro la cuenta en la base de datos");
                return ("/presentation/cajero/depositos/View.jsp");
            }

        } else {
            return this.show(request);
        }
    }

    public String ingresar(HttpServletRequest request) {
        if (!validarIngresar(request)) {
            String motivo = request.getParameter("motivo");
            String nombreDepositante = request.getParameter("nombreDepositante");
            String monto = request.getParameter("monto");

            banco.presentacion.cajero.depositos.Model model = (banco.presentacion.cajero.depositos.Model) request.getAttribute("model");
            Integer ID = Integer.parseInt(request.getParameter("cuentaABuscar"));

            try {
                request.setAttribute("motivo", motivo);
                request.setAttribute("nombreDepositante", nombreDepositante);
                request.setAttribute("monto", monto);

                Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
                request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
                model.setSeleccionada(seleccionada);

                request.setAttribute("mensaje", "Se han ingresado los datos correctamente");

                return ("/presentation/cajero/depositos/View.jsp");

            } catch (Exception ex) {
                return ("/presentation/cajero/depositos/View.jsp");
            }
        } else {
            return this.show(request);
        }
    }

    public String confirmar(HttpServletRequest request) {
       if (!validarConfirmar(request)) {
        String motivo = (String) request.getParameter("motivoConf");
        String nombreDepositante = (String) request.getParameter("nombreDepositanteConf");
        String monto = (String) request.getParameter("montoConf");
        Integer ID = Integer.parseInt(request.getParameter("cuentaConf"));
        java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {

            Movimiento movimiento = new Movimiento();
            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);

            movimiento.setNombre_depositante(nombreDepositante);
            movimiento.setCuenta(seleccionada);
            movimiento.setAplicado((short) 1);
            movimiento.setMonto(Integer.valueOf(monto));
            movimiento.setNombre_depositante(nombreDepositante);
            movimiento.setMotivo(motivo);
            movimiento.setFecha(fecha);

            seleccionada.setSaldoFinal(seleccionada.getSaldoFinal() + Integer.valueOf(monto));

            banco.data.CuentaDao.insertarMovimiento(movimiento, seleccionada);
            banco.data.CuentaDao.updateSaldo(seleccionada);

            request.setAttribute("mensaje", "El depósito ha sido éxitoso");

            return ("/presentation/cajero/depositos/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/depositos/View.jsp");
        }
        } else {
            return this.show(request);
        }
    }

    public String seleccionar(HttpServletRequest request) {

        banco.presentacion.cajero.depositos.Model model = (banco.presentacion.cajero.depositos.Model) request.getAttribute("model");
        Integer ID = Integer.parseInt(request.getParameter("idCuenta"));

        try {
            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(ID);
            request.setAttribute("cuentaSeleccionada", seleccionada.getNumCuenta().toString());
            model.setSeleccionada(seleccionada);

            return ("/presentation/cajero/depositos/View.jsp");

        } catch (Exception ex) {
            return ("/presentation/cajero/depositos/View.jsp");
        }

    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());
        return "/presentation/cajero/depositos/View.jsp";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.transferencia;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Transferencia;
import banco.logica.Usuario;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author david
 */
@WebServlet(name = "controllerTransferenciaCajero", urlPatterns = {"/presentation/cajero/transferencia/show", "/cajero/transferir/confirmar", "/cajero/transferencia/buscar", "/cajero/transferencia/seleccionar"})

public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new banco.presentacion.cajero.transferencia.Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/cajero/transferencia/show":
                viewUrl = this.show(request);
                break;
            case "/cajero/transferir/confirmar":
                viewUrl = this.transferir(request);
                break;
            case "/cajero/transferencia/buscar":
                viewUrl = this.buscar(request);
                break;
            case "/cajero/transferencia/seleccionar":
                viewUrl = this.seleccionar(request);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String transferir(HttpServletRequest request) {
        banco.presentacion.cajero.transferencia.Model model = (banco.presentacion.cajero.transferencia.Model) request.getAttribute("model");

        HttpSession session = request.getSession(true);

        try {
            Transferencia t = new Transferencia();
            int numCuentaO = Integer.parseInt(request.getParameter("cuentaOrigenConf"));
            int numCuentaD = Integer.parseInt(request.getParameter("cuentaDestinoConf"));
            double monto = Double.parseDouble(request.getParameter("montoConf"));
            Cuenta origen = banco.data.CuentaDao.getCuenta(numCuentaO);
            if (monto < origen.getSaldoFinal() && monto < origen.getLimiteTransferenciaDiaria()) {
                t.setCuenta(origen);

                t.setCuenta_Destino(banco.data.CuentaDao.getCuenta(numCuentaD));

                t.setAplicado(Short.parseShort("1"));

                t.setMonto(String.valueOf(monto));
                Calendar calendar = java.util.Calendar.getInstance();
                t.setFecha(calendar.getTime());
                if (banco.data.movimientosDao.registrarTransferencia(t)) {
                    double montoFinal = t.getCuenta().getSaldoFinal() - monto;
                    double montoFinalDestino = t.getCuenta_Destino().getSaldoFinal() + monto;
                    double montoTransferencia = t.getCuenta().getLimiteTransferenciaDiaria() - monto;
                   
                    t.getCuenta().setSaldoFinal(montoFinal);
                    t.getCuenta().setLimiteTransferenciaDiaria(montoTransferencia);
                    t.getCuenta_Destino().setSaldoFinal(montoFinalDestino);

                    banco.data.CuentaDao.updateSaldo(t.getCuenta());
                    banco.data.CuentaDao.updateSaldo(t.getCuenta_Destino());
                }
            }
            return "/presentation/cajero/transferencia/View.jsp";
        } catch (Exception ex) {
            return "/errorCajeroTransferencia";
        }
    }

    public String buscar(HttpServletRequest request) {
        banco.presentacion.cajero.transferencia.Model model = (banco.presentacion.cajero.transferencia.Model) request.getAttribute("model");
//volver a poner al cliente aqui para evitar que luego no se pueda cambiar de cuenta.
//Porque si se deja asi solamente se podra elegir una vez y no es la gracia.
        if (!request.getParameter("cuentaOrigenSelect").isEmpty()) {
            try {
                Cuenta seleccionada = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaOrigenSelect")));
                model.setSeleccionada(seleccionada);
                model.setClienteBuscar(seleccionada.getClienteIdCliente());
            } catch (Exception ex) {
                System.out.print(ex);
            }
        } else {
            this.buscarCliente(request, model);
            this.buscarCuenta(request, model);
        }

        if (!request.getParameter("cuentaDestinoSelect").isEmpty()) {
            try {
                Cuenta seleccionada = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaDestinoSelect")));
                model.setSeleccionadaDestino(seleccionada);
                model.setClienteBuscarDestino(seleccionada.getClienteIdCliente());
            } catch (Exception ex) {
                System.out.print(ex);
            }
        } else {
            this.buscarClienteDestino(request, model);
            this.buscarCuentaDestino(request, model);
        }
        if (!request.getParameter("monto").isEmpty()) {
            try {
                model.setMonto(Double.parseDouble(request.getParameter("monto")));
                request.setAttribute("numCuentaO", String.valueOf( model.getSeleccionada().getNumCuenta()));
                request.setAttribute("numCuentaD", String.valueOf( model.getSeleccionadaDestino().getNumCuenta()));
                request.setAttribute("monto", String.valueOf(model.getMonto()));
            } catch (Exception ex) {
                System.out.print(ex);
            }
        }
        request.setAttribute("model", model);

        return ("/presentation/cajero/transferencia/View.jsp");

    }

    public String seleccionar(HttpServletRequest request) {
        banco.presentacion.cajero.transferencia.Model model = (banco.presentacion.cajero.transferencia.Model) request.getAttribute("model");

        try {

            if (request.getParameter("idCuentaO") != null) {
                Cuenta seleccionada = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("idCuentaO")));
                model.setSeleccionada(seleccionada);
                if (seleccionada != null) {
                    model.setClienteBuscar(seleccionada.getClienteIdCliente());
                }

            }
            if (request.getParameter("cuentaDestinoSelect") != null) {
                Cuenta seleccionadaDestino = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaDestinoSelect")));
                model.setSeleccionadaDestino(seleccionadaDestino);
                if (seleccionadaDestino != null) {
                    model.setClienteBuscarDestino(seleccionadaDestino.getClienteIdCliente());
                }

            }

            if (request.getParameter("idCuentaD") != null) {
                Cuenta seleccionadaDestino = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("idCuentaD")));
                model.setSeleccionadaDestino(seleccionadaDestino);
                if (seleccionadaDestino != null) {
                    model.setClienteBuscarDestino(seleccionadaDestino.getClienteIdCliente());
                }

            }
            if (request.getParameter("cuentaOrigenSelect") != null) {
                Cuenta seleccionada = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaOrigenSelect")));
                model.setSeleccionada(seleccionada);
                if (seleccionada != null) {
                    model.setClienteBuscar(seleccionada.getClienteIdCliente());
                }

            }
            return ("/presentation/cajero/transferencia/View.jsp");
        } catch (Exception ex) {
            return ("/presentation/cajero/transferencia/View.jsp");
        }

    }

    public void buscarCliente(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {
            Cliente clienteBuscar = banco.data.ClienteDao.buscarPorCliente(Integer.parseInt(request.getParameter("clienteABuscar")));
            model.setClienteBuscar(clienteBuscar);
            model.setCuentas(banco.data.CuentaDao.getCuentasCliente(clienteBuscar.getUsuarioIdUsuario().getIdUsuario()));

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void buscarCuenta(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {

            Cuenta seleccionada = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaABuscar")));
            model.setSeleccionada(seleccionada);

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void buscarClienteDestino(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {
            Cliente clienteBuscarDestino = banco.data.ClienteDao.buscarPorCliente(Integer.parseInt(request.getParameter("clienteABuscarDestino")));
            model.setClienteBuscarDestino(clienteBuscarDestino);
            model.setCuentasDestino(banco.data.CuentaDao.getCuentasCliente(clienteBuscarDestino.getUsuarioIdUsuario().getIdUsuario()));

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void buscarCuentaDestino(HttpServletRequest request, banco.presentacion.cajero.transferencia.Model model) {

        try {
            Cuenta seleccionadaDestino = banco.data.CuentaDao.getCuenta(Integer.parseInt(request.getParameter("cuentaABuscarDestino")));
            model.setSeleccionadaDestino(seleccionadaDestino);
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());
        return "/presentation/cajero/transferencia/View.jsp";
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

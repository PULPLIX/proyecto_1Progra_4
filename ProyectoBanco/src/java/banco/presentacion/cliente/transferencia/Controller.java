/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.transferencia;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Moneda;
import banco.logica.Transferencia;
import banco.logica.Usuario;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "controllerTransferencia", urlPatterns = {"/presentation/login/transferencia/show", "/transferir/selecciona/cuentas", "/transferir/confirmar", "/transferir/ingresar"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/login/transferencia/show":
                viewUrl = this.show(request);
                break;
            case "/transferir/selecciona/cuentas":
                viewUrl = this.seleccionaCuenta(request);
                break;
            case "/transferir/confirmar":
                viewUrl = this.transferir(request);
                break;
            case "/transferir/ingresar":
                viewUrl = this.ingresar(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    public String ingresar(HttpServletRequest request) {
        try {
            Model model = (Model) request.getAttribute("model");

            String numCuentaO = (String) request.getParameter("cuentaOrigenSelect");
            String numCuentaD = (String) request.getParameter("cuentaDestinoSelect");
            String monto = (String) request.getParameter("monto");

            if (!(numCuentaO).equals(numCuentaD)) {
                try {
                    model.setSeleccionado(banco.data.CuentaDao.getCuentaUnica(numCuentaO));
                    request.setAttribute("numCuentaO", numCuentaO);
                } catch (Exception e) {
                    request.setAttribute("vaciaO", "errorTxt");
                }
                try {
                    model.setaTransferir(banco.data.CuentaDao.getCuentaUnica(numCuentaD));
                    request.setAttribute("numCuentaD", numCuentaD);
                } catch (Exception e) {
                    request.setAttribute("vacioD", "errorTxt");
                }

                if (!request.getParameter("monto").isEmpty()) {
                    if (Double.parseDouble(monto) <= model.getSeleccionado().getLimiteTransferenciaDiaria() && (Double.parseDouble(monto) <= model.getSeleccionado().getSaldoFinal())) {
                        request.setAttribute("monto", monto);
                    } else {
                        request.setAttribute("errorMonto", "errorTxt");
                    }
                } else {
                    request.setAttribute("vacioM", "errorTxt");
                }
            } else {
                request.setAttribute("iguales", "errorTxt");
            }
            request.setAttribute("mode1", model);

            return this.show(request);
        } catch (Exception ex) {
            return this.show(request);
        }
    }

    public boolean validar(HttpServletRequest request) {
        boolean error = false;
        if (request.getParameter("cuentaDestinoConf").isEmpty()) {
            request.setAttribute("vacioD", "errorTxt");
            error = true;
        }
        if (request.getParameter("cuentaOrigenConf").isEmpty()) {
            request.setAttribute("vacioO", "errorTxt");
            error = true;
        }
        if (request.getParameter("montoConf").isEmpty()) {
            request.setAttribute("vacioM", "errorTxt");
            error = true;
        }

        return error;
    }

    public String transferir(HttpServletRequest request) {
        if (!this.validar(request)) {
            return this.transferirAction(request);
        } else {
            return "/presentation/cliente/transferencia/View.jsp";
        }
    }

       public String transferirAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");

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
                    double montoTransferencia = t.getCuenta().getLimiteTransferenciaDiaria() - monto;
                    double montoFinalDestino = this.conversion(monto, t.getCuenta().getMonedaNombre(), t.getCuenta_Destino().getMonedaNombre()) + t.getCuenta_Destino().getSaldoFinal();

                    t.getCuenta().setSaldoFinal(montoFinal);
                    t.getCuenta().setLimiteTransferenciaDiaria(montoTransferencia);
                    t.getCuenta_Destino().setSaldoFinal(montoFinalDestino);

                    banco.data.CuentaDao.updateSaldo(t.getCuenta());
                    banco.data.CuentaDao.updateSaldo(t.getCuenta_Destino());
                    request.setAttribute("mensaje", " ✅ Transferencia Exitosa !!!");
                }
            } else {
                request.setAttribute("estadoTransferencia", " ⚠ No se pudo realizar la transferencia debido a que el monto ingresedo fue invalido.");
            }
            return this.show(request);
        } catch (Exception ex) {
            request.setAttribute("estadoTransferencia", "⚠No se pudo realizar la transferencia");
            return this.show(request);
        }
    }

    public double conversion(double monto, Moneda monedaOrg, Moneda monedaDest) {

        if (Objects.equals(monedaOrg.getNombre(), monedaDest.getNombre())) {
            return monto;
        } else if (Objects.equals(monedaOrg.getNombre(), 2)) {
            double montoConvertido = monto * monedaDest.getTipoCambioCompra();
            return montoConvertido;
        } else if (Objects.equals(monedaDest.getNombre(), 2)) {
            double montoDolar = monto / monedaOrg.getTipoCambioCompra();
            return montoDolar;
        } else {
            double montoDolar = monto / monedaOrg.getTipoCambioCompra();
            double montoConvertido = montoDolar * monedaDest.getTipoCambioCompra();
            return montoConvertido;
        }

    }

    public String seleccionaCuenta(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");

        try {
            if (request.getParameter("idCuentaOrigen") != null) {
                model.setSeleccionado(banco.data.CuentaDao.getCuentaUnica((String) request.getParameter("idCuentaOrigen")));
            }
            if (request.getParameter("idCuentaDestino") != null) {
                model.setaTransferir(banco.data.CuentaDao.getCuentaUnica((String) request.getParameter("idCuentaDestino")));
            }
            request.setAttribute("model", model);
            return this.show(request);

        } catch (Exception e) {
            return this.show(request);
        }
    }

    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        request.setAttribute("clienteNombre", cliente.getNombre());
        request.setAttribute("clienteApellidos", cliente.getApellidos());

        Model model = (Model) request.getAttribute("model");

        try {
            model.setCuentas(banco.data.CuentaDao.getCuentasCliente(cliente.getUsuarioIdUsuario().getIdUsuario()));
            model.setFavoritas(cliente.getFavoritasCollection());

        } catch (Exception ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return "/presentation/cliente/transferencia/View.jsp";
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

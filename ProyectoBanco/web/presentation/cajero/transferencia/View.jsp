<%-- 
    Document   : TEMPLATE
    Created on : 03/03/2020, 07:54:14 PM
    Authors     : Iván Chinchilla - David Aguilar
--%>
<%@page import="banco.presentacion.cajero.transferencia.Model"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    banco.presentacion.cajero.transferencia.Model model = (banco.presentacion.cajero.transferencia.Model) request.getAttribute("model");
    Cliente clienteBuscar = model.getClienteBuscar();
    ArrayList<Cuenta> cuentas = model.getCuentas();
    Cuenta seleccionada = model.getSeleccionada();
    Cliente clienteBuscarDestino = model.getClienteBuscarDestino();
    ArrayList<Cuenta> cuentasDestino = model.getCuentasDestino();
    Cuenta seleccionadaDestino = model.getSeleccionadaDestino();
%>
<html>
    <head>
        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Depósitos</title>
    </head>

    <body>

        <div class="container-all" id="modal">
            <div class="popup">
                <div class="img"></div>
                <div class="container-text">
                    <h1>Depósito</h1>
                    <div class="inputs">
                        <form name="confirm" action="/ProyectoBanco/cajero/depositos/confirmar" method="post">
                            <label>Cuenta Origen:&nbsp</label> <input type="text" name="cuentaConf" value="<% if (model.getSeleccionada() != null) {
                                out.print((String) String.valueOf(model.getSeleccionada().getNumCuenta()));
                                } else {
                                    out.print("Vacío");
                                }%>" id="inputText" readonly/><br>
                            <label>Cuenta Destino&nbsp</label> <input type="text" name="cuentaConf" value="<% if (model.getSeleccionada() != null) {
                                out.print((String) String.valueOf(model.getSeleccionada().getNumCuenta()));
                                } else {
                                    out.print("Vacío");
                                }%>" id="inputText" readonly/><br>
                            <label>Monto:&nbsp&nbsp&nbsp</label> <input type="text" name="montoConf" value="<% if ((String) request.getAttribute("monto") != null) {
                                    out.print((String) request.getAttribute("monto"));
                                } else {
                                    out.print("Vacío");
                                }%>" id="inputText" readonly/><br>
                            <br>
                            <center><input class="btn-confirm" type="submit" value="Confirmar" id="inputBoton"/><br></center>
                        </form>
                    </div>
                </div>
                <a href="#" class="btn-close-popup">X</a>
            </div>
        </div>


        <!-- SECCION DE INF PERSONAL
                ============================================ -->
        <%@ include file="/presentation/Template/InformacionPersonal.jsp" %>

        <!-- SECCION DE MENU
               ============================================ -->
        <%@ include file="/presentation/Template/MenuCajero.jsp" %>

        <!-- SECCION DE CUERPO
                ============================================ -->
        <%@ include file="/presentation/cajero/transferencia/Cuerpo.jsp" %>

        <br><br><br><br>
        <a href="#modal" class="btn-open-popup">Confirmar</a>
        <br><br>


        <br> <div class="cuerpo-caja pie">
            <h2>Banco Central</h2>
        </div>

        <!-- SECCION DE COPYRIGHT
                ============================================ -->
        <div class="cuerpo-caja copyright">
            <h3> Copyright ©2020 All rights reserved | Iván Chinchilla y David Aguilar</h3>
        </div>

    </body>

</html>

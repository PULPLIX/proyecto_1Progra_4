<%-- 
    Document   : View
    Created on : Mar 28, 2020, 11:21:22 PM
    Author     : Oscar
--%>

<%
    banco.presentacion.cajero.retiros.Model model = (banco.presentacion.cajero.retiros.Model) request.getAttribute("model");
    Cliente clienteBuscar = model.getClienteBuscar();
    List<Cuenta> cuentas = model.getCuentas();
    Cuenta seleccionada = model.getSeleccionada();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Retiros</title>
    </head>

    <body>

        <div class="container-all" id="modal">
            <div class="popup">
                <div class="img"></div>
                <div class="container-text">
                    <h1>Retiros</h1>
                    <div class="inputs">
                        <form name="confirm" action="/ProyectoBanco/cajero/retiros/confirmar" method="post">
                            <label>Cuenta:&nbsp</label> <input type="text" name="cuentaConf" value="<% if (model.getSeleccionada() != null) {
                                out.print((String) String.valueOf(model.getSeleccionada().getNumCuenta()));
                                } else {
                                    out.print("Vacío");
                                }%>" id="inputText" readonly/><br>
                            <label>Monto:&nbsp&nbsp&nbsp</label> <input type="text" name="montoConf" value="<% if ((String) request.getAttribute("monto") != null) {
                                    out.print((String) request.getAttribute("monto"));
                                } else {
                                    out.print("Vacío");
                                }%>" id="inputText" readonly/><br>
 
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
        <%@ include file="/presentation/cajero/retiros/Cuerpo.jsp" %>

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

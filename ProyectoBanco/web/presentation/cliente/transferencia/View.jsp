<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/table.css">


    <head>
        <link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/popUp.css">

        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Mis cuentas</title>
    </head>
    <body>           
        <div class="container-all" id="modal">
            <div class="popup">
                <div class="img"></div>
                <div class="container-text">
                    <h1>Transferencia Bancaria</h1>
                    <div class="inputs">
                        <form name="confirm" action="/ProyectoBanco/transferir/confirmar" method="post">
                            <label>Cuenta Origen:</label> <input type="text" name="cuentaOrigenConf" value="<% if ((String) request.getAttribute("numCuentaO") != null) {
                    out.print((String) request.getAttribute("numCuentaO"));
                } else {
                    out.print("Vacío");
                }%>" id="inputText" readonly/><br>
                            <label>Cuenta destino:</label><input type="text" name="cuentaDestinoConf" value="<% if ((String) request.getAttribute("numCuentaD") != null) {
                    out.print((String) request.getAttribute("numCuentaD"));
                } else {
                    out.print("Vacío");
                }%>" id="inputText" readonly/><br>
                            <center><label>Monto:</label><input type="text" name="montoConf" value="<% if ((String) request.getAttribute("monto") != null) {
                    out.print((String) request.getAttribute("monto"));
                } else {
                    out.print("Vacío");
                }%>" id="inputText" readonly/><br>
                            <input class="btn-confirm" type="submit" value="Confirmar" id="inputBoton"/><br></center>
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
        <%@ include file="/presentation/Template/Menu.jsp" %>

        <br><br>
        <br><br>

        <!-- SECCION DE CUERPO
                ============================================ -->
        <%@ include file="/presentation/cliente/transferencia/Cuerpo.jsp" %>

        <br><br>            
                <a href="#modal" class="btn-open-popup">Confirmar </a>
        <br><br>  
        <br><br>   
        <br><br>   
        <br><br> 
        <div class="cuerpo-caja pie">
            <h2>Banco Central</h2>
        </div>

        <!-- SECCION DE COPYRIGHT
                ============================================ -->
        <div class="cuerpo-caja copyright">
            <h3> Copyright ©2020 All rights reserved | Iván Chinchilla y David Aguilar</h3>
        </div>

    </body>


</html>


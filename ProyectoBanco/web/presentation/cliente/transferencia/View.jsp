<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Mis cuentas</title>
        <link rel="stylesheet" href="/ProyectoBanco/css/Template/registro.css"/>
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
                                } %>" id="inputText" placeholder="vacío" readonly/><br>
                            <label>Cuenta destino:</label><input type="text" name="cuentaDestinoConf" value="<% if ((String) request.getAttribute("numCuentaD") != null) {
                                    out.print((String) request.getAttribute("numCuentaD"));
                                } %>" id="inputText" placeholder="vacío" readonly/><br>
                            <center><label>Monto:</label><input type="text" name="montoConf" value="<% if ((String) request.getAttribute("monto") != null) {
                                    out.print((String) request.getAttribute("monto"));
                                }
                               %>" id="inputText" placeholder="vacío"  readonly/><br>
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


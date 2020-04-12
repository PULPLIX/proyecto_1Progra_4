<%-- 
    Document   : View
    Created on : Mar 23, 2020, 3:20:55 PM
    Author     : Oscar
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Cuentas Favoritas</title>
    </head>

    <body>
        <div class="container-all" id="modal">
            <div class="popup">
                <div class="imgV"></div>
                <div class="container-text">
                    <center><h1>Vicular una cuenta</h1> </center>
                    <div class="inputs">
                        <form name="confirm" action="/ProyectoBanco/cliente/cuentas/favoritas" method="post">
                            <center>
                                <label>Número de cuenta a vincular:</label> <input type="text" name="cuentaVincular" value="<%if (request.getAttribute("idCuenta") != null) {
                                out.print(request.getAttribute("idCuenta"));
                       }%>" id="inputText" placeholder="Id cuenta"/><br><br><br><br>
                                <input class="btn-confirm" type="submit" value="Confirmar" id="inputBoton"/><br>
                            </center>
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
        <%@ include file="/presentation/cliente/infoPersonal/favoritas/Cuerpo.jsp" %>

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

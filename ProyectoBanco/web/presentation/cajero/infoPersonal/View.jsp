<%-- 
    Document   : TEMPLATE
    Created on : 03/03/2020, 07:54:14 PM
    Authors     : Iván Chinchilla - David Aguilar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Mi perfil</title>
    </head>

    <body>
        <!-- SECCION DE INF PERSONAL
                ============================================ -->
        <%@ include file="/presentation/Template/InformacionPersonal.jsp" %>

        <!-- SECCION DE MENU
               ============================================ -->
        <%@ include file="/presentation/Template/MenuCajero.jsp" %>

        <br><br>
        <br><br>

        <!-- SECCION DE CUERPO
                ============================================ -->
        <%@ include file="/presentation/cajero/infoPersonal/Cuerpo.jsp" %>

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

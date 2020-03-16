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
        <title>TÍTULO</title>
    </head>

    <body>
         <!-- SECCION DE MENU
                ============================================ -->
        <%@ include file="/presentation/Template/Menu.jsp" %>
        
        <!-- SECCION DE CABEZA
                ============================================ -->
        <%@ include file="/presentation/Template/Cabeza.jsp" %>
        
        <!-- SECCION DE CUERPO
                ============================================ -->
        <%@ include file="/presentation/Template/Cuerpo.jsp" %>

        <!-- SECCION DE PIE
                ============================================ -->
        <!--%@ include file="/presentation/Template/Pie.jsp" %-->

    </body>

</html>

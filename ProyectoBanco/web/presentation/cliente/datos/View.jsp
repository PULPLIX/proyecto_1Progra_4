<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Mis cuentas</title>
    </head>

    <body>
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
        <%@ include file="/presentation/cliente/datos/Cuerpo.jsp" %>
        
    </body>

</html>

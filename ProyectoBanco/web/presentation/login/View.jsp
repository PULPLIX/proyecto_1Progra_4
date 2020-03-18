
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banco.presentacion.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Index/HojaEstilo.jsp" %>
        <title>Log In</title>
    </head>
    <body >
        <%! private String erroneo(String campo, Map<String, String> errores) {
                if ((errores != null) && (errores.get(campo) != null)) {
                    return "is-invalid";
                } else {
                    return "";
                }
            }

        %>
        <%!   private String title(String campo, Map<String, String> errores) {
                if ((errores != null) && (errores.get(campo) != null)) {
                    return errores.get(campo);
                } else {
                    return "";
                }
            }
        %>

        <% Model model = (Model) request.getAttribute("model"); %>
        <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");%>

        <form name="form" action="/ProyectoBanco/presentation/login/login" method="post" > 
            <div class="panel" style="width:30%;">
                <div class="fila encabezado">Login</div>
                <div class="fila">
                    <div class="etiqueta">Cedula</div>
                    <div class="campo"><input class="<%=erroneo("cedulaFld", errores)%>" placeholder="Cedula del usuario" type="text" name="cedulaFld" value="" title="<%=title("cedulaFld", errores)%>"></div>
                </div>
                <div class="fila">
                    <div class="etiqueta">Clave</div>
                    <div class="campo"><input class="<%=erroneo("claveFld", errores)%>" placeholder="Clave del usuario" type="password" name="claveFld" value="" title="<%=title("claveFld", errores)%>"></div>
                </div>
                <div class="fila encabezado"><button  style="margin-bottom: 15px">Ingresar</button> </div>
                <div>
                    <%=(request.getAttribute("mensaje") != null) ? request.getAttribute("mensaje") : " No se ha hecho ningun cambio"%>
                </div>
            </div>

        </form>
        <%@ include file="/presentation/Index/Pie.jsp" %>

    </body>
</html>

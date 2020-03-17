
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banco.presentacion.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Index/HojaEstilo.jsp" %>

    </head>
    <body >


        <% Model model = (Model) request.getAttribute("model"); %>
        <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
        <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>

        <form name="form" action="/Guia/presentation/login/login" method="post" > 
            <div class="panel" style="width:30%;">
                <div class="fila encabezado">Login</div>
                <div class="fila">
                    <div class="etiqueta">Cedula</div>
                    <div class="campo"><input class="<%=erroneo("cedulaFld", errores)%>" placeholder="Cedula del usuario" type="text" name="cedulaFld" value="<%=form.get("cedulaFld")[0]%>" title="<%=title("cedulaFld", errores)%>"></div>
                </div>
                <div class="fila">
                    <div class="etiqueta">Clave</div>
                    <div class="campo"><input class="<%=erroneo("claveFld", errores)%>" placeholder="Clave del usuario" type="password" name="claveFld" value="<%=form.get("claveFld")[0]%>" title="<%=title("claveFld", errores)%>"></div>
                </div>
                <div class="fila encabezado"><button  style="margin-bottom: 15px">Ingresar</button> </div>
            </div>
        </form>
        <%@ include file="/presentation/Index/Pie.jsp" %>

    </body>
</html>
<%!
    private String erroneo(String campo, Map<String, String> errores) {
        if ((errores != null) && (errores.get(campo) != null)) {
            return "is-invalid";
        } else {
            return "";
        }
    }

    private String title(String campo, Map<String, String> errores) {
        if ((errores != null) && (errores.get(campo) != null)) {
            return errores.get(campo);
        } else {
            return "";
        }
    }

    private Map<String, String[]> getForm(Model model) {
        Map<String, String[]> values = new HashMap<>();
        values.put("cedulaFld", new String[]{model.getCurrent().getIdUsuario()});
        values.put("claveFld", new String[]{model.getCurrent().getClaveAcceso()});
        return values;
    }

%> 
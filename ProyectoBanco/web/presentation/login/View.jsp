<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banco.presentacion.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Template/HojaEstilo.jsp" %>
        <title>Log In</title>
    </head>
    <body>

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

        <!-- SECCION DE MENU
                ============================================ -->
        <%@ include file="/presentation/login/Menu.jsp" %>

        <div class="cuerpo">

            <div class="cuerpo-caja cuerpo-cabeza">
                <center>
                    <h2>INICIAR SESIÓN</h2>
                </center>
            </div>

            <div class="cuerpo-caja cuerpo-derecho">
                <center>
                    <form name="form" action="/ProyectoBanco/presentation/login/login" method="post" > 
                        <div class="panel" style="width:30%;">
                            <div class="fila">
                                <div class="etiqueta">Cédula</div>
                                <div class="campo"><input class="<%=erroneo("cedulaFld", errores)%>" placeholder="Cedula del usuario" type="text" name="cedulaFld" value="" title="<%=title("cedulaFld", errores)%>"></div>
                            </div>
                            <br>
                            <div class="fila">
                                <div class="etiqueta">Clave</div>
                                <div class="campo"><input class="<%=erroneo("claveFld", errores)%>" placeholder="Clave del usuario" type="password" name="claveFld" value="" title="<%=title("claveFld", errores)%>"></div>
                            </div>
                            <br>
                            <div class="fila encabezado"><button  style="margin-bottom: 15px">Ingresar</button> </div>
                            <div>
                                <%=(request.getAttribute("mensaje") != null) ? request.getAttribute("mensaje") : " No se ha hecho ningun cambio"%>
                            </div>
                        </div>
                    </form>
                </center>
                <br><br>
                <br><br>
            </div>

            <div class="cuerpo-caja pie">
                <h2>Banco Central</h2>
            </div>

            <!-- SECCION DE COPYRIGHT
                    ============================================ -->
            <div class="cuerpo-caja copyright">
                <h3> Copyright ©2020 All rights reserved | Iván Chinchilla y David Aguilar</h3>
            </div>

        </div>

    </body>
</html>
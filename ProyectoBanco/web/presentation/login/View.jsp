<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banco.presentacion.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Iniciar sesión</title>
    </head>
    <body>
        <link rel="stylesheet" href="/ProyectoBanco/css/Template/cuerpo.css"/>
        <link rel="stylesheet" href="/ProyectoBanco/css/Template/registro.css"/>
        <link rel="stylesheet" href="/ProyectoBanco/css/login/login.css"/>

        <div class="cabeza">               
            <a href="/ProyectoBanco/login/logout"><img src="/ProyectoBanco/images/logo/logoBlanco.png" class="logoimg2"></a>  
        </div>
        <div class="wrap">


            <div class="titulo">
                <h1>INICIAR SESIÓN</h1>
            </div>
            <div class="img-log"></div>
            <div class="formulario">
                <form name="form" action="/ProyectoBanco/presentation/login/login" method="post" > 
                    <div class="fila">
                        <div class="etiqueta"><label>Cédula: </label></div>
                        <div class="campo">
                            <input class="<%if ((String) request.getAttribute("errorCedula") != null) {
                                    out.print((String) request.getAttribute("errorCedula"));
                                } %>" placeholder="Cedula del usuario" type="text" name="cedulaFld" value="" title="">
                            <%if ((String) request.getAttribute("errorCedula") != null) {
                                    out.print("<br>" + "<p style=\"color:red; font-size: 14px;\">* Cedula requerida</p>");
                                } %>

                        </div>
                    </div>
                    <div class="fila">
                        <div class="etiqueta">Clave: </div>
                        <div class="campo">
                            <input class="<%if ((String) request.getAttribute("errorClave") != null) {
                                    out.print((String) request.getAttribute("errorClave"));
                                }%>" placeholder="Clave del usuario" type="password" name="claveFld" value="" title="">
                            <%if ((String) request.getAttribute("errorClave") != null) {
                                    out.print("<br>" + "<p style=\"color:red; font-size: 14px;\">* Clave requerida</p>");
                                }%>
                        </div>
                    </div>
                    <div class="error">
                        <%if ((String) request.getAttribute("errorRegistrar") != null) {
                                out.print("<br>" + "<p style=\"color:red; font-size: 14px;\">" + (String) request.getAttribute("errorRegistrar") + " </p>");
                            }%>
                    </div>
                    <div class="fila encabezado"><button class="btn">Ingresar</button> </div>
                </form>
            </div>
        </div>
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
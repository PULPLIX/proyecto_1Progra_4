<%-- 
    Document   : Index
    Created on : 03/03/2020, 07:26:41 PM
    Authors     : Iván Chinchilla - David Aguilar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/HojaEstiloIndex.jsp" %>
        <title>Banco</title>
    </head>

    <body>
        <!-- MENU
                ============================================ -->
        <%@ include file="/presentation/Menu.jsp" %>

        <!-- SECCION DE CABEZA
                 ============================================ -->
        <div class="cabeza">
            <div class="cabeza-contenido">
                <div class="cabeza-caja cabeza-derecho">
                    <center><br>
                        <br><br><br><br>
                        <div id="transicion-imagen" class="shadow">
                            <img src="/ProyectoBanco/images/publicidad4crop.jpg">
                            <img src="/ProyectoBanco/images/publicidad3crop.jpg">
                            <img src="/ProyectoBanco/images/publicidad1crop.jpg">
                            <img src="/ProyectoBanco/images/publicidad5crop.jpg">
                        </div><br>
                        <br><br><br><br>
                        <h3>El mejor sistema bancario para su tranquilidad</h3>
                        <br><br><br><br>
                        <br><br><br><br>
                    </center>
                </div>
            </div>

            <div class="cabeza-contenido">
                <br><br><br><br>
                <br><br>
                <div class="cabeza-caja cabeza-izquierdo">
                    <h2>BIENVENIDO AL SISTEMA BANCARIO</h2><br><br>
                    <form>
                        Cédula: &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input type="text"><br>
                        <br>
                        Contraseña: &ensp;&ensp;<input type="password"><br><br>
                        <a href="#">INICIA SESION</a>
                    </form>
                </div>
            </div>
        </div>

        <!-- SECCION DEL CUERPO
                ============================================ -->

        <div class="cuerpo">
            
            <div class="cuerpo-caja cuerpo-izquierdo">
                <div class="imagen-muestra">
                    <ul class="imagen-muestra">
                        <li>
                            <img src="/ProyectoBanco/images/tarjeta.jpg" alt="">
                            <div class="muestra">
                                <div class="difuminado"></div>
                                <div class="muestra-texto">
                                    <h1>TRANSFERENCIAS
                                        <br>Mayor seguridad en tus transferencias</h1>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="cuerpo-caja cuerpo-central">
                <div class="imagen-muestra">
                    <ul class="imagen-muestra">
                        <li>
                            <img src="/ProyectoBanco/images/tarjeta.jpg" alt="">
                            <div class="muestra">
                                <div class="difuminado"></div>
                                <div class="muestra-texto">
                                    <h1>TRANSFERENCIAS
                                        <br>Mayor seguridad en tus transferencias</h1>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="cuerpo-caja cuerpo-derecho">
                <div class="imagen-muestra">
                    <ul class="imagen-muestra">
                        <li>
                            <img src="/ProyectoBanco/images/tarjeta.jpg" alt="">
                            <div class="muestra">
                                <div class="difuminado"></div>
                                <div class="muestra-texto">
                                    <h1>TRANSFERENCIAS
                                        <br>Mayor seguridad en tus transferencias</h1>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            
        </div>

        <br><br><br>
        
        <!-- SECCION DE PIE
		============================================ -->
        <%@ include file="/presentation/Pie.jsp" %>

    </body>

</html>

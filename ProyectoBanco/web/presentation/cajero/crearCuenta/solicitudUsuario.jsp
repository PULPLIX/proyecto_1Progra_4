<%-- 
    Document   : solicitudUsuario
    Created on : Mar 27, 2020, 8:08:02 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error!</title>
    </head>
    <body>

            <div class="container-all-Error" >
                <div class="content">
                    <h1 id="Titulo"  style="font-size: 100px;">ERROR! </h1>
                        <form name="confirm" method="post">
                             <img src="/ProyectoBanco/images/is-invalid.png"><p style="font-size: 30px;  margin-top: 10px; margin-bottom: 40px;">El número de cédula ingresado no existe.<br><br>¿Desea Crear un usuario Nuevo o <br>devolverse a <i>"Registro Cuenta"</i>?</p><br><br><br><br>
                            <a href="/ProyectoBanco/presentation/login/crearCliente" class="btn-confirm">Crear Cliente</a>
                            <a href="/ProyectoBanco/presentation/cajero/crearCuenta" class="btn-confirm">Devolverse</a>
                        
                        </form>
                </div>
            </div>

    </body>

</html>

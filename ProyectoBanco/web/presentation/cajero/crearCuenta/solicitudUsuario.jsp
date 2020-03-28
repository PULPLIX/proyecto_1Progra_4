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
    <center>

        <div class="container-all-Error" >
            <div class="content">
                <h1 id="Titulo">El usuario Ingresado No existe!</h1>
                <div class="inputs">
                    <form name="confirm" method="post">
                        <p>Desea Crear un usuario Nuevo o devolverse a Registro Cuenta?</p><br><br><br><br>
                        <a href="/ProyectoBanco/presentation/login/crearCliente" class="btn-confirm">Crear Cliente</a>
                        <a href="/ProyectoBanco/presentation/cajero/crearCuenta" class="btn-confirm">Devolverse</a>
                    </form>
                </div>
            </div>
        </div>
    </center>

</body>

</html>

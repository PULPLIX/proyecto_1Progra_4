<%-- 
    Document   : Cuerpo
    Created on : 26/03/2020, 07:55:36 PM
    Author     : Iván
--%>

<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/infoPersonal.css">
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">

<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cajero.infoPersonal.Model"%>

<%
    banco.presentacion.cajero.infoPersonal.Model model = (Model) request.getAttribute("model");
    //Cliente cliente = (Cliente) session.getAttribute("cliente");

%>


<div class="limiter"> 

    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <center><h1> Registro de Cliente </h1>
                    <form action="/ProyectoBanco/registrar/cliente">
                        <label>usuario:</label><input type="text" name="usuario" value="" placeholder="usuario" id="inputTextR" /><br>
                        <label>nombre:</label><input type="text" name="nombre" value="" placeholder="Nombre" id="inputTextR" /><br>
                        <label>apellidos:</label><input type="text" name="apellidos" value="" placeholder="Apellidos" id="inputTextR" /><br>
                        <label>Cédula:</label><input type="text" name="id" value="" placeholder="Cedula" id="inputTextR" /><br>
                        <label>Teléfono</label><input type="text" name="telefono" value="" placeholder="Telefono" id="inputTextR" /> <br>                   
                        <input type="submit" value="Registrar" class="btn-confirm"/><br>
                    </form>
                </center>
                <br><br>
                <br><br>
                <br><br>
                <br><br>
                <br><br>
                <br><br>
                <br><br>


            </div>
        </div>
    </div>

    <br><br>

</div>  
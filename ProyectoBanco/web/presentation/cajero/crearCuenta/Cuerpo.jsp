<%-- 
    Document   : Cuerpo
    Created on : 26/03/2020, 07:55:36 PM
    Author     : Iván
--%>

<%@page import="banco.logica.TipoCuenta"%>
<%@page import="banco.logica.Moneda"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/infoPersonal.css">
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">

<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cajero.creaCuenta.Model"%>

<%
    banco.presentacion.cajero.creaCuenta.Model model = (banco.presentacion.cajero.creaCuenta.Model) request.getAttribute("model");
    ArrayList<Moneda> monedaList =  model.getTipoMoneda();
    ArrayList<TipoCuenta> tipoCuenta = model.getTipoCuenta();
%>


<div class="limiter"> 

    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <center><h1> Registro de Cuenta </h1>
                    <form action="/ProyectoBanco/registrar/cuenta">
                        <label>Cliente:</label><input type="text" name="usuario" value="" placeholder="id" id="inputTextR" /><br>
                        <label>Límite de Transferencia: </label><input type="text" name="limiteTran" value="" placeholder="Limite" id="inputTextR" /><br>
                        <label>Tipo de Moneda <select name="moneda">
                                                            <option value="0">Seleccione una moneda</option>
                                                            <%for(int i = 0; i< monedaList.size();i++){%>
                                                                <option value="<%= monedaList.get(i).getNombre()%>"><%= monedaList.get(i).getSimbolo()%>  <%=  monedaList.get(i).getDescripcion()%></option>
                                                            <%}%>
                                                        </select><br>
                        <label>Tipo de Cuenta <select name="tipoCuenta">
                                                            <option value="0">Seleccione el tipo de Cuenta</option>
                                                            <%for(int i = 0; i< tipoCuenta.size();i++){%>
                                                                <option value="<%= tipoCuenta.get(i).getIdTipoCuenta()%>"><%= tipoCuenta.get(i).getDescripción()%> <%=  tipoCuenta.get(i).getTasaInterés()%></option>
                                                            <%}%>
                                                        </select><br>

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
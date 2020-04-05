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
    ArrayList<Moneda> monedaList = model.getTipoMoneda();
    ArrayList<TipoCuenta> tipoCuenta = model.getTipoCuenta();
%>


<div class="conteiner"> 
    <h1> Registro de Cuenta </h1>
    <form action="/ProyectoBanco/registrar/cuenta">
        <label>Cliente:</label><br><input type="text" name="usuario" value="" placeholder="id" id="inputTextR" class="<%if (request.getAttribute("errorCliente") != null) {
                out.print((String) request.getAttribute("errorCliente"));
            }%>"/><br>
        <label>Límite de Transferencia: </label><br><input type="text" name="limiteTran" value="" placeholder="Limite" id="inputTextR" class="<%if (request.getAttribute("errorLimite") != null) {
                out.print((String) request.getAttribute("errorLimite"));
            }%>"/><br>

        <label>Tipo de Moneda: </label>
        <select name="moneda" id="inputSelect" class="<%if (request.getAttribute("errorMoneda") != null) {
                    out.print((String) request.getAttribute("errorMoneda"));
                }%>">
            <option value="0" >
                Seleccione una moneda</option>
                <%for (int i = 0; i < monedaList.size(); i++) {%>
            <option value="<%= monedaList.get(i).getNombre()%>"><%= monedaList.get(i).getSimbolo()%>  <%=  monedaList.get(i).getDescripcion()%></option>
            <%}%>
        </select><br>
        <label>Tipo de Cuenta: </label>

        <select  name="tipoCuenta" id="inputSelect" class="<%if (request.getAttribute("errorCuenta") != null) {
                            out.print((String) request.getAttribute("errorCuenta"));
                        }%>">
            <option id="option" value="0">
                Seleccione el tipo de Cuenta</option>
                <%for (int i = 0; i < tipoCuenta.size(); i++) {%>
            <option value="<%= tipoCuenta.get(i).getIdTipoCuenta()%>"><%= tipoCuenta.get(i).getDescripción()%> <%=  tipoCuenta.get(i).getTasaInterés()%></option>
            <%}%>
        </select><br>
        <input type="submit" value="Registrar" class="btn-confirm"/><br>
    </form>

    <br><br>
</div>  
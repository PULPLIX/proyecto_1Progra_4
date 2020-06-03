
<%@page import="banco.logica.Cuenta"%>
<%@page import="banco.logica.TipoCuenta"%>
<%@page import="banco.logica.Moneda"%>
<%@page import="java.util.ArrayList"%>

<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/acreditarIntereses.css">
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/infoPersonal.css">
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">

<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cajero.acreditarIntereses.Model"%>

<%
    banco.presentacion.cajero.acreditarIntereses.Model model = (Model) request.getAttribute("model");
    ArrayList<Moneda> monedaList = model.getListaMonedas();
%>

<div class="limiter"> 
    <center>

        <% if ((String) request.getAttribute("mensaje") != null) {
            out.print("<center><p style=\"color:green; font-size: 30px;\">" + (String) request.getAttribute("mensaje") + "</p></center>");
        } %>
        <% if ((String) request.getAttribute("error") != null) {
            out.print("<center><p style=\"color:red; font-size: 30px;\">" + (String) request.getAttribute("estadoTransferencia") + "</p></center>");
        } %>
        <h1> Acreditar Intereses </h1>


        <div class="container-table100">
            <div class="wrap-table100">
                <div class="table100 ver1 m-b-110">
                    <table data-vertable="ver1">
                        <thead>
                            <tr class="row100 head">
                                <th class="column100 column2" data-column="column2">Simbolo</th>
                                <th class="column100 column1" data-column="column1">Nombre de moneda </th>                               
                                <th class="column100 column3" data-column="column3">Tipo de cambio de compra</th>
                                <th class="column100 column4" data-column="column4">Tipo de cambio de venta</th>
                                <th class="column100 column4" data-column="column4">Tasa de interes</th>

                            </tr>
                        </thead>
                        <tbody>
                            <% if (monedaList != null) {%>
                            <% for (Moneda m : monedaList) {%>
                            <tr  class="row100">
                                <td class="column100 column1" data-column="column2"><%=m.getSimbolo()%></td>
                                <td class="column100 column1" data-column="column1"><%=m.getDescripcion()%> </td>                                 
                                <td class="column100 column1" data-column="column4"><%=m.getTipoCambioCompra()%></td>
                                <td class="column100 column1" data-column="column5"><%=m.getTipoCambioVenta()%></td>
                                <td class="column100 column1" data-column="column6"><%=m.getTasaIntereses()%></td>
                            </tr>
                            <%}
                                }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <br><br>
        <form action="/ProyectoBanco/presentation/cajero/acreditarIntereses/acreditar">     
            <br>
            <input type="submit" value="Acreditar Intereses" class="btn-confirm"/><br>
        </form>

        <br><br>
    </center>
</div>
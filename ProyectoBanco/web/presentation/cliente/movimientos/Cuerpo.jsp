

<%@page import="banco.logica.Transferencia"%>
<%@page import="banco.logica.Movimiento"%>
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/movimientos.css">

<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cliente.movimientos.Model"%>

<%
    banco.presentacion.cliente.movimientos.Model model = (Model) request.getAttribute("model");
    List<Movimiento> movimientos = model.getMovimientos();
    List<Transferencia> transferencia = model.getTransferencia();
%>

<div class="limiter"> 
    <center> <h2> Movimientos </h2></center>

    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <table data-vertable="ver1">
                    <thead>
                        <tr class="row100 head">
                            <th class="column100 column1" data-column="column1">Id movimiento </th>
                            <th class="column100 column2" data-column="column2">Monto</th>
                            <th class="column100 column3" data-column="column3">Fecha</th>
                            <th class="column100 column4" data-column="column4">Tipo Moviento</th>
                            <th class="column100 column5" data-column="column5">Numero de cuenta</th>

                            

                        </tr>
                    </thead>
                    <tbody>
                        <% for (Movimiento m : movimientos) {%>
                        <tr  class="row100">
                            <td class="column100 column1" data-column="column1"><%=m.getId_movimiento()%> </td> 
                            <td class="column100 column1" data-column="column2"><%=m.getMonto()%></td>
                            <td class="column100 column1" data-column="column3"><%=m.getFecha()%></td>
                            <td class="column100 column1" data-column="column4"><%if(m.getAplicado() ==1){out.print("<p style=\"color:green;\">Deposito</p>");}else if(m.getAplicado() ==2){out.print("<p style= \"color:red;\">Retiro</p>");}%></td>
                            <td class="column100 column1" data-column="column5"><%=m.getCuenta().getNumCuenta()%></td>              
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <center> <h2> Transferencias </h2></center>               
                <table data-vertable="ver1">
                    <thead>
                        <tr class="row100 head">
                            <th class="column100 column1" data-column="column1">Id transferencia </th>
                            <th class="column100 column2" data-column="column2">Monto</th>
                            <th class="column100 column3" data-column="column3">Fecha</th>
                            <th class="column100 column5" data-column="column5">Numero de cuenta Origen</th>
                            <th class="column100 column5" data-column="column5">Numero de cuenta Destino</th>

                        </tr>
                    </thead>
                    <tbody>
                        <% for (Transferencia t : transferencia) {%>
                        <tr  class="row100">
                            <td class="column100 column1" data-column="column1"><%=t.getId_transferencia()%> </td> 
                            <td class="column100 column1" data-column="column2"><%=t.getMonto()%></td>
                            <td class="column100 column1" data-column="column3"><%=t.getFecha()%></td>
                            <td class="column100 column1" data-column="column5"><%=t.getCuenta().getNumCuenta()%></td>
                            <td class="column100 column1" data-column="column6"><%=t.getCuenta_Destino().getNumCuenta()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br><br><br><br><br><br><br><br>


            </div>
        </div>
    </div>




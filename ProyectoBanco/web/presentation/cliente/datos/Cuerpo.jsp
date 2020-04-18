<%@page import="java.util.List"%>
<%@page import="banco.logica.Cuenta"%>
<%@page import="banco.presentacion.cliente.cuentas.Model"%>
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/table.css">

<%
    Model model = (Model) request.getAttribute("model");
    List<Cuenta> cuentas = model.getCuentas();
%>


<div class="limiter">
    <center>
        <h2>MIS CUENTAS</h2>

        <div class="container-table100">
            <div class="wrap-table100">
                <div class="table100 ver1 m-b-110">
                    <table data-vertable="ver1">
                        <thead>
                            <tr class="row100 head">
                                <th class="column100 column1" data-column="column1">Id cuenta </th>
                                <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                <th class="column100 column3" data-column="column3">Moneda</th>
                                <th class="column100 column4" data-column="column4">Propietario</th>
                                <th class="column100 column5" data-column="column5"> Límite de transferencia</th>
                                <th class="column100 column6" data-column="column6">Saldo Neto</th>
                                <th class="column100 column7" data-column="column7"><img src="/ProyectoBanco/images/menuIcon/debito.png"  id="subIcon" style="height: 30px;"> Ver movimientos</th>

                            </tr>
                        </thead>
                        <tbody>
                            <% for (Cuenta c : cuentas) {%>
                            <tr  class="row100">
                                <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripción()%></td>
                                <td class="column100 column1" data-column="column3"><%=c.getMonedaNombre().getSimbolo() + " " + c.getMonedaNombre().getDescripcion()%></td>
                                <td class="column100 column1" data-column="column4"><%=c.getClienteIdCliente().getNombre() + " " + c.getClienteIdCliente().getApellidos()%></td>
                                <td class="column100 column1" data-column="column5"><%=c.getLimiteTransferenciaDiaria()%></td>
                                <td class="column100 column1" data-column="column6"><%=c.getSaldoFinal()%></td>
                                <td class="column100 column1" data-column="column7"> 
                                    <a href="/ProyectoBanco/presentation/login/movimientos/show?idCuenta=<%=c.getNumCuenta()%>" id="textoSub">Mis movimientos</a>
                                </td>
                            </tr>

                            <%}%>
                        </tbody>

                    </table>

                    <br><br>
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
    </center>
</div>



<br><br>

<div class="cuerpo-caja pie">
    <h2>Banco Central</h2>
</div>

<!-- SECCION DE COPYRIGHT
        ============================================ -->
<div class="cuerpo-caja copyright">
    <h3> Copyright ©2020 All rights reserved | Iván Chinchilla y David Aguilar</h3>
</div>

</div>

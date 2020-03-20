<%@page import="java.util.List"%>
<%@page import="banco.logica.Cuenta"%>
<%@page import="banco.presentacion.cliente.cuentas.Model"%>

<%
    Model model = (Model) request.getAttribute("model");
    List<Cuenta> cuentas = model.getCuentas();
%>

<div class="cuerpo">

    <div class="cuerpo-caja cuerpo-cabeza">
        <center>
            <h2>MIS CUENTAS</h2>
        </center>
    </div>

    <div class="cuerpo-caja cuerpo-derecho">
        <form action="">
            <table border="1" class="table" width="500" align="center" >
                <tr bgcolor="skyblue">
                    <th>Id cuenta </th>
                    <th>Tipo de Cuenta</th>
                    <th>Propietario</th>
                    <th>Límite de transferencia</th>
                    <th>Saldo Neto</th>
                </tr>
                </thead>
                <tbody>
                    <% for (Cuenta c : cuentas) {%>
                    <tr> 
                        <td><%=c.getNumCuenta()%> </td> 
                        <td><%=c.getIdTipoCuenta().getDescripción()%></td>
                        <td><%=c.getClienteIdCliente().getNombre()%></td>
                        <td><%=c.getLimiteTransferenciaDiaria()%></td>
                        <td><%=c.getSaldoFinal()%></td>
                    </tr>

                    <%}%>
                </tbody>
            </table>
        </form>

        <br><br>
        <br><br>
        <br><br>
        <br><br>
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

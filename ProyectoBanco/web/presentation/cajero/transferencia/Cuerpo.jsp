<%-- 
    Document   : Cuerpo
    Created on : 27/03/2020, 05:25:37 PM
    Author     : Iván
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="banco.logica.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>


<div class="limiter"> 
    <center> <h2> Transferencia </h2><br><br></center>

    <% if ((String) request.getAttribute("mensaje") != null) {
            out.print((String) request.getAttribute("mensaje") + "\n");
        } %>
    <form action="/ProyectoBanco/cajero/transferencia/buscar" method="post">

        <center>                   
            <h2>Origen de los fondos <br><br>Buscar por número de cédula del cliente</h2>

            <input type="text" name="clienteABuscar" value="<% if ((String) request.getAttribute("clienteBuscar") != null) {
                    out.print((String) request.getAttribute("clienteBuscar"));
                } else {
                    out.print("Cliente");
                }%>" >

            <input type="submit" value="Buscar" /><br><br>

            <div class="container-table100">
                <div class="wrap-table100">
                    <div class="table100 ver1 m-b-110">
                        <table data-vertable="ver1">
                            <thead>
                                <tr class="row100 head">
                                    <th class="column100 column1" data-column="column1">Id cliente </th>
                                    <th class="column100 column2" data-column="column2">Apellidos</th>
                                    <th class="column100 column3" data-column="column3">Nombre</th>
                                    <th class="column100 column4" data-column="column4">Telefono</th>

                                </tr>
                            </thead>
                            <tbody>
                                <%if (clienteBuscar != null) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=clienteBuscar.getIdCliente()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=clienteBuscar.getApellidos()%></td>
                                    <td class="column100 column1" data-column="column3"><%=clienteBuscar.getNombre()%></td>
                                    <td class="column100 column1" data-column="column4"><%=clienteBuscar.getTelefono()%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                        <br><br>
                        <h2>Buscar por número de cuenta </h2>
                        <br>
                        <input type="text" name="cuentaABuscar" value="<% if ((String) request.getAttribute("cuentaSeleccionada") != null) {
                                out.print((String) request.getAttribute("cuentaSeleccionada"));
                            } else {
                                out.print("1235");
                            }%>" >

                        <input type="submit" value="Buscar" /><br><br>
                        <br>
                        <table data-vertable="ver1">
                            <thead>
                                <tr class="row100 head">
                                    <th class="column100 column1" data-column="column1">Id cuenta </th>
                                    <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                    <th class="column100 column2" data-column="column2">Nombre Cliente</th>
                                    <th class="column100 column4" data-column="column4"> Límite de transferencia</th>
                                    <th class="column100 column5" data-column="column5">Saldo Neto</th>
                                    <th class="column100 column6" data-column="column5">Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (cuentas != null) {%>
                                <% for (Cuenta c : cuentas) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripción()%></td>
                                    <td class="column100 column1" data-column="column3"><%=c.getClienteIdCliente().getNombre() + " " + c.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column4"><%=c.getLimiteTransferenciaDiaria()%></td>
                                    <td class="column100 column1" data-column="column5"><%=c.getSaldoFinal()%></td>
                                    <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaO=<%=c.getNumCuenta()%>">Seleccionar</a></td>
                                </tr>

                                <% }
                                } else if (seleccionada != null) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=seleccionada.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=seleccionada.getIdTipoCuenta().getDescripción()%></td>
                                    <td class="column100 column1" data-column="column3"><%=seleccionada.getClienteIdCliente().getNombre() + " " + seleccionada.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column4"><%=seleccionada.getLimiteTransferenciaDiaria()%></td>
                                    <td class="column100 column1" data-column="column5"><%=seleccionada.getSaldoFinal()%></td>
                                    <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaO=<%=seleccionada.getNumCuenta()%>">Seleccionar</a></td>

                                </tr>
                                <%}%>
                            </tbody>

                        </table>

                        <br><br>
                        <h2>Destino de los fondos <br><br>Buscar por número de cédula del cliente</h2>

                        <input type="text" name="clienteABuscarDestino" value="<% if ((String) request.getAttribute("clienteBuscarDestino") != null) {
                                out.print((String) request.getAttribute("clienteBuscarDestino"));
                            } else {
                                out.print("Cliente");
                            }%>" >

                        <input type="submit" value="Buscar" /><br><br>

                        <div class="container-table100">
                            <div class="wrap-table100">
                                <div class="table100 ver1 m-b-110">
                                    <table data-vertable="ver1">
                                        <thead>
                                            <tr class="row100 head">
                                                <th class="column100 column1" data-column="column1">Id cliente </th>
                                                <th class="column100 column2" data-column="column2">Apellidos</th>
                                                <th class="column100 column3" data-column="column3">Nombre</th>
                                                <th class="column100 column4" data-column="column4">Telefono</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%if (clienteBuscarDestino != null) {%>
                                            <tr  class="row100">
                                                <td class="column100 column1" data-column="column1"><%=clienteBuscarDestino.getIdCliente()%> </td> 
                                                <td class="column100 column1" data-column="column2"><%=clienteBuscarDestino.getApellidos()%></td>
                                                <td class="column100 column1" data-column="column3"><%=clienteBuscarDestino.getNombre()%></td>
                                                <td class="column100 column1" data-column="column4"><%=clienteBuscarDestino.getTelefono()%></td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                    <br><br>
                                    <h2>Buscar por número de cuenta </h2>
                                    <br>
                                    <input type="text" name="cuentaABuscarDestino" value="<% if ((String) request.getAttribute("cuentaSeleccionadaDestino") != null) {
                                            out.print((String) request.getAttribute("cuentaSeleccionadaDestino"));
                                        } else {
                                            out.print("1235");
                                        }%>" >

                                    <input type="submit" value="Buscar" /><br><br>
                                    <br>
                                    <table data-vertable="ver1">
                                        <thead>
                                            <tr class="row100 head">
                                                <th class="column100 column1" data-column="column1">Id cuenta </th>
                                                <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                                <th class="column100 column2" data-column="column2">Nombre Cliente</th>
                                                <th class="column100 column4" data-column="column4"> Límite de transferencia</th>
                                                <th class="column100 column5" data-column="column5">Saldo Neto</th>
                                                <th class="column100 column6" data-column="column5">Seleccionar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (cuentasDestino != null) {%>
                                            <% for (Cuenta c : cuentasDestino) {%>
                                            <tr  class="row100">
                                                <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                                <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripción()%></td>
                                                <td class="column100 column1" data-column="column3"><%=c.getClienteIdCliente().getNombre() + " " + c.getClienteIdCliente().getApellidos()%></td>
                                                <td class="column100 column1" data-column="column4"><%=c.getLimiteTransferenciaDiaria()%></td>
                                                <td class="column100 column1" data-column="column5"><%=c.getSaldoFinal()%></td>
                                                <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaD=<%=c.getNumCuenta()%>">Seleccionar</a></td>
                                            </tr>

                                            <% }
                                            } else if (seleccionadaDestino != null) {%>
                                            <tr  class="row100">
                                                <td class="column100 column1" data-column="column1"><%=seleccionadaDestino.getNumCuenta()%> </td> 
                                                <td class="column100 column1" data-column="column2"><%=seleccionadaDestino.getIdTipoCuenta().getDescripción()%></td>
                                                <td class="column100 column1" data-column="column3"><%=seleccionadaDestino.getClienteIdCliente().getNombre() + " " + seleccionada.getClienteIdCliente().getApellidos()%></td>
                                                <td class="column100 column1" data-column="column4"><%=seleccionadaDestino.getLimiteTransferenciaDiaria()%></td>
                                                <td class="column100 column1" data-column="column5"><%=seleccionadaDestino.getSaldoFinal()%></td>
                                                <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaD=<%=seleccionada.getNumCuenta()%>">Seleccionar</a></td>

                                            </tr>
                                            <%}%>
                                        </tbody>

                                    </table>

                                    <br><br>
                                </div>
                            </div>
                        </div>
                        </center>
                        </form>

                        <h2>Cuenta de origen</h2> 
                        <input type="text" name="cuentaOrigenSelect" value="<% if (model.getSeleccionada() != null) {
                                out.print((String) String.valueOf(model.getSeleccionada().getNumCuenta()));
                            } else {
                                out.print("1235");
                            }%>"  id="inputText" readonly/>
                        
                        <h2>Cuenta de destino</h2> 
                        <input type="text" name="cuentaDestinoSelect" value="<% if (model.getSeleccionadaDestino() != null) {
                                out.print((String) String.valueOf(model.getSeleccionada().getNumCuenta()));
                            } else {
                                out.print("1235");
                            }%>"  id="inputText" readonly/>
                        <h2>Digite el monto</h2>
                        <input type="text" name="monto" value="<% if ((String) request.getAttribute("monto") != null) {
                                out.print((String) request.getAttribute("monto"));
                            } else {
                                out.print("000.00");
                            }%>">
                        <input type="submit" value="Ingresar Datos" />
                        <br>Si todos los datos están correctos, puede ingresarlos en el sistema y luego se confirmará el depósito.
                        &nbsp


</div>  
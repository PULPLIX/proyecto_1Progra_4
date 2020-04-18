<%-- 
    Document   : Cuerpo
    Created on : 27/03/2020, 05:25:37 PM
    Author     : Iv�n
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="banco.logica.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>

<%
    banco.presentacion.cajero.transferencia.Model model = (banco.presentacion.cajero.transferencia.Model) request.getAttribute("model");
    Cliente clienteBuscar = model.getClienteBuscar();
    ArrayList<Cuenta> cuentas = model.getCuentas();
    Cuenta seleccionada = model.getSeleccionada();

    Cliente clienteBuscarDestino = model.getClienteBuscarDestino();
    ArrayList<Cuenta> cuentasDestino = model.getCuentasDestino();
    Cuenta seleccionadaDestino = model.getSeleccionadaDestino();
    double monto = model.getMonto();
%>
<div class="limiter"> 
    <center> <h2> Transferencia </h2><br><br></center>

    <% if ((String) request.getAttribute("mensaje") != null) {
 out.print("<center><p style=\"color:green; font-size: 30px;\">"+(String) request.getAttribute("mensaje")+"</p></center>");        } %>
    <% if ((String) request.getAttribute("estadoTransferencia") != null) {
                    out.print("<center><p style=\"color:red; font-size: 30px;\">"+(String) request.getAttribute("estadoTransferencia")+"</p></center>");
                } %>
                
    <form action="/ProyectoBanco/cajero/transferencia/buscar" method="post">

        <center>              
            <%if (request.getAttribute("errorIguales") != null) {%><p style="color:red; font-size: 30px;"><%=(String) request.getAttribute("errorIguales")%> <br></p><%}%>

            <h2>Origen de los fondos <br><br>Buscar por n�mero de c�dula del cliente</h2>

            <%if (request.getAttribute("errorClienteO") != null) {%><label style="color:red;">*El cliente Origen ingresado no existe <br></label><%}%>
            <input type="text" name="clienteABuscar" value="<% if (clienteBuscar != null) {
                    out.print(clienteBuscar.getIdCliente());
                } %>" placeholder="Cliente Origen" class="<%if (request.getAttribute("errorClienteO") != null) {
                        out.print((String) request.getAttribute("errorClienteO"));
                    }%>">

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
                        <h2>Buscar por n�mero de cuenta </h2>
                        <br>
                        <%if (request.getAttribute("errorOrigen") != null) {%><label style="color:red;">*La cuenta Origen ingresada no existe <br></label><%}%>
                        <input type="text" name="cuentaABuscar" value="<% if (seleccionada != null) {
                                out.print(seleccionada.getNumCuenta());
                            } %>" placeholder="Cuenta Origen" class="<%if (request.getAttribute("errorOrigen") != null) {
                                    out.print((String) request.getAttribute("errorOrigen"));
                                }%>">

                        <input type="submit" value="Buscar" /><br><br>
                        <br>
                        <table data-vertable="ver1">
                            <thead>
                                <tr class="row100 head">
                                    <th class="column100 column1" data-column="column1">Id cuenta </th>
                                    <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                    <th class="column100 column3" data-column="column3">Tipo de Moneda</th>
                                    <th class="column100 column4" data-column="column4">Nombre Cliente</th>
                                    <th class="column100 column5" data-column="column5"> L�mite de transferencia</th>
                                    <th class="column100 column6" data-column="column6">Saldo Neto</th>
                                    <th class="column100 column7" data-column="column7">Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (cuentas != null) {%>
                                <% for (Cuenta c : cuentas) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripci�n()%></td>
                                    <td class="column100 column1" data-column="column3"><%=c.getMonedaNombre().getSimbolo() + " " + c.getMonedaNombre().getDescripcion()%></td>
                                    <td class="column100 column1" data-column="column4"><%=c.getClienteIdCliente().getNombre() + " " + c.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column5"><%=c.getLimiteTransferenciaDiaria()%></td>
                                    <td class="column100 column1" data-column="column6"><%=c.getSaldoFinal()%></td>
                                    <td class="column100 column1" data-column="column7"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaO=<%=c.getNumCuenta()%><%if (seleccionadaDestino != null) {%>&cuentaDestinoSelect=<%=seleccionadaDestino.getNumCuenta()%><%}%>">Seleccionar</a></td>
                                </tr>

                                <% }
                                } else if (seleccionada != null) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=seleccionada.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=seleccionada.getIdTipoCuenta().getDescripci�n()%></td>
                                    <td class="column100 column1" data-column="column3"><%=seleccionada.getMonedaNombre().getSimbolo() + " " + seleccionada.getMonedaNombre().getDescripcion()%></td>
                                    <td class="column100 column1" data-column="column3"><%=seleccionada.getClienteIdCliente().getNombre() + " " + seleccionada.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column4"><%=seleccionada.getLimiteTransferenciaDiaria()%></td>
                                    <td class="column100 column1" data-column="column5"><%=seleccionada.getSaldoFinal()%></td>
                                    <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaO=<%=seleccionada.getNumCuenta()%><%if (seleccionadaDestino != null) {%>&cuentaDestinoSelect=<%=seleccionadaDestino.getNumCuenta()%><%}%>">Seleccionar</a></td>

                                </tr>
                                <%}%>
                            </tbody>

                        </table>
                        <br><br>
                        <h2>Destino de los fondos <br>
                            <br>Buscar por n�mero de c�dula del cliente</h2>
                        <%if (request.getAttribute("errorClienteD") != null) {%><label style="color:red;">*El cliente Destino ingresado no existe <br></label><%}%>
                        <input type="text" name="clienteABuscarDestino" value="<% if (clienteBuscarDestino != null) {
                                out.print(clienteBuscarDestino.getIdCliente());
                            }%>" placeholder="Cliente Destino" class="<%if (request.getAttribute("errorClienteD") != null) {
                                    out.print((String) request.getAttribute("errorClienteD"));
                                }%>">

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
                                    <h2>Buscar por n�mero de cuenta </h2>
                                    <br>
                                    <%if (request.getAttribute("errorDestino") != null) {%><label style="color:red;">*La cuenta Destino ingresada no existe<br></label><%}%>

                                    <input type="text" name="cuentaABuscarDestino" value="<% if (seleccionadaDestino != null) {
                                            out.print(seleccionadaDestino.getNumCuenta());
                                        } %>" placeholder="Cuenta Destino" class="<%if ((String) request.getAttribute("errorDestino") != null) {
                                                out.print((String) request.getAttribute("errorDestino"));
                                            }%>">

                                    <input type="submit" value="Buscar" /><br><br>
                                    <br>
                                    <table data-vertable="ver1">
                                        <thead>
                                            <tr class="row100 head">
                                                <th class="column100 column1" data-column="column1">Id cuenta </th>
                                                <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                                <th class="column100 column2" data-column="column2">Tipo de Moneda</th>
                                                <th class="column100 column2" data-column="column2">Nombre Cliente</th>
                                                <th class="column100 column4" data-column="column4"> L�mite de transferencia</th>
                                                <th class="column100 column5" data-column="column5">Saldo Neto</th>
                                                <th class="column100 column6" data-column="column5">Seleccionar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (cuentasDestino != null) {%>
                                            <% for (Cuenta c : cuentasDestino) {%>
                                            <tr  class="row100">
                                                <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                                <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripci�n()%></td>
                                                <td class="column100 column1" data-column="column3"><%=c.getMonedaNombre().getSimbolo() + " " + c.getMonedaNombre().getDescripcion()%></td>
                                                <td class="column100 column1" data-column="column3"><%=c.getClienteIdCliente().getNombre() + " " + c.getClienteIdCliente().getApellidos()%></td>
                                                <td class="column100 column1" data-column="column4"><%=c.getLimiteTransferenciaDiaria()%></td>
                                                <td class="column100 column1" data-column="column5"><%=c.getSaldoFinal()%></td>
                                                <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaD=<%=c.getNumCuenta()%><%if (seleccionada != null) {%>&cuentaOrigenSelect=<%=seleccionada.getNumCuenta()%><%}%>">Seleccionar</a></td>
                                            </tr>

                                            <% }
                                            } else if (seleccionadaDestino != null) {%>
                                            <tr  class="row100">
                                                <td class="column100 column1" data-column="column1"><%=seleccionadaDestino.getNumCuenta()%> </td> 
                                                <td class="column100 column1" data-column="column2"><%=seleccionadaDestino.getIdTipoCuenta().getDescripci�n()%></td>
                                                <td class="column100 column1" data-column="column3"><%=seleccionadaDestino.getMonedaNombre().getSimbolo() + " " + seleccionadaDestino.getMonedaNombre().getDescripcion()%></td>
                                                <td class="column100 column1" data-column="column3"><%=seleccionadaDestino.getClienteIdCliente().getNombre() + " " + seleccionadaDestino.getClienteIdCliente().getApellidos()%></td>
                                                <td class="column100 column1" data-column="column4"><%=seleccionadaDestino.getLimiteTransferenciaDiaria()%></td>
                                                <td class="column100 column1" data-column="column5"><%=seleccionadaDestino.getSaldoFinal()%></td>
                                                <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/transferencia/seleccionar?idCuentaD=<%=seleccionadaDestino.getNumCuenta()%><%if (seleccionada != null) {%>&cuentaOrigenSelect=<%=seleccionada.getNumCuenta()%><%}%>">Seleccionar</a></td>

                                            </tr>
                                            <%}%>
                                        </tbody>

                                    </table>

                                    <br><br>
                                </div>
                            </div>
                        </div>
                        </center>
                        <h2>Cuenta de origen</h2> 
                        <input type="text" name="cuentaOrigenSelect" value="<% if (seleccionada != null) {
                                out.print((String) String.valueOf(seleccionada.getNumCuenta()));
                            } %>"  id="inputText" readonly/>

                        <h2>Cuenta de destino</h2> 
                        <input type="text" name="cuentaDestinoSelect" value="<% if (seleccionadaDestino != null) {
                                out.print((String) String.valueOf(seleccionadaDestino.getNumCuenta()));
                            } %>"  id="inputText" readonly/>
                        <h2>Digite el monto</h2>
                        <input type="text" name="monto" value="<% if (monto != 0) {
                                out.print(monto);
                            }%>">
                        <br><input type="submit" value="Ingresar Datos" />
                        <br>Si todos los datos est�n correctos, puede ingresarlos en el sistema y luego se confirmar� el dep�sito.
                        &nbsp
                        </form>




                    </div>  
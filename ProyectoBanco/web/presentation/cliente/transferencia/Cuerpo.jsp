<%@page import="java.util.List"%>
<%@page import="banco.logica.Cuenta"%>
<%@page import="banco.presentacion.cliente.transferencia.Model"%>

<%
    banco.presentacion.cliente.transferencia.Model model = (Model) request.getAttribute("model");
    Cuenta cuentasOrigen = model.getSeleccionado();
    Cuenta cuentasDestino = model.getaTransferir();

%>


<div class="limiter">

    <p id="parrafo"><br><br>
        Recuerde que desde la plataforma virtual solamente se pueden hacer transferencias  <br>
        a cuentas propias, si desea transferir a cuentas agenas dirijase a la sucursal más cercana.
    </p><br><br>
    <form action="/ProyectoBanco/transferir/busca/cuentas" method="post">

        <center>
            <h2>Elige la cuenta de origen de los fondos</h2>

            <input type="text" name="cuentaOrigen" value="<% if ((String) request.getAttribute("numCuentaO") != null) {
                    out.print((String) request.getAttribute("numCuentaO"));
                } else {
                    out.print("Cuenta Origen");
                }%>" >
            <input type="submit" value="Buscar" /><br><br>

            <div class="container-table100">
                <div class="wrap-table100">
                    <div class="table100 ver1 m-b-110">
                        <table data-vertable="ver1">
                            <thead>
                                <tr class="row100 head">
                                    <th class="column100 column1" data-column="column1">Id cuenta </th>
                                    <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                    <th class="column100 column3" data-column="column3">Propietario</th>
                                    <th class="column100 column4" data-column="column4"> Límite de transferencia</th>
                                    <th class="column100 column5" data-column="column5">Saldo Neto</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if (cuentasOrigen != null) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=cuentasOrigen.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=cuentasOrigen.getIdTipoCuenta().getDescripción()%></td>
                                    <td class="column100 column1" data-column="column3"><%=cuentasOrigen.getClienteIdCliente().getNombre() + " " + cuentasOrigen.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column4"><%=cuentasOrigen.getLimiteTransferenciaDiaria()%></td>
                                    <td class="column100 column1" data-column="column5"><%=cuentasOrigen.getSaldoFinal()%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
        </center>
        <center>
            <br><br><br><br><br><h2>Elige a la que desea hacer la transferencia</h2><br>

            <input type="text" name="cuentaDestino" value="<% if ((String) request.getAttribute("numCuentaD") != null) {
                    out.print((String) request.getAttribute("numCuentaD"));
                } else {
                    out.print("Cuenta Destino");
                }%>" > 
            <input type="submit" value="Buscar" />

            <br><br>

            <div class="container-table100">
                <div class="wrap-table100">
                    <div class="table100 ver1 m-b-110">
                        <table data-vertable="ver1">
                            <thead>
                                <tr class="row100 head">
                                    <th class="column100 column1" data-column="column1">Id cuenta </th>
                                    <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                    <th class="column100 column3" data-column="column3">Propietario</th>
                                    <th class="column100 column4" data-column="column4"> Límite de transferencia</th>
                                    <th class="column100 column5" data-column="column5">Saldo Neto</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr  class="row100">
                                    <%if (cuentasDestino != null) {%>
                                    <td class="column100 column1" data-column="column1"><%=cuentasDestino.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=cuentasDestino.getIdTipoCuenta().getDescripción()%></td>
                                    <td class="column100 column1" data-column="column3"><%=cuentasDestino.getClienteIdCliente().getNombre() + " " + cuentasDestino.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column4"><%=cuentasDestino.getLimiteTransferenciaDiaria()%></td>
                                    <td class="column100 column1" data-column="column5"><%=cuentasDestino.getSaldoFinal()%></td>
                                    <%}%>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <br><br><h2>Inserte el monto: </h2>
            <p><i>El monto debe ser menor o igual a los fondos <br>pertenecientes a la cuenta origen</i></p>
            <input type="text" name="monto" value="<% if ((String) request.getAttribute("monto") != null) {
                    out.print((String) request.getAttribute("monto"));
                } else {
                    out.print("Monto");
                }%>"  />
            <input type="submit" value="Ingresar" />
        </center>
    </form>
    <br><br>
    <br><br>

</div>



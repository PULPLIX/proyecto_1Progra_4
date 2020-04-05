<%@page import="java.util.List"%>
<%@page import="banco.logica.Cuenta"%>
<%@page import="banco.presentacion.cliente.transferencia.Model"%>
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">


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
            <%if (request.getAttribute("noExisteOrigen") != null) {%><label style="color:red;">La cuenta ingresada no existe o no le pertence<br></label><%}%>
            <input type="text" name="cuentaOrigen" value="<% if ((String) request.getAttribute("numCuentaO") != null) {
                    out.print((String) request.getAttribute("numCuentaO"));
                                   }%>" placeholder="origen" class="<%if (request.getAttribute("errorOrigen") != null) {
                                                              out.print((String) request.getAttribute("errorOrigen"));
                                                          }%>"/>
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

            <br><br>
            <%if (request.getAttribute("iguales") != null) {%><label style="color:red; font-size: 30px;">Has escogido la misma cuenta dos veces<br></label><%}%>
            <br><br><br><h2>Elige a la que desea hacer la transferencia</h2><br>
            <%if (request.getAttribute("noExisteDestino") != null) {%><label style="color:red;">La cuenta ingresada no existe<br></label><%}%>

            <input type="text" name="cuentaDestino" value="<% if ((String) request.getAttribute("numCuentaD") != null) {
                    out.print((String) request.getAttribute("numCuentaD"));
                } %>"  placeholder=" Destino" class="<%if (request.getAttribute("errorDestino") != null) {
                        out.print((String) request.getAttribute("errorDestino"));
                    }%>"/> 
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
            <%if (request.getAttribute("Excede") != null) {%><label style="color:red;">El monto es mayor al limite de transferencia o al de los fondos de la cuenta de origen<br></label><%}%>

            <input type="text" name="monto" value="<% if ((String) request.getAttribute("monto") != null) {
                    out.print((String) request.getAttribute("monto"));
                                   }%>" placeholder="Monto" class="<%if (request.getAttribute("errorMonto") != null) {
                                                              out.print((String) request.getAttribute("errorMonto"));
                                                          }%>"/>
            <input type="submit" value="Ingresar" />
        </center>
    </form>
    <br><br>
    <br><br>

</div>



<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="banco.logica.Cuenta"%>
<%@page import="banco.presentacion.cliente.transferencia.Model"%>
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">


<%
    banco.presentacion.cliente.transferencia.Model model = (Model) request.getAttribute("model");
    List<Cuenta> cuentasOrigen = model.getCuentas();
    ArrayList<Cuenta> cuentasDestino = model.getFavoritas();

    Cuenta seleccionadaOrigen = model.getSeleccionado();
    Cuenta seleccionadaDestino = model.getaTransferir();
%>


<div class="limiter">

    <p id="parrafo"><br><br>
        Recuerde que desde la plataforma virtual solamente se pueden hacer transferencias  <br>
        a cuentas propias, si desea transferir a cuentas agenas dirijase a la sucursal más cercana.
    </p><br><br>

    <form action="/ProyectoBanco/transferir/ingresar" method="post">

        <center>
            <h1>Transferencias Remotas</h1>
            <% if ((String) request.getAttribute("mensaje") != null) {
                    out.print("<center><p style=\"color:green; font-size: 30px;\">" + (String) request.getAttribute("mensaje") + "</p></center>");
                } %>
            <% if ((String) request.getAttribute("estadoTransferencia") != null) {
                    out.print("<center><p style=\"color:red; font-size: 30px;\">" + (String) request.getAttribute("estadoTransferencia") + "</p></center>");
                } %>
            <h2>Elige la cuenta de origen de los fondos</h2>

            <div class="container-table100">
                <div class="wrap-table100">
                    <div class="table100 ver1 m-b-110">
                        <table data-vertable="ver1">
                            <thead>
                                <tr class="row100 head">
                                    <th class="column100 column1" data-column="column1">Id cuenta </th>
                                    <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                    <th class="column100 column1" data-column="column3">Tipo de Moneda</th>
                                    <th class="column100 column3" data-column="column4">Propietario</th>
                                    <th class="column100 column4" data-column="column5"> Límite de transferencia</th>
                                    <th class="column100 column5" data-column="column6">Saldo Neto</th>
                                    <th class="column100 column6" data-column="column7">Cuenta Origen</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (Cuenta c : cuentasOrigen) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripción()%></td>
                                    <td class="column100 column1" data-column="column3"><%=c.getMonedaNombre().getSimbolo() + " " + c.getMonedaNombre().getDescripcion()%></td>
                                    <td class="column100 column1" data-column="column4"><%=c.getClienteIdCliente().getNombre() + " " + c.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column5"><%=c.getLimiteTransferenciaDiaria()%></td>
                                    <td class="column100 column1" data-column="column6"><%=c.getSaldoFinal()%></td>
                                    <td class="column100 column1" data-column="column7"> 
                                        <a href="/ProyectoBanco/transferir/selecciona/cuentas?idCuentaOrigen=<%=c.getNumCuenta()%><%if (model.getaTransferir() != null) {
                                                out.print("&idCuentaDestino=" + model.getaTransferir().getNumCuenta());
                                            }%>" id="textoSub">Seleccionar</a>
                                    </td>
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

            <br><br>

            <div class="container-table100">
                <div class="wrap-table100">
                    <div class="table100 ver1 m-b-110">
                        <table data-vertable="ver1">
                            <thead>
                                <tr class="row100 head">
                                    <th class="column100 column1" data-column="column1">Id cuenta </th>
                                    <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                    <th class="column100 column1" data-column="column1">Tipo de Moneda</th>
                                    <th class="column100 column3" data-column="column3">Propietario</th>
                                    <th class="column100 column5" data-column="column5">Cuenta Destino</th>

                                </tr>
                            </thead>
                            <tbody>
                                <%for (Cuenta c : cuentasDestino) {%>
                                <tr  class="row100">
                                    <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                    <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripción()%></td>
                                    <td class="column100 column2" data-column="column1"><%=c.getMonedaNombre().getSimbolo() + " " + c.getMonedaNombre().getDescripcion()%></td>
                                    <td class="column100 column1" data-column="column3"><%=c.getClienteIdCliente().getNombre() + " " + c.getClienteIdCliente().getApellidos()%></td>
                                    <td class="column100 column1" data-column="column6"> 
                                        <a href="/ProyectoBanco/transferir/selecciona/cuentas?idCuentaDestino=<%=c.getNumCuenta()%><%if (model.getSeleccionado() != null) {
                                                out.print("&idCuentaOrigen=" + model.getSeleccionado().getNumCuenta());
                                            }%>" id="textoSub">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>

                            </tbody>
                        </table>

                    </div>
                </div>
            </div>

            <h2>Cuenta de origen</h2> 
            <%if (request.getAttribute("iguales") != null) {%><label style="color:red;"> *Los numeros de cuenta son iguales o vacios<br></label><%}%>

            <input type="text" name="cuentaOrigenSelect" value="<% if (model.getSeleccionado() != null) {
                    out.print((String) String.valueOf(model.getSeleccionado().getNumCuenta()));
                } %>"  id="inputText" class="<%if (request.getAttribute("iguales") != null) {
                        out.print((String) request.getAttribute("iguales"));
                    } else if (request.getAttribute("vaciaO") != null) {
                        out.print((String) request.getAttribute("vaciaO"));
                    }%>" readonly/>

            <h2>Cuenta de destino</h2> 
            <input type="text" name="cuentaDestinoSelect" value="<% if (model.getaTransferir() != null) {
                    out.print((String) String.valueOf(model.getaTransferir().getNumCuenta()));
                } %>"  id="inputText" class="<%if (request.getAttribute("iguales") != null) {
                        out.print((String) request.getAttribute("iguales"));
                    } else if (request.getAttribute("vacioD") != null) {
                        out.print((String) request.getAttribute("vacioD"));
                    }%>" readonly/>

            <br><br><h2>Inserte el monto: </h2>
            <p><i>El monto debe ser menor o igual a los fondos <br>pertenecientes a la cuenta origen</i></p>
            <%if (request.getAttribute("errorMonto") != null) {%><label style="color:red;">*El monto es mayor al limite de transferencia o al de los fondos de la cuenta de origen<br></label><%}%>

            <input type="text" name="monto" value="<% if ((String) request.getAttribute("monto") != null) {
                    out.print((String) request.getAttribute("monto"));
                }%>" placeholder="Monto" class="<%if (request.getAttribute("errorMonto") != null) {
                        out.print((String) request.getAttribute("errorMonto"));
                    } else if (request.getAttribute("vacioM") != null) {
                        out.print((String) request.getAttribute("vacioM"));
                    }%>"/>
            <input type="submit" value="Ingresar" />
        </center>
    </form>
    <br><br>
    <br><br>

</div>



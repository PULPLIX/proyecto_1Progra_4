

<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">

<%@page import="banco.logica.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cajero.retiros.Model"%>

<div class="limiter"> 
    <br><br><br>
    <center><h2> Retiros </h2><br><br></center>
        
        
            <p style=" color: red"> <% if ((String) request.getAttribute("errorClienteVacio") != null) {
            out.print((String) request.getAttribute("errorClienteVacio"));
        }
        %>  </p>  
    <p style=" color: red"> <% if ((String) request.getAttribute("errorCuentaVacia") != null) {
            out.print((String) request.getAttribute("errorCuentaVacia"));
        }
        %>  </p>  
    <p style=" color: red">   <% if ((String) request.getAttribute("errorMontoVacio") != null) {
            out.print((String) request.getAttribute("errorMontoVacio"));
        }
        %>
    </p> 
        

    <p style=" color: red">   <% if ((String) request.getAttribute("errorClienteInvalido") != null) {
            out.print((String) request.getAttribute("errorClienteInvalido"));
        }
        %>
    </p> 

    <p style=" color: red">   <% if ((String) request.getAttribute("errorCuentaInvalido") != null) {
            out.print((String) request.getAttribute("errorCuentaInvalido"));
        }
        %>
    </p> 
    <p style=" color: red">   <% if ((String) request.getAttribute("errorClienteIndefinido") != null) {
            out.print((String) request.getAttribute("errorClienteIndefinido"));
        }
        %>
    </p> 

    <p style=" color: red">   <% if ((String) request.getAttribute("errorCuentaIndefinido") != null) {
            out.print((String) request.getAttribute("errorCuentaIndefinido"));
        }
        %>
    </p> 
        <p style=" color: red">   <% if ((String) request.getAttribute("errorMontoInvalido") != null) {
            out.print((String) request.getAttribute("errorMontoInvalido"));
        }
        %>
    </p> 

        <p style=" color: red">   <% if ((String) request.getAttribute("errorMontoExcesivo") != null) {
            out.print((String) request.getAttribute("errorMontoExcesivo"));
        }
        %>
    </p> 
        
        
<% if ((String) request.getAttribute("mensaje") != null) {
                out.print((String) request.getAttribute("mensaje") + "\n");
            } %>

    <center>                   
        <h2>Digite el n�mero de c�dula del cliente</h2>

        <form action="/ProyectoBanco/cajero/retiros/buscarCliente" method="post">
            <input type="text" name="clienteABuscar" value="<% if ((String) request.getAttribute("clienteBuscar") != null) {
                    out.print((String) request.getAttribute("clienteBuscar"));
                } else {
                    out.print("Cliente");
                }%>" class="<%if (request.getAttribute("errorClienteVacio") != null || request.getAttribute("errorClienteInvalido") != null|| request.getAttribute("errorClienteIndefinido") != null) {
                out.print("errorTxt");
            }%>">

            <input type="submit" value="Buscar" /><br><br>
        </form>

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
                    <h2>Cuentas del cliente</h2>
                    <br><br>
                    <table data-vertable="ver1">
                        <thead>
                            <tr class="row100 head">
                                <th class="column100 column1" data-column="column1">Id cuenta </th>
                                <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                <th class="column100 column4" data-column="column4"> L�mite de transferencia</th>
                                <th class="column100 column5" data-column="column5">Saldo Neto</th>
                                <th class="column100 column6" data-column="column5">Seleccionar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (cuentas != null) {%>
                            <% for (Cuenta c : cuentas) {%>
                            <tr  class="row100">
                                <td class="column100 column1" data-column="column1"><%=c.getNumCuenta()%> </td> 
                                <td class="column100 column1" data-column="column2"><%=c.getIdTipoCuenta().getDescripci�n()%></td>
                                <td class="column100 column1" data-column="column4"><%=c.getLimiteTransferenciaDiaria()%></td>
                                <td class="column100 column1" data-column="column5"><%=c.getSaldoFinal()%></td>
                                <td class="column100 column1" data-column="column6"><a href="/ProyectoBanco/cajero/retiros/seleccionar?idCuenta=<%=c.getNumCuenta()%>">Seleccionar</a></td>
                            </tr>
                            <%}
                                }%>
                        </tbody>

                    </table>

                    <br><br>
                    <h2>Digite la cuenta a seleccionar</h2>

                    <form action="/ProyectoBanco/cajero/retiros/buscarCuenta" method="post">
                        <input type="text" name="cuentaABuscar" value="<% if ((String) request.getAttribute("cuentaSeleccionada") != null) {
                                out.print((String) request.getAttribute("cuentaSeleccionada"));
                            } else {
                                out.print("");
                            }%>" class="<%if (request.getAttribute("errorCuentaVacia") != null || request.getAttribute("errorCuentaInvalido") != null|| request.getAttribute("errorCuentaIndefinido") != null) {
                out.print("errorTxt");
            }%>">

                        <input type="submit" value="Buscar" /><br><br>
                    </form>

                    <table data-vertable="ver2">
                        <thead>
                            <tr class="row100 head">
                                <th class="column100 column1" data-column="column1">Id cuenta </th>
                                <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                                <th class="column100 column3" data-column="column3">Propietario</th>
                                <th class="column100 column4" data-column="column4">L�mite de transferencia</th>
                                <th class="column100 column5" data-column="column5">Saldo Neto</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr  class="row100">
                                <%if (model.getSeleccionada() != null) {%>
                                <td class="column100 column1" data-column="column1"><%=seleccionada.getNumCuenta()%> </td> 
                                <td class="column100 column1" data-column="column2"><%=seleccionada.getIdTipoCuenta().getDescripci�n()%></td>
                                <td class="column100 column1" data-column="column3"><%=seleccionada.getClienteIdCliente().getNombre() + " " + seleccionada.getClienteIdCliente().getApellidos()%></td>
                                <td class="column100 column1" data-column="column4"><%=seleccionada.getLimiteTransferenciaDiaria()%></td>
                                <td class="column100 column1" data-column="column5"><%=seleccionada.getSaldoFinal()%></td>
                                <%}%>
                            </tr>
                        </tbody>
                    </table>

                    <form action="/ProyectoBanco/cajero/retiros/ingresar" method="post">
                        <br><br>
                        <h2>Cuenta seleccionada</h2> <!--POR AQUI VOY-->
                        <input type="text" name="cuentaABuscar" value="<% if (model.getSeleccionada() != null) {
                                out.print((String) String.valueOf(model.getSeleccionada().getNumCuenta()));
                            } else {
                                out.print("1235");
                            }%>"  id="inputText" readonly/>
                        <h2>Digite el monto</h2>
                        <input type="text" name="monto" value="<% if ((String) request.getAttribute("monto") != null) {
                                out.print((String) request.getAttribute("monto"));
                            } else {
                                out.print("Monto");
                            }%>" class="<%if (request.getAttribute("errorMontoVacio")!= null || request.getAttribute("errorMontoInvalido") != null || request.getAttribute("errorMontoExcesivo") != null)  {
                out.print("errorTxt");
            }%>">                      
                        <br><br>
                        Si todos los datos est�n correctos, puede ingresarlos en el sistema y luego se confirmar� el retiro. <br><br>
                        &nbsp<input type="submit" value="Ingresar Datos" />


                    </form>

                </div>
            </div>
        </div>
    
    </center>
</div>
    

    
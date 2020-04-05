<%-- 
    Document   : Cuerpo
    Created on : 26/03/2020, 07:55:36 PM
    Author     : Iván
--%>

<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/infoPersonal.css">
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/registro.css">

<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cajero.infoPersonal.Model"%>

<%
    banco.presentacion.cajero.infoPersonal.Model model = (Model) request.getAttribute("model");
    //Cliente cliente = (Cliente) session.getAttribute("cliente");

%>


<div class="limiter"> 
    <p style=" color: red"> <% if ((String) request.getAttribute("errorNombre") != null) {
            out.print((String) request.getAttribute("errorNombre"));
        }
        %>  </p>  
    <p style=" color: red"> <% if ((String) request.getAttribute("errorApellidos") != null) {
            out.print((String) request.getAttribute("errorApellidos"));
        }
        %>  </p>  
    <p style=" color: red">   <% if ((String) request.getAttribute("errorTelefono") != null) {
            out.print((String) request.getAttribute("errorTelefono"));
        }
        %>
    </p> 
        <p style=" color: red">   <% if ((String) request.getAttribute("errorTelefonoInvalido") != null) {
            out.print((String) request.getAttribute("errorTelefonoInvalido"));
        }
        %>
    </p> 
    <center> <h2> Informacion Personal </h2></center>

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
                        <%if (cliente != null) {%>
                        <tr  class="row100">
                            <td class="column100 column1" data-column="column1"><%=cliente.getIdCliente()%> </td> 
                            <td class="column100 column1" data-column="column2"><%=cliente.getApellidos()%></td>
                            <td class="column100 column1" data-column="column3"><%=cliente.getNombre()%></td>
                            <td class="column100 column1" data-column="column4"><%=cliente.getTelefono()%></td>

                        </tr>
                        <%}%>
                    </tbody>
                </table>


            </div>
        </div>
    </div>

    <br><br>
    <center>
        <label for="show">
            <span>[Editar informacion]</span>
        </label><br>
        <input type="radio" id="show" name="group">


        <input type="radio" id="hide" name="group">



        <div id="content">
            <form action="/ProyectoBanco/cajero/infoPersonal/actualizar"> 
                <br> Nombre <br>
                <input type="text" name="nombreE" value="<% if ((String) request.getAttribute("clienteNombre") != null) {
                        out.print((String) request.getAttribute("clienteNombre"));
                    } else {
                        out.print("Nombre");
                    }%>" class="<%if (request.getAttribute("errorNombre") != null) {
                    out.print("errorTxt");
            }%>" >
                <br><br> Apellidos <br>
                <input type="text" name="apellidosE" value="<% if ((String) request.getAttribute("clienteApellidos") != null) {
                        out.print((String) request.getAttribute("clienteApellidos"));
                    } else {
                        out.print("Apellidos");
                    }%>" class="<%if (request.getAttribute("errorApellidos") != null) {
                out.print("errorTxt");
            }%>">
                <br><br> Telefono <br>
                <input type="text" name="telefonoE" value="<% if ((String) request.getAttribute("clienteTelefono") != null) {
                        out.print((String) request.getAttribute("clienteTelefono"));
                    } else {
                        out.print("Telefono");
                    }%>" class="<%if (request.getAttribute("errorTelefono") != null || request.getAttribute("errorTelefonoInvalido") != null) {
                out.print("errorTxt");
            } %>">


                <br><br><input type="submit" value="Actualizar"/>
            </form>
            <br><br> <label for="hide">               
                <span>[Cancelar]</span>
            </label><br>
        </div>
    </center>             
</div>  


<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/infoPersonal.css">

<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cliente.infoPersonal.Model"%>

<%
    banco.presentacion.cliente.infoPersonal.Model model = (Model) request.getAttribute("model");
    //Cliente cliente = (Cliente) session.getAttribute("cliente");

%>
<!--
//LOS ERRORES QUE APARECEN CON RESPECTO AL CLIENTE ES PORQUE EL ATRIBUTO SE 
//ENCUENTRA DECLARADO PREVIAMENTE EN EL ARCHIVO DE "InfoPersonal.jsp". El -->
<!--//CUAL SE INCLUYE EN EL ARCHIVO DE VIEW.JSP -->


<div class="limiter"> 
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
                    <form action="/ProyectoBanco/infoPersonal/actualizar"> 
                <br> Nombre <br>
                <input type="text" name="nombreE" value="<% if ((String) request.getAttribute("clienteNombre") != null) {
                        out.print((String) request.getAttribute("clienteNombre"));
                    } else {
                        out.print("Nombre");
                    }%>" >
                <br><br> Apellidos <br>
                <input type="text" name="apellidosE" value="<% if ((String) request.getAttribute("clienteApellidos") != null) {
                        out.print((String) request.getAttribute("clienteApellidos"));
                    } else {
                        out.print("Apellidos");
                    }%>" >
                <br><br> Telefono <br>
                <input type="text" name="telefonoE" value="<% if ((String) request.getAttribute("clienteTelefono") != null) {
                        out.print((String) request.getAttribute("clienteTelefono"));
                    } else {
                        out.print("Telefono");
                    }%>" >
                
                
                <br><br><input type="submit" value="Actualizar"/>
                </form>
                <br><br> <label for="hide">               
                    <span>[Cancelar]</span>
                </label><br>
            </div>
    </center>             
</div>  


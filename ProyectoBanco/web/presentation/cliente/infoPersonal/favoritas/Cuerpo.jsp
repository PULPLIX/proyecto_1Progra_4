<%-- 
    Document   : Cuerpo
    Created on : Mar 23, 2020, 3:21:32 PM
    Author     : Oscar
--%>

<%@page import="banco.logica.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Template/infoPersonal.css">

<%@page import="java.util.List"%>
<%@page import="banco.logica.Cliente"%>
<%@page import="banco.presentacion.cliente.cuentas.Model"%>

<%
    banco.presentacion.cliente.cuentas.Model model = (Model) request.getAttribute("model");
    ArrayList<Cuenta> favoritas = model.getCurrent().getFavoritasCollection();
%>



<div class="limiter"> 
    <center> <h2> Cuentas Favoritas </h2></center>

    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <table data-vertable="ver1">
                    <thead>
                        <tr class="row100 head">
                            <th class="column100 column1" data-column="column1">Id cuenta </th>
                            <th class="column100 column2" data-column="column2">Tipo de Cuenta</th>
                            <th class="column100 column3" data-column="column3">Estado</th>
                            <th class="column100 column4" data-column="column4">Propietario</th>
                            <th class="column100 column5" data-column="column5">Eliminar</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%for (Cuenta fav : favoritas) {%>
                        <tr  class="row100">
                            <td class="column100 column1" data-column="column1"><%=fav.getNumCuenta()%> </td> 
                            <td class="column100 column1" data-column="column2"><%=fav.getIdTipoCuenta().getIdTipoCuenta()%></td>
                            <td class="column100 column1" data-column="column3"><%=fav.getIdTipoCuenta().getDescripción()%></td>
                            <td class="column100 column1" data-column="column4"><%=fav.getClienteIdCliente().getNombre() + " " + fav.getClienteIdCliente().getApellidos()%></td>
                            <td class="column100 column1" data-column="column5"> <form method="post" > 
                                    <a href="/ProyectoBanco/cuentas/favoritas/eliminar?idCuenta=<%=fav.getNumCuenta()%>" method="post" > <img src="/ProyectoBanco/images/menuIcon/eliminar.png"  width="30" height="30" id="subIcon"></a>
                                </form></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <br><br>      

</div>  
<%if (request.getAttribute("noExiste") != null) {%><center><label style="color:red;">La cuenta ingresada no existe o ya se encuentra registrada<br></label></center><%}%>       
<br><br> 
<a href="#modal" class="btn-open-popup">Vincular una cuenta </a>
<br><br> 
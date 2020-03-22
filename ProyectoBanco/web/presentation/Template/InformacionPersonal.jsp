<%@page import="java.util.Calendar"%>
<link rel="stylesheet" type="text/css" href="/ProyectoBanco/css/Index/cabeza.css">
<div id="imagenBanco">
    <img src="/ProyectoBanco/images/logo/logoBlanco2.png"  width="160" height="65" style="position: relative; left: 50px;"> 
</div>

<div id="banner" >
    <div id="bienvenida">
        <% long horas = java.util.Calendar.getInstance().getTime().getHours();
            if (horas >= 0 && horas < 12) {
                out.print("Buenos Días,");
            } else if (horas >= 12 && horas < 18) {
                out.print("Buenos Tardes,");
            } else if (horas >= 18 && horas <= 24) {
                out.print("Buenos Noches,");
            }

            out.print(" " + request.getAttribute("clienteNombre") + " " + request.getAttribute("clienteApellidos"));

        %></div>
    <div id="fecha">
        <%        Calendar calendar = java.util.Calendar.getInstance();
            int month = calendar.get(java.util.Calendar.MONTH);
            month += 1;
            out.print("Fecha de hoy: " + month);
            out.print("/" + calendar.get(java.util.Calendar.DATE));
            out.print("/" + calendar.get(java.util.Calendar.YEAR));
        %>
    </div>
</div>
<%-- 
    Document   : Index
    Created on : 08/03/2020, 07:59:33 PM
    Author     : Iván
--%>

<% 
int secuencia = 0;
    
for(int i = 0; i < 10; i++){
    secuencia += 5;
}

String respuesta = String.valueOf(secuencia);

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <p>LA SECUENCIA ES:<br>
        
        <%= respuesta %>
        
        </p>
        
        
    </body>
</html>

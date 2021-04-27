<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAQs</title>
        
        <link  rel="icon"   href="./assets/img/logoEWG.png" type="image/png" />
        <style>
        	<%@include file="/WEB-INF/css/estilos.css" %>
        </style>
    </head>
    <body>
        <%
            String redirectURL = "/Faqs/list";
            response.sendRedirect(redirectURL);
        %>  
    </body>
</html>

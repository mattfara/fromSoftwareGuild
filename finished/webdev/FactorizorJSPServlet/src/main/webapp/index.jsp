<%-- 
    Document   : index
    Created on : Oct 2, 2017, 8:09:02 PM
    Author     : matt
--%>
<!-- note that this looks completely independent of the servlet -->
<!-- the servlet reaches into this without its knowledge -->
<!-- this is run by the container -->
<!-- tomcat container chooses this with welcome file list -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizor</title>
    </head>
    <body>
        <h1>Factorizor</h1>
        <p>Please enter the num you wanna factor</p>
        <form method="post" action="FactorizorServlet"> <!-- note that action takes urlPattern from servlet file-->
            <input type="text" name="numberToFactor"/><br>
            <input type="submit" value="FInd Factors"/>
        </form>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Details Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <!-- top half -->
            <div class="row">
                <!-- stick this button into a form -->
                <h1> <c:out value="${dvd.title}"/> </h1> <!-- is this the correct syntax? -->               
            </div>    


            <hr/>



            <!-- bottom half -->

            <div class="row"> <!-- double check syntax/parsing-->
                <p>Release year: <c:out value="${dvd.releaseYear}"/>></p>
                <p>Director: <c:out value="${dvd.director}"/>></p>
                <p>Rating: <c:out value="${dvd.rating}"/>></p>
                <p>Notes: <c:out value="${dvd.notes}"/>></p>
                


            </div> <!-- End row div -->
            
            <a href="displayDVDsPage"><button>Back</button></a>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


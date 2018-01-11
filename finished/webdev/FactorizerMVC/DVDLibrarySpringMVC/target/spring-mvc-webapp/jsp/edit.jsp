<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <!-- top half -->
            <div class="row">
                <!-- stick this button into a form -->
                <h1> Edit DVD: <c:out value="${dvd.title}"/> </h1> <!-- is this the correct syntax? -->               
            </div>    


            <hr/>



            <!-- bottom half -->

            <div class="row"> <!-- double check syntax/parsing-->
                <form role="form" class="form-horizontal">
                    <label for="title">Dvd title: </label>
                    <input type="text" id="title" value="${dvd.title}"/>
                    
                    <label for="releaseYear">Release year: </label>
                    <input type="text" id="releaseYear" value="${dvd.releaseYear}"/>
                    
                    <label for="director">Director: </label>
                    <input type="text" id="director" value="${dvd.director}"/>
                    
                    <!-- 
                    #########################
                        Strategies for pre-selecting the original rating 
                        1. jQuery -- requires review and extra work
                        2. just place it separately on the page --easiest
                        3. use JSTL somehow -- probably the best compromise
                    #########################
                    -->
                    <label for="rating">Rating: </label>
                    <select id="rating" value="${dvd.rating}">
                        <option value="g">G</option>
                        <option value="pg">PG</option>
                        <option value="pg-13">PG-13</option>
                        <option value="r">R</option>
                    </select>
                    
                    <label for="notes">Notes: </label>
                    <textarea><c:out value="${dvd.notes}"/></textarea>
                    
                </form>
                
                
                
                
                <p>Release year: <c:out value="${dvd.releaseYear}"/>></p>
                <p>Director: <c:out value="${dvd.director}"/>></p>
                <p>Rating: <c:out value="${dvd.rating}"/>></p>
                <p>Notes: <c:out value="${dvd.notes}"/>></p>
                


            </div> <!-- End row div -->
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


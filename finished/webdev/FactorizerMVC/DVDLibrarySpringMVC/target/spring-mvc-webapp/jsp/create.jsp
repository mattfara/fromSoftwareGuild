<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <!-- top half -->
            <div class="row">
                <!-- stick this button into a form -->
                <h1> Create DVD</h1> <!-- is this the correct syntax? -->               
            </div>    


            <hr/>



            <!-- bottom half -->

            <div class="row"> <!-- double check syntax/parsing-->
                <form role="form" class="form-horizontal" method="POST" action="createDVD">
                    <label for="title">Dvd title: </label>
                    <input type="text" id="title" placeholder="Enter Title"/>
                    
                    <label for="releaseYear">Release year: </label>
                    <input type="text" id="releaseYear" placeholder="Enter Release Year"/>
                    
                    <label for="director">Director: </label>
                    <input type="text" id="director" placeholder="Enter Director"/>
                    
                    <label for="rating">Rating: </label>
                    <select id="rating">
                        <option value="g">G</option>
                        <option value="pg">PG</option>
                        <option value="pg-13">PG-13</option>
                        <option value="r">R</option>
                    </select>
                    
                    <label for="notes">Notes: </label>
                    <textarea placeholder="Enter Note"></textarea>
                    <button type="submit">Create</button>
                </form>

            </div> <!-- End row div -->
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


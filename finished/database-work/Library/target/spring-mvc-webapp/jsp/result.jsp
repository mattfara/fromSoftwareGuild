<%-- 
    Document   : result
    Created on : Oct 3, 2017, 9:25:40 AM
    Author     : matt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHANGE THIS</title>
    </head>
    <body>
        <main>
            <section>
                <header>
                    <h1>Results MAKE DESCRIPTION</h1>
                </header>
            </section>
            <section>
                USE JSTL AND NAMES FROM CONTROLLER TO CALL IN VALUES TO BE GENERATED
				EG <c:out value="${calculatedResult}"/>
				
            </section>
                <section>
                    <h3> Next </h3>
                    <p>
                        <a href="index.jsp"> <-- THIS SHOULD POINT BACK TO MAIN PAGE DYNAMICALLY -- WANNA DO SOMETHING AGAIN?</a><!-- this should be used instead of index.jsp, since you want to keep loading dynamically -->
                    </p>
                </section>
        </main>
    </body>
</html>


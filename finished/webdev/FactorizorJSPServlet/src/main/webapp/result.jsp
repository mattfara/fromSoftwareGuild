<%-- 
    Document   : result
    Created on : Oct 2, 2017, 8:10:32 PM
    Author     : matt
--%>

<!-- tomcat container knew to use this file because of the RequestDispatcher object and its .forward method-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h1>Result</h1>
        <p>
                You asked to factor ${numberToFactor} <!-- this is the once gotten with .getParameter -->
        </p>
        <p>
            Facotrs are:
            <c:forEach var="currentFactor" items="${factors}"> <!-- note that this is the name in quotes in the index.jsp-->
                <c:out value="${currentFactor}"/>
            </c:forEach>
        </p>
        <p>
            <c:choose>
                <c:when test="${isPerfect}">
                    <c:out value="The number is perfect"/>
                </c:when>
                <c:otherwise>
                    <c:out value="The number is not perfect"/>
                </c:otherwise>
            </c:choose>
        </p>
        <p>
            <c:choose>
                <c:when test="${isPrime}">
                    <c:out value="The num is prime"/>
                </c:when>
                <c:otherwise>
                    <c:out value="Not prime"/>
                </c:otherwise>
            </c:choose>
        </p>
        <p>
            <a href="index.jsp"> Factor Anoterh One!</a> <!-- lets us repeat process -->
        </p>
    </body>
</html>

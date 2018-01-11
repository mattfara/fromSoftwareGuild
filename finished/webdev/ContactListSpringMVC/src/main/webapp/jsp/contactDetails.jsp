<%-- 
    Document   : contactDetails
    Created on : Oct 5, 2017, 3:02:38 PM
    Author     : matt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Details</title>
    </head>
    <body>
        <div class="container">
            <h1>Contact List Spring MVC</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class=""><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation" class=""><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a></li>
                </ul>    
            </div>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>


            <h2>Contact Info</h2>
            <p> Name: <c:out value="${selectedContact.firstName}"/> <c:out value="${selectedContact.lastName}"/></p>
            <p> Company: <c:out value="${selectedContact.company}"/></p>
            <p> Phone: <c:out value="${selectedContact.phone}"/></p>
            <p> Email: <c:out value="${selectedContact.email}"/></p>
        </div>
    </body>
</html>

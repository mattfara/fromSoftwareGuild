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
        <title>Tip Calculator -- Result </title>
    </head>
    <body>
        <main>
            <section>
                <header>
                    <h1>Results</h1>
                </header>
            </section>
            <section>
                <p>Amount: $<c:out value="${originalAmount}"/></p>
                <p>Tip %: <c:out value="${tipPercent}"/></p>
                <p>Tip Amount: $<c:out value="${tipAmount}"/></p>
                <p>Total Bill: $<c:out value="${total}"/></p>
            </section>
                <section>
                    <h3> Next </h3>
                    <p>
                        <a href="index.jsp"> Wanna calculate again?</a><!-- this should be used instead of index.jsp, since you want to keep loading dynamically -->
                    </p>
                </section>
        </main>
    </body>
</html>

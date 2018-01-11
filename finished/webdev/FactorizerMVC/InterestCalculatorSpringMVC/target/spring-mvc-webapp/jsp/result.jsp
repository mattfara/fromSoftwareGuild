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
        <title>Interest Calculator - Results</title>
    </head>
    <body>
        <main>
            <section>
                <header>
                    <h1>Yearly <abbr title="Return On Investment">ROI</abbr></h1>
                    <p>Below are the results for your calculation</p>
                </header>
            </section>
            <section>
                <p> <!-- need p's in p's? -->
                    <div>
                        <p>If you invest $ ${initialPrincipal} for ${yearsToInvest} <br>
                            years at an interest rate of ${interestRate}%....</p>
                    </div>
                </p>
            </section>
            <section> <!-- results -->
                <c:forEach var="currentYear" items="${yearlyReports}">
                    <hr>
                    <p> Year: </p> <c:out value="${currentYear.year}"/><br>
                    <p>Year start principal: </p><c:out value="${currentYear.startingPrincipal}"/><br>
                    <p>Year end principal: </p><c:out value="${currentYear.endingPrincipal}"/><br>
                    <p>Interest earned for year: </p><c:out value="${currentYear.interestEarned}"/>
                </c:forEach>
                           
            </section>
            <section>
                <h2>Next</h2>
                <p>
                    <a href="index.jsp">Test another investment</a>
                </p>
            </section>    
        </main>
    </body>
</html>

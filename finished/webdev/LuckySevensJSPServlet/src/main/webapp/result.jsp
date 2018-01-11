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
        <title>Lucky Sevens -- You Lose</title>
    </head>
    <body>
        <main>
            <section>
                <header>
                    <h1>Results</h1>
                    <p>This is how you fared....</p>
                </header>
            </section>
            <section>
                <p>You played $<c:out value="${initialMoney}"/></p>
                <p>And went broke after <c:out value="${numberOfRolls}"/> rolls</p>
                <p>You should have quit after <c:out value="${rollWhenMoneyWasHighest}"/> rolls<br>
                    when you had $<c:out value="${maxMoney}"/>
                </p>
            </section>
                <section>
                    <h3> Next </h3>
                    <p>
                        <a href="index.jsp"> Wanna play again?</a><!-- this should be used instead of index.jsp, since you want to keep loading dynamically -->
                    </p>
                </section>
        </main>
    </body>
</html>

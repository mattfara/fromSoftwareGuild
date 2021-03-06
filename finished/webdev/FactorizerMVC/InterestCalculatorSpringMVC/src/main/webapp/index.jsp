<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Interest Calculator Welcome</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
<main>
            <section>
        <h1> Interest Calculator Welcome </h1>
        <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                </ul>    
            </div>
        <header>
            <section>
                <h2>Our service</h2>
                <p>
                    Welcome to interest calculator. Here, you determine your return on investment before investing!
                </p>
            </section>
            <section>
                <h2>Instructions</h2>
                <p>
                    In the form below, punch in the annual interest rate, years of investment, and initial principal into the text fields. 
                </p>
            </section>
        </header>
        </section>
            <section>
                <form method="post" action="calculateInvestment">
                    <fieldset>
                        <legend>Investment Dimensions</legend>
                        <p>
                            <label for="interest-rate" > Enter annual interest rate: </label>
                            <input type="number" name="interest-rate" id="interest-rate" min="1" autofocus required>
                        </p>
                        <p>
                            <label for="years" > Enter years: </label>
                            <input type="number" name="years" id="years" min="1" required>
                        </p>
                        <p>
                            <label for="starting-principal" > Enter starting principal: </label>
                            <input type="number" name="starting-principal" id="starting-principal" min="1" required>
                        </p>
                    </fieldset>
                    <button type="submit">Submit</button>
                </form>
            </section>
        </main>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>


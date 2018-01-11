<%-- 
    Document   : index
    Created on : Oct 3, 2017, 9:25:21 AM
    Author     : matt
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tip Calculator -- Start</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
        <main>
            <section>
        <h1> Tip Calculator </h1>
        <div class="navbar">
            <ul class="nav nav-tabs">
              <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
              <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
            </ul>    
        </div>
        <header>
            <section>
                <h3>The App</h3>
                <p>
                    Welcome to our Tip Calculator.
                </p>
            </section>
            <section>
                <h3>Instructions</h3>
                <p>
                    In the form below, punch in the bill and what percent tip you'd like to leave.<br>
                    We will tell you how much to leave as a tip.
                </p>
            </section>
        </header>
        </section>
            <section>
                <form method="post" action="calculateTip">
                    <fieldset>
                        <legend>Bill and Tip</legend>
                        <p>
                            <label for="bill" > Enter $: </label>
                            <input type="number" name="bill" id="bill" min="1" autofocus required/>
                        </p>
                        <p>
                            <label for="tip" > Enter tip (%): </label>
                            <input type="number" name="tip" id="tip" min="1" autofocus required/>
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

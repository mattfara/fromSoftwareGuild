<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <main>


    <section>
<h1> Flooring Cost Calculator </h1>
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
            Welcome to our flooring calculator. Here, you can discover the price of an order before placing it.<br>
            We work at a pace of about 20 sqFt per hour, and charge $86/hr
        </p>
    </section>
    <section>
        <h2>Instructions</h2>
        <p>
            In the form below, punch in the length and width of your order into the text fields. 
            <br> Next, choose a flooring material, noting its cost per sq foot.
        </p>
    </section>
</header>
</section>
    <section>
        <form method="post" action="calculateFlooringCost">
            <fieldset>
                <legend>Order Dimensions</legend>
                <p>
                    <label for="length" > Enter length: </label>
                    <input type="number" name="length" id="length" min="10" max="20" autofocus required>
                </p>
                <p>
                    <label for="width" > Enter width: </label>
                    <input type="number" name="width" id="width" min="10" max="20" required>
                </p>
            </fieldset>
            <fieldset>
                <legend>Material ($/sqFt) </legend>
                <p>
                    <select name = "costPerSqFt" required>
                        <option value="10">Toilet Paper ($10)</option>
                        <option value="6">Ragweed ($6)</option>
                        <option value="5">Drift wood ($5)</option>
                        <option value="1">Wood ($1)</option>
                    </select>
                </p>
            </fieldset>
            <button type="submit">Submit</button>
        </form>
    </section>
</main>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


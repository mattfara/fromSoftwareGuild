
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens -- Start</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <main>
            <section>
        <h1> Lucky Sevens </h1>
        <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                </ul>    
            </div>
        <header>
            <section>
                <h2>The Game</h2>
                <p>
                    Welcome to our Lucky Sevens, a fun dice game. If you roll a 7, you win $4.<br>
                    Otherwise, you will lose $1.
                </p>
            </section>
            <section>
                <h2>Instructions</h2>
                <p>
                    In the form below, punch in the amount of money you want to play with. 
                </p>
            </section>
        </header>
        </section>
            <section>
                <form method="post" action="play">
                    <fieldset>
                        <legend>Money to Bet</legend>
                        <p>
                            <label for="money" > Enter $: </label>
                            <input type="number" name="money" id="money" min="1" autofocus required>
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

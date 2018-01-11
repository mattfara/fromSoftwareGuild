<%-- 
    Document   : result
    Created on : Oct 3, 2017, 9:25:40 AM
    Author     : matt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator -- Price Estimate</title>
    </head>
    <body>
        <main>
            <section>
                <header>
                    <h1>Order Estimate</h1>
                    <p>Below is the estimate for your order</p>
                </header>
            </section>
            <section>
                <p>
                    <div id="time">Estimated time required for labor: ${totalTimeRequired} hours</div>
                    <div id="total-cost">Total cost: ${totalCost}</div>
                </p>
            </section>
            <section>
                <h2>Next</h2>
                <p>
                    <a href="index.jsp">Make another estimate</a>
                </p>
            </section>    
        </main>
    </body>
</html>

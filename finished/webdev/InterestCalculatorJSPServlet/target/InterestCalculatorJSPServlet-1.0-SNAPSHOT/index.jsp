<%-- 
    Document   : index
    Created on : Oct 3, 2017, 10:43:58 AM
    Author     : matt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interest Calculator Welcome</title>
    </head>
        <body>
        <main>
            <section>
        <h1> Interest Calculator Welcome </h1>
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
                <form method="post" action="InterestCalculatorServlet">
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
    </body>
</html>

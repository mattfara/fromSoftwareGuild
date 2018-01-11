<%-- 
    Document   : index
    Created on : Oct 3, 2017, 9:25:21 AM
    Author     : matt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tip Calculator -- Start</title>
    </head>
    <body>
        <main>
            <section>
        <h1> Tip Calculator </h1>
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
                <form method="post" action="TipCalculatorServlet">
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
    </body>
</html>

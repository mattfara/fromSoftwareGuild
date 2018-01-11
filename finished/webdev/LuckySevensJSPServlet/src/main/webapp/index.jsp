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
        <title>Lucky Sevens -- Start</title>
    </head>
    <body>
        <main>
            <section>
        <h1> Lucky Sevens </h1>
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
                <form method="post" action="LuckySevensServlet">
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
    </body>
</html>

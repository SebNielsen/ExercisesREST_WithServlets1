<%-- 
    Document   : index
    Created on : 25-09-2015, 08:20:55
    Author     : sebastiannielsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>"Quote of Today"</h1>
        <form>
            <textarea id="quote" rows="4" cols="50"></textarea>
            <br>
            <button id="randomQuote">New Quote</button>
            <button id="edit">Edit Quote</button>
            <button id="removeQuote">Remove Quote</button>
        </form>
        <br>
        <br>
        <h3>Create new Quote</h3>
        <form>
            <input id="input" type="text" placeholder="New quote">
            <button id="newQuot">New Quote</button>
        </form>
        <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="assets/javascript.js"></script>
    </body>
</html>

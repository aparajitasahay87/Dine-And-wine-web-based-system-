<%-- 
    Document   : login
    Created on : Dec 15, 2015, 12:55:09 PM
    Author     : Funapp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Please login</h1>
        <form action="Userlogin" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td>Username: </td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Login" name="Login" />
        </form>
    </body>
</html>

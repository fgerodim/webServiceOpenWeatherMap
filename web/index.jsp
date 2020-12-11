<%-- 
    Document   : index
    Created on : Feb 19, 2017, 11:18:00 AM
    Author     : Theofanis Gerodimos
--%>
<%@page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maria</title>
<!A javascript that is called on submit to check if form's textfields have contents>
<!It shows an alert window when somebody submit the form without content>
        <script>
        function validateForm() {
            var city = document.forms["myForm"]["city"].value;
            var country = document.forms["myForm"]["country"].value;
            if (city="" || country=="") {
                alert("City field and Country field must be filled out");
                return false;
            }
        }
        </script>
    </head>
    <body>
        <!Here i have all the components of my html form>
        <form name ="myForm" action="HelloServlet" onsubmit="return validateForm()">
            <p>To retrieve weather information, fill in the two textfields, select a forecast periodand press 'Get Weather!' button</p>
            City name: <input type="text" name="city"size="20px"><br><br>
            Country Code: <input type="text" name="country"size="20px"><br><br>
            Forecast period:<br>
            <input type="radio" name="period" id="three" value=3 checked="checked"><label>3 days</label><br>
            <input type="radio" name="period" id="five" value=5><label>5 days</label><br>
            <input type="radio" name="period" id="seven" value=7><label>7 days</label><br><br>
            <input type="submit" value="Get weather!"  >						
	</form>		
        <%Date d=new Date(); %>
        <h1><%=d%></h1>
    </body>
</html>

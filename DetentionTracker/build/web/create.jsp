<%-- 
    Document   : create
    Created on : 14/08/2014, 8:39:32 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>     
        <h1>Create a Detention</h1>        
        <div>
            <form align="center" name="input" action="view.jsp" method="get">
                First Name: <input type="text" name="fname" > <br>
                Last Name: <input type="text" name="sname"> <br>
                Year: <select name="year">
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select><br>
                Type of Detention: <select name="type">
                    <option value="afternoon">Afternoon</option>
                    <option value="morning">Morning</option>
                    <option value="saturday">Saturday</option>
                    <option value="lunchtime">Lunchtime</option>
                </select><br>
                Department: <select name="dept">
                    <option value="afternoon">Computing</option>
                    <option value="morning">Math</option>
                    <option value="saturday">English</option>
                    <option value="lunchtime">Science</option>
                    <option value="lunchtime">HSIE</option>
                </select><br>
                Reason: <input type="text" name="reason">
                <br>
                <input type="submit" value="Submit" align="right">
            </form>
        </div>       
        <jsp:include page="footer.jsp"/>
    </body>
</html>

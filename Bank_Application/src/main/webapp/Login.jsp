<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>	
<body>
  <form action="http://localhost:8081/Bank_Application/LoginServlet" method="post">
  <table>
    <tr>
      <th>User Name:<input type="text" name="uname" required></th>
    </tr>
    <tr>
      <th>Password:<input type="password" name="pass" required></th>
    </tr>
    <tr>
      <th><input type="submit" value="Login"></th>
    </tr>  
  </table>
  </form>
  <form action="http://localhost:8081/Bank_Application/Registration.jsp">
  <input type="submit" value="New User">
  </form>
</body>
</html>
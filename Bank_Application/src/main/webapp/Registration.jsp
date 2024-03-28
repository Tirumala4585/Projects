<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="http://localhost:8081/Bank_Application/RegisterServlet" method="post">
    <table align="center">
       <tr>
         <th>User Name:<input type="text" name="user_name" required></th>
       </tr>
       <tr>
         <th>Password:<input type="password" name="user_pass" required></th>
       </tr>
       <tr>
        <th>Full Name:<input type="text"  name="full_name" required></th>
      </tr>
      <tr>
        <th>Phno:<input type="text" name="user_phno" required></th>
      </tr>
      <tr>
        <th>Email<input type="email" name="user_email" required></th>
      </tr>
      <tr>
       <th>Address:<input type="text" name="user_address" required></th> 
      </tr>
      <tr>
        <th><input type="submit" value="Register"></th>
      </tr>  
      </table>
      </form>      
</body>
</html>
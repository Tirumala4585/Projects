<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
h2{
   text-align:center;
   background-color:white;
   width:200px;
   padding-right:5px;
   margin:10px 50px;
}
div{
background-color:skyblue;
width:300px;
height:300px;
border:1px solid black;
padding:20px 20px 20px 30px;
margin-top:150px;
margin-left:550px;
}
</style>
</head>	
<body>
  <div>
   <h2>Login</h2>
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
 </div>
</body>
</html>

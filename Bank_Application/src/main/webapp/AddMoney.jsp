<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bank.userdto.UserDTO" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
     function myFunction()
     {
    	 var account = document.getElementById("amount").value;
    	 if(!account)
    	 {	
    	    alert("Please enter amount before deposit");
    	    return false;
    	 }
    	  document.getElementById('addMoney').submit();
    	  return true;
     }
</script>    
</head>
<body>
   <%UserDTO user = (UserDTO)session.getAttribute("user"); 
  if(user!=null){%>
  <h1>Addmoney</h1>
  <h4>UserName:<%out.print(user.getUserName());%></h4>
  <form  id="addMoney" action="http://localhost:8081/Bank_Application/AddMoneyServlet" method="post">
     <input type="hidden" name="accountId" value=<%= request.getAttribute("accountId") %>>
     Amount:<input type="text"  id="amount" name="amount"></br></br>
     <button type="button" onclick="myFunction()">Deposit</button>
   </form> 
  <%
  }
  else
  {
	  response.sendRedirect("Home.jsp");
  }
  %>  
</body>
</html>
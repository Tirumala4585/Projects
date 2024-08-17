<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bank.userdto.UserDTO"%>    
<%@ page import="com.bank.bankdao.BankDAO"%>  
<%@ page import="com.bank.bankdto.BankDTO"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%UserDTO user = (UserDTO)session.getAttribute("user");
  if(user!=null)
  { 
       int account_id = Integer.parseInt((String)request.getAttribute("accountId"));
       String account_no = (String)request.getAttribute("accountNo");%>
       <h1>Send Money</h1> 
       <form action="SendMoneyServlet" method="post">
       <input type="hidden" name="account_id" value=<%= account_id%>>
       <input type="hidden" name="account_no" value=<%= account_no%>>
       <table>
          <tr>
              <th>Source Account No:<%= account_no%></th>
          </tr>
          <tr>   
             <th>Target Account No:<input type="text" name="target_account" required></th>
          </tr> 	
          <tr>    
             <th>Amount to Transfer:<input type="text" name="amount" required></th>
          </tr>   
          <tr> 
             <th> <input type="submit" value="Send"></th>
          </tr>    
       </table>     
       </form>
  <%}
  else
  {
	  response.sendRedirect("Login.jsp");
  }%>
</body>
</html>

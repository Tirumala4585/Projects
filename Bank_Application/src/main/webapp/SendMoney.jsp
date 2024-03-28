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
    int account_Id = Integer.parseInt((String)request.getAttribute("accountId"));
    BankDAO dao = new BankDAO();
    BankDTO dto=dao.getBalance(account_Id);	
    %>
    <h1>Send Money</h1> 
    <form action="http://localhost:8081/Bank_Application/SendMoneyServlet" method="post">
      <input type="hidden" name="account_id" value="<%= account_Id %>">
    <table>
        <tr>
            <th>Source Account No:<%= dto.getAccountNo()%></th>
        </tr>
        <tr>   
            <th>Target Account No: <input type="text" name="recipient_account" required></th>
        </tr> 
        <tr>    
            <th> Amount to Transfer: <input type="text" name="amount" required></th>
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
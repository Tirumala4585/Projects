<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bank.bankdao.BankDAO"%>
<%@ page import="com.bank.userdto.UserDTO" %> 
<%@ page import="com.bank.bankdto.BankDTO" %>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    table{
       border-collapse:collapse;
    }
 </style>   
<script>
   function validateAndRedirect(action)
   {
	   var selectedAccount=document.querySelector('input[name="selectedAccount"]:checked');
	   if(!selectedAccount)
       {
		   alert("Please select an Account before proceeding");
		   return false;
       }
	   document.getElementById('selectedAction').value=action;
	   document.getElementById('AccountForm').submit();
	   return true;
   }
</script>
</head>  
<body>
    <form id="AccountForm" action="http://localhost:8081/Bank_Application/ButtonServlet" method="post">
   <% UserDTO user = (UserDTO)session.getAttribute("user");
   if(user!=null)
   {%>
               <h1>Home Page</h1>
			   <h4>
			        Full Name:<%= user.getFullName() %>
			   </h4>
			   <h4>
			       Phno:<%= "+91-"+user.getPhno() %>
			   </h4>
	           <h4>
	              Email:<%= user.getEmail() %>
	           </h4>       
	   <h1>Account Details</h1>
     <%BankDAO dao = new BankDAO();
     List<BankDTO> acclist = dao.getAccountDetails(user.getUserName());%>
     <table border="1">
	   <thead>
	   <tr>
	     <th>Select Option</th>
	     <th>Account Id</th>
	     <th>Account No</th>
	     <th>Bank Name</th>
	     <th>IFSC Code</th>
	     <th>Current Balance</th>
	   </tr> 
	   </thead>  
	   <tbody>
    <%for(BankDTO dto:acclist){%>
    	   <tr>	
    	      <td><input type="radio" name="selectedAccount" value=<%= dto.getAccountId() %>>
    	      <td><%= dto.getAccountId()%></td>
    	      <td><%= dto.getAccountNo()%></td>
    	      <td><%= dto.getBankName()%></td>
    	      <td><%= dto.getIfscCode()%></td>
    	      <td><%out.print(dto.getCurrentBalance());%></td>
    	   </tr> 
    <% }%> 
     </tbody> 
     </table>
     <%} 
     else
     {
    	 response.sendRedirect("Login.jsp");
     }
     %>
      <input type="hidden" id="selectedAction" name="selectedAction">
     <button type="button"  onclick="validateAndRedirect('AddMoney')">Add Money</button>  	
     
     <button type="button" onclick="validateAndRedirect('SendMoney')">Send Money</button>
     </form>
      <form action="http://localhost:8081/Bank_Application/AddAccount.jsp">
        <input type="submit" value="AddAccount">	 
     </form>    
</body>
</html>
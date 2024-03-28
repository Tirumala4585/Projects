<%@ page import="com.bank.userdto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" language="java"%>
<%@ page import="com.bank.userdto.UserDTO"%> 
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
     out.print("Welcome to "+user.getUserName());
     %> 
   <form action="http://localhost:8081/Bank_Application/AddAccountServlet" method="post"> 
     <table>
       <tr>
         <th>Account No:<input type="text" name="account_no" required></th>
       </tr>
       <tr>
         <th>IFSC code:<input type="text" name="ifsc_code" required></th>
      </tr>
      <tr>
         <th>Bank Name:<input type="text" name="bank_name" required></th>
      </tr>
      <tr>
        <th>
             <label>Account Type:</label>
             <select name="account_type">
                  <option value="savings">Savings</option>
                  <option value="current">Current</option>
             </select>
        </th>
     </tr>
     <tr>
       <th>Intial Balance:<input type="text" name="intial_bal"></th>
    </tr> 
    <tr>
      <th><input type="submit" value="submit"></th>
    </tr>
   </table>
   </form> 
   <%}
     else{
           response.sendRedirect("Home.jsp");
    }%>         
</body>
</html>
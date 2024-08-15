

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.bank.bankdao.BankDAO;
import com.bank.bankdto.BankDTO;
import com.bank.tansactiondto.TransactionDTO;

/**
 * Servlet implementation class SendMoneyServlet
 */
public class SendMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMoneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             	HttpSession session=request.getSession(false);
		if(session!=null) {
		   UserDTO user = (UserDTO)session.getAttribute("user");
		   int userId = user.getUserId();
		   int sourceAccountId = Integer.parseInt(request.getParameter("account_id"));
		   String sourceAccountNo = request.getParameter("account_no");
		   String targetAccountNo = request.getParameter("target_account");
		   double amount = Double.parseDouble((String)request.getParameter("amount"));
		   
		   
		   response.setContentType("text/html");
    	   PrintWriter out = response.getWriter();
    	   
		   BankDAO dao = new BankDAO();
		   double sourceBalance = dao.getBalance(sourceAccountId);
		   
		   
	       if(sourceBalance>=amount)
	       {	   
	           sourceBalance=sourceBalance-amount;
	           int targetAccountId= dao.getAccountId(targetAccountNo);
	           double targetBalance = dao.getBalance(targetAccountId);
	           targetBalance = targetBalance+amount;
	           if(dao.updateBalance(sourceBalance, sourceAccountId)&&dao.updateBalance(targetBalance, targetAccountId))
	           {
	              TransactionDTO sourcetransdto = new TransactionDTO();  
	              sourcetransdto.setSourceAccountNo(sourceAccountNo);
		          sourcetransdto.setTargetAccountNo(targetAccountNo);
		          sourcetransdto.setTransactionType("Debit");
		          sourcetransdto.setAmount(amount);
		          sourcetransdto.setBalance(dao.getBalance(sourceAccountId));
		          
		          
		          TransactionDTO targettransdto = new TransactionDTO();
		          targettransdto.setSourceAccountNo(targetAccountNo);
		          targettransdto.setTargetAccountNo(sourceAccountNo);
		          targettransdto.setTransactionType("Credit");
		          targettransdto.setAmount(amount);
		          targettransdto.setBalance(dao.getBalance(targetAccountId));
		          if(dao.setTransactionDetails(sourcetransdto)&&dao.setTransactionDetails(targettransdto))
		          {
		        	 BankDAO bankdao = new BankDAO();
		        	 List<BankDTO> acclist = bankdao.getAccountDetails(userId);
		        	 request.setAttribute("accountdetails",acclist);
                     RequestDispatcher rd = request.getRequestDispatcher("Home.jsp"); 
                     rd.forward(request, response);
		          } 
		          else {
		        	  out.println("Transaction iS Failed");
		          }
	           }
	           else{
	        	   out.println("Transaction is Failed");
		        	  
		          }
	       }      
	     else {
	    	 out.println("Insufficient Balance");
	     }
      }
	else {
		response.sendRedirect("Login.jsp");
	}
		
	}
}

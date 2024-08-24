

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class AddMoneyServlet
 */
public class AddMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMoneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 HttpSession session = request.getSession(false);
		if(session!=null) {
		UserDTO user = (UserDTO)session.getAttribute("user");
		int userId = user.getUserId();
		int accountId = Integer.parseInt(request.getParameter("account_Id"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String accountNo=request.getParameter("account_no");
		System.out.println(accountNo);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		BankDAO dao = new BankDAO();
		double current_balance=dao.getBalance(accountId);
		current_balance=current_balance+amount;
		if(dao.updateBalance(current_balance,accountId))
		{
		    TransactionDTO transdto = new TransactionDTO();
    	            transdto.setSourceAccountNo(accountNo);
    	            transdto.setTargetAccountNo(accountNo);
    	            transdto.setTransactionType("Credit");
    	            transdto.setAmount(amount);
    	            transdto.setBalance(dao.getBalance(accountId));
    	            transdto.setAccountId(accountId);
		    if(dao.setTransactionDetails(transdto))
		    {
			   BankDAO bankdao = new BankDAO();
	    	           List<BankDTO> acclist = bankdao.getAccountDetails(userId);
	    	           request.setAttribute("accountdetails", acclist);
			   RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			   rd.forward(request,response);
		    }
		    else {
		    	out.println("Balance not sucessfully not updated");
		    }
		}   
		else {
			
			out.print("Balance not successfully not updated");
		}
	 }
	else {	
		response.sendRedirect("Login.jsp");
	}	
		
   }

}

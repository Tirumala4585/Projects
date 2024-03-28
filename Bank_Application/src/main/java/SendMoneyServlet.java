

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
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		double amount = Double.parseDouble((String)request.getParameter("amount"));
		//String fromAccountNo = request.getParameter("account_no");
		String toAccountNo = request.getParameter("recipient_account");
		response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
		BankDAO dao = new BankDAO();
		BankDTO dto = dao.getBalance(accountId);
	    double balance	= dto.getCurrentBalance();
	    balance=balance-amount;
	    dto.setCurrentBalance(balance);
	    	TransactionDTO transdto = new TransactionDTO();
	    	transdto.setTransactionId(accountId);
	    	//transdto.setSourceAccountNo(fromAccountNo);
	    	transdto.setTargetAccountNo(toAccountNo);
	    	transdto.setAmount(amount);
	    	if(dao.updateBalance(dto,accountId) && dao.setTransactionDetails(transdto))
		    { 
		    	out.print("Transaction successfull");
		    }
		    else 
		    {
		    	out.print("Transaction is failed");
		    }
	    
	    
	}
}

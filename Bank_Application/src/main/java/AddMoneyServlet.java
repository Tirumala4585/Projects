

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
		int accountId = Integer.parseInt(request.getParameter("accountId"));
		double balance = Double.parseDouble(request.getParameter("amount"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		BankDAO dao = new BankDAO();
		BankDTO dto = dao.getBalance(accountId);
		double current_balance=dto.getCurrentBalance();
		current_balance=current_balance+balance;
		dto.setCurrentBalance(current_balance);
		TransactionDTO transdto = new TransactionDTO();
    	transdto.setTransactionId(accountId);
    	//transdto.setSourceAccountNo(fromAccountNo);
    	transdto.setAmount(balance);
		if(dao.updateBalance(dto,accountId) && dao.setTransactionDetails(transdto))
		{
			response.sendRedirect("Home.jsp");
		}
		else {
			out.print("Balance not successfully not updated");
		}
			
		}
		
	}



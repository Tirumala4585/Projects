
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.bank.bankdao.BankDAO;
import com.bank.bankdto.BankDTO;
import com.bank.userdto.UserDTO;




public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccountServlet() {
        super();
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UserDTO user = (UserDTO)session.getAttribute("user");
		String userName=user.getUserName();
		String accountNo=request.getParameter("account_no");
		String bankName=request.getParameter("bank_name");
		String ifscCode=request.getParameter("ifsc_code");
		String accountType=request.getParameter("account_type");
		double currBal=Double.parseDouble(request.getParameter("intial_bal"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		BankDAO dao = new BankDAO();
		BankDTO dto = new BankDTO();
		dto.setUserName(userName);
		dto.setAccountNo(accountNo);
	    dto.setBankName(bankName);
	    dto.setIfscCode(ifscCode);
	    dto.setAccountType(accountType);
	    dto.setCurrentBalance(currBal);
	    if(dao.setAccountDetails(dto)) {
		    response.sendRedirect("http://localhost:8081/Bank_Application/Home.jsp");
	    }
	    else
	    {
	    	out.print("Your Accoutn details are not Added");
	    }
	}

}

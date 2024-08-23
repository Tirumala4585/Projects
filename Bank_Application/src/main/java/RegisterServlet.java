

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.bank.bankdao.BankDAO;
import com.bank.userdto.UserDTO;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName=request.getParameter("user_name");
		String password=request.getParameter("user_pass");
		String fullName=request.getParameter("full_name");
		String phoneNo=request.getParameter("user_phno");
		String email=request.getParameter("user_email");
		String address=request.getParameter("user_address");
		
		BankDAO dao = new BankDAO();
		UserDTO userdto = new UserDTO();
		userdto.setUserName(userName);
		userdto.setPassword(password);
		userdto.setFullName(fullName);
		userdto.setPhno(phoneNo);
		userdto.setEmail(email);
		userdto.setAddress(address);
		if(dao.setUserDetails(userdto)) {
			response.sendRedirect("Login.jsp");
		}
		else {
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/html");
			out.print("Your registration is failed");
			RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
			rd.include(request, response);
			doGet(request,response);
		}
	}

}

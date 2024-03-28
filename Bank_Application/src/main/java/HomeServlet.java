

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.bank.bankdao.BankDAO;
import com.bank.bankdto.BankDTO;
import com.bank.userdto.UserDTO;



public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HomeServlet() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session!=null) {
		UserDTO user = (UserDTO) request.getAttribute("user");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String str="<html>\r\n"
				+ "  <body>\r\n"
				+ "    <h1>Home Page</h1>\r\n"
				+ "    <h4>Full Name:"+user.getFullName()+"</h4>\r\n"
				+ "    <h4>Phno:"+user.getPhno()+"</h4>\r\n"
				+ "    <h4>Email:"+user.getEmail()+"</h4>\r\n"
				+ " </body>\r\n"
				+ "</html>";
		String acc = "<html>\r\n"
				+ "   <body>\r\n"
				+ "     <form action=\"http://localhost:8081/Bank_Application/\">\r\n"
				+ "       <input type=\"submit\" value=\"Add Account\">\r\n"
				+ "     </form>\r\n"
				+ "   </body>\r\n"
				+ "</html>";
		out.print(str);
		out.print(acc);
		 StringBuffer stb = new StringBuffer();
		 BankDAO dao = new BankDAO();
		 List<BankDTO> acclist = dao.getBankDetails(user.getUserName()); 
		 stb.append("<form>");
		       for(BankDTO dto:acclist) {
		    	   stb.append("<html>\r\n"
							+ "  <body>\r\n"
		    			    + "  <input type=\"radio\" name=\"account_details\">\r\n"
							+ "  <h1>Account Details<h1>\r\n"
							+ "  <table border=\"2\">"
							+ "  <tr>"
							+ "  <th>User Name:</th>" 
							+ "  <th>Account Id</th>\r\n"
							+ "  <th>Bank Name</th>"
							+ "  <th>IFSCCode</th>"
							+ "  <th>AccountNo</th>"
							+ "  <th>AccountType</td>"
							+ "  <th>CurrentBalance</th>"
							+ "  </tr>"
							+ "  <tr>"
						    + "  <td>"+user.getUserName()+"</td>\r\n"
							+ "  <td>"+dto.getAccountId()+"</td>\r\n"
							+ "  <td>"+dto.getBankName()+"</td>\r\n"
							+ "  <td>"+dto.getIfscCode()+"</td>\r\n"
							+ "  <td>"+dto.getAccountNo()+"</td>\r\n"
							+ "  <td>"+dto.getAccountType()+"</td>\r\n"
							+ "  <td>"+dto.getCurrentBalance()+"</td>\r\n"
							+"   </tr>"
							+ " </body>\r\n"
							+ "</html>");
		          }
		  stb.append("</form>"); 
		  out.print(stb);
		}
		else {
			response.sendRedirect("http://localhost:8082/TMF_Banking/AddAccount.jsp");
		}
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		}
		
	}

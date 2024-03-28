import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ButtonServlet
 */
public class ButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ButtonServlet() {
        super();  
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectedAction = request.getParameter("selectedAction");
		String account_Id = request.getParameter("selectedAccount");
		request.setAttribute("accountId", account_Id);
		if(selectedAction.equals("AddMoney"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("AddMoney.jsp");
			rd.forward(request,response);
		}
		else if(selectedAction.equals("SendMoney"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("SendMoney.jsp");
			rd.forward(request, response);
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("Login is failed");
	    }
	}
}
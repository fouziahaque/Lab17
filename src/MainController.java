

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.db.CustomerDB;
import com.gc.model.Customer;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		CustomerDB customerDB = new CustomerDB(); 
		
		switch(action){
		case "add":{
			Customer customer = new Customer();
			customer.setEmail(request.getParameter("emailId"));
			customer.setLastName(request.getParameter("lastName"));
			customer.setFirstName(request.getParameter("firstName"));
			
			
			List<Customer>list = customerDB.getCustomerList();
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/MyJsp.jsp").forward(request, response);
			
			try {
				customerDB.addCustomer(customer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		case "list":{
			List<Customer>list = customerDB.getCustomerList();
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/MyJsp.jsp").forward(request, response);
			break;
		}
		
		case "delete":{
			String emailId = request.getParameter("emailId");
			
			
			try {
				customerDB.deleteCustomer(emailId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			List<Customer>list = customerDB.getCustomerList();
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/MyJsp.jsp").forward(request, response);
			
			break;
		}
		}
		
		
	}

}

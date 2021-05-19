package idv.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idv.customer.model.CustomerService;
import idv.customer.model.CustomerVO;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		if(action==null) {
			log("no parameters for action");
			return;
		}
		CustomerService cusSvc = new CustomerService();
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		switch(action) {
			case "register":
				CustomerVO customer = cusSvc.insertCustomer(name, password, email);
				//customer used for check registered already
				log("add -"+customer);
				res.sendRedirect(req.getContextPath()+"/login.jsp");
				break;
			case "login":
				List<CustomerVO> list = cusSvc.getAllCustomer();
//				list.forEach(c->System.out.println(c));
				//judge name 
				int index =0; 
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getName().equals(name)) {
						//if matched , save index to check password
						index=i;
					}
				}
				
				if(index==0) {
					log("username is mismatched : your input - "+name);
					res.sendRedirect(req.getContextPath()+"/login.jsp");
					return;
				}	
						
				//judge name & password
				boolean isPasswordCorrect = list.get(index).getPassword().equals(password);
				if(!isPasswordCorrect) {
					log("username & password is mismatched : your input name - "+name +"\tpassword - "+password);
					res.sendRedirect(req.getContextPath()+"/login.jsp");
					return;
				}
					req.getSession().setAttribute("customer", list.get(index));
					res.sendRedirect(req.getContextPath()+"/index.jsp");
				break;
			case "logout":
				req.getSession().removeAttribute("customer");
				log("logout");
				res.sendRedirect(req.getContextPath()+"/login.jsp");
				
				break;
			
			default:
				log("undefined actions");
		}
		
		
	}

}

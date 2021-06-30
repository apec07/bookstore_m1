package idv.customer.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.*;

import idv.customer.model.CustomerService;
import idv.customer.model.CustomerVO;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Logger LOGGER = null;
	
	public CustomerServlet() {
		
	}
	
	@Override
	public void destroy() {
		LOGGER.traceExit();
		LOGGER =null ;
	}

	@Override
	public void init() throws ServletException {
		LOGGER = LogManager.getLogger(this.getClass());
		LOGGER.traceEntry();
	}

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
			LOGGER.info("no parameters for actions");
			return;
		}
		CustomerService cusSvc = new CustomerService();
	
		LOGGER.info("action = "+action);
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		switch(action) {
			case "register":
				//Ajax ~ Check UserName
				//customer used for check registered already
				String email = req.getParameter("email");
				if(password.isEmpty()) {
					List<CustomerVO> list = cusSvc.getAllCustomer();
					boolean isUserExist = false;
					boolean isEmailExist = false;
					for(CustomerVO customer : list) {
						isUserExist = name.equals(customer.getName());
						isEmailExist = email.equals(customer.getEmail());
						if(isUserExist==true) break; //
						if(isEmailExist==true) break; // 
					}
					LOGGER.info("isUserExist "+isUserExist);
					req.getSession().setAttribute("isUserExist",isUserExist);
					req.getSession().setAttribute("isEmailExist",isEmailExist);
					res.getWriter().append(""+isUserExist);
					res.getWriter().close();
					return;
				}
				CustomerVO customer = cusSvc.insertCustomer(name, password, email);
				
				res.sendRedirect(req.getContextPath()+"/login.jsp");
				break;
			case "login":
				List<CustomerVO> list = cusSvc.getAllCustomer();

				//judge name 
				int index =-1; 
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getName().equals(name)) {
						//if matched , save index to check password
						index=i;
					}
				}
				//error handler
				//clean error message
				req.getSession().removeAttribute("errorMsgs");
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.getSession().setAttribute("errorMsgs", errorMsgs);
				if(index==-1) {
					LOGGER.info("username is mismatched : your input - "+name);
					errorMsgs.put("name_error", name +" is mismatched");
//					log("username is mismatched : your input - "+name);
					res.sendRedirect(req.getContextPath()+"/login.jsp");
					return;
				}	
						
				//judge name & password
				boolean isPasswordCorrect = list.get(index).getPassword().equals(password);
				if(!isPasswordCorrect) {
					LOGGER.info("username & password is mismatched : your input name - "+name +"\tpassword - "+password);
					errorMsgs.put("pass_error", password + " is incorrect");
//					log("username & password is mismatched : your input name - "+name +"\tpassword - "+password);
					req.getSession().setAttribute("customerName", name);
					res.sendRedirect(req.getContextPath()+"/index.jsp");
					return;
				}
					req.getSession().setAttribute("customer", list.get(index));
					LOGGER.info("login success");
					res.sendRedirect(req.getContextPath()+"/index.jsp");
				break;
			case "logout":
				req.getSession().removeAttribute("customer");
				LOGGER.info("logout");
//				log("logout");
				res.sendRedirect(req.getContextPath()+"/login.jsp");
				
				break;
			
			default:
				LOGGER.error("undefined actions");
//				log("undefined actions");
		}
		
		
	}

}

package idv.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import idv.customer.model.CustomerService;
import idv.customer.model.CustomerVO;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Logger LOGGER = null;
	public Gson gson = null;
	
	public CustomerServlet() {
		
	}
	
	@Override
	public void destroy() {
		LOGGER.traceExit();
		LOGGER = null ;
		gson = null;
	}

	@Override
	public void init() throws ServletException {
		LOGGER = LogManager.getLogger(this.getClass());
		LOGGER.traceEntry();
		gson = new Gson();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String loginUrl = req.getContextPath()+"/login.jsp";
		String indexUrl = req.getContextPath()+"/index.jsp";
		String action = req.getParameter("action");
		if(action==null) {
			LOGGER.info("no parameters for actions");
			res.sendRedirect(loginUrl);
			return;
		}
		CustomerService cusSvc = new CustomerService();
	
		LOGGER.info("action = "+action);
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		PrintWriter out = res.getWriter();
		switch(action) {
			case "register":
				
				CustomerVO customerReg = cusSvc.insertCustomer(name, password, email);
				res.sendRedirect(loginUrl);
				
				break;
			case "login":
				List<CustomerVO> list = cusSvc.getAllCustomer();
				//judge name 
				Gson gson = new Gson();
				String listGson = gson.toJson(list);
				LOGGER.info(listGson);
				
//				CustomerVO customerLog = findUsingIterator(name,list);
				//or use Java 8 Stream API
				CustomerVO customerLog = list.stream().filter(cus -> name.equals(cus.getName())).findAny().orElse(null);
				//error handler
				//clean error message
				req.getSession().removeAttribute("errorMsgs");
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.getSession().setAttribute("errorMsgs", errorMsgs);
				if(customerLog==null) {
					LOGGER.info("username is mismatched : your input - "+name);
					errorMsgs.put("name_error", name +" is mismatched");
					res.sendRedirect(loginUrl);
					return;
				}

				//judge name & password
				boolean isPasswordCorrect = customerLog.getPassword().equals(password);
				if(!isPasswordCorrect) {
					LOGGER.info("username & password is mismatched : your input name - "+name +"\tpassword - "+password);
					errorMsgs.put("pass_error", password + " is incorrect");
					req.getSession().setAttribute("customerName", name);
					res.sendRedirect(indexUrl);
					return;
				}
					req.getSession().setAttribute("customer", customerLog);
					LOGGER.info("login success");
					LOGGER.info(gson.toJson(customerLog));
					res.sendRedirect(indexUrl);
				break;
			case "logout":
				req.getSession().removeAttribute("customer");
				LOGGER.info("logout");
//				log("logout");
				res.sendRedirect(loginUrl);
				
				break;
			
			case "checkName":
				//Ajax ~ Check UserName
				boolean isUserExist = false;
				List<String> names = cusSvc.getAllNames();
				if(name.length()>0) {
					for(String eachName : names ) {
						isUserExist = name.equals(eachName);
						if(isUserExist==true) break; // duplicated
					}
				}else {
					return;
				}
					LOGGER.info("isUserExist "+isUserExist);
				
					res.getWriter().append(""+isUserExist); //true or false to front-end
					res.getWriter().close();
				
				break;
				
			case "checkEmail":
				boolean isEmailExist = false;
				List<String> mails = cusSvc.getAllEmails();
				if(email.length()>0) {
					for(String eachMail : mails ) {
						LOGGER.info(eachMail);
						if(email.equals(eachMail)) {
							isEmailExist = true;
						}
					}
				}
				LOGGER.info("isEmailExist "+isEmailExist);
				res.getWriter().append(""+isEmailExist); //true or false to front-end
				res.getWriter().close();
				
				break;
			default:
				LOGGER.error("undefined actions");
//				log("undefined actions");
		}
		
		
	}
	public CustomerVO findUsingIterator(
			  String name, List<CustomerVO> customers) {
			    Iterator<CustomerVO> iterator = customers.iterator();
			    while (iterator.hasNext()) {
			    	CustomerVO customer = iterator.next();
			        if (customer.getName().equals(name)) {
			            return customer;
			        }
			    }
			    return null;
	}

}

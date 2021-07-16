package idv.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import idv.customer.model.CustomerService;
import idv.customer.model.CustomerVO;


public class BackCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Logger LOGGER = null;
	public Gson gson = null;
	
    public BackCustomerServlet() {
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


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if(action==null) {
			LOGGER.info("no parameters for actions");
			res.sendRedirect(req.getContextPath()+"/backend/customer/customerlist.jsp");
			return;
		}
		CustomerService cusSvc = new CustomerService();
		
		LOGGER.info("action = "+action);
		switch(action) {

		case "listLike":
			String like = req.getParameter("filter").trim();
			if(like.isEmpty()) {
				LOGGER.info("未輸入或是空");
				req.setAttribute("isSome", null);
				req.getRequestDispatcher("/backend/customer/customerlist.jsp").forward(req, res);
				return;
			}
			LOGGER.info("已輸入，進行判斷 =" + like);
			List<CustomerVO> list = cusSvc.getSomeCustomer(like);
			req.setAttribute("isSome", list);
			req.setAttribute("inputStr", like);
			req.getRequestDispatcher("/backend/customer/customerlist.jsp").forward(req, res);;
			break;
			
			
			default:
				LOGGER.error("undefined actions");
				res.sendRedirect(req.getContextPath()+"/backend/customer/customerlist.jsp");
		}

		
	}

}

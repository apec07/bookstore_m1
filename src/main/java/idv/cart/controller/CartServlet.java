package idv.cart.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;

import com.google.gson.Gson;
import idv.cart.model.CartVO;
import idv.customer.model.CustomerVO;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson ;
    private StringBuffer gsonStr;
    private Logger LOGGER;

    public CartServlet() {
        super();
    }


	@Override
	public void destroy() {
		LOGGER.traceExit();
		LOGGER = null;
	}


	@Override
	public void init() throws ServletException {
		LOGGER = LogManager.getLogger(this.getClass());
		LOGGER.traceEntry();
		gson = new Gson();
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		//get Current User
		CustomerVO customer = (CustomerVO)session.getAttribute("customer");
		if(customer==null) {
			LOGGER.info("redirect url to "+req.getContextPath()+"/login.jsp");
			res.sendRedirect(req.getContextPath()+"/login.jsp");
			return;
		}
		Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
		String method = req.getParameter("action");
		
		if(method==null ||method.trim().length()==0) {
			LOGGER.warn("no parameters for action");
			res.sendRedirect(req.getContextPath());//#至Cart list
			return;
		}
		if(method.equals("cart")) {
			LOGGER.info("Add to Cart");
			CartVO newCart = new CartVO();
			
			newCart.setCustomer_no(customer.getNo());
			newCart.setProduct_no(Integer.valueOf(req.getParameter("prod_no")));
			//Retrived from DB?
			newCart.setProd_name(req.getParameter("prod_name"));
			newCart.setProd_price(Integer.valueOf(req.getParameter("prod_price")));
			newCart.setCart_mount(Integer.valueOf(req.getParameter("quantity")));
			LOGGER.info("newCart - "+newCart.getProd_name());
			// check buylist if null or add before
			if (buylist == null) {
				buylist = new Vector<CartVO>();
				buylist.add(newCart);
				LOGGER.info("First Cart add");
//				LOGGER.info(buylist instanceof Vector<?>);
			} else {
				if(buylist.contains(newCart)){
					 CartVO innerCarVO = buylist.get(buylist.indexOf(newCart));
					 innerCarVO.setCart_mount(innerCarVO.getCart_mount()+ newCart.getCart_mount());
					 LOGGER.info("the same product+1");
				}else{
					buylist.add(newCart);
					LOGGER.info("different product");
				}
			}
			
			LOGGER.info(gson.toJson(buylist));
			session.setAttribute("shoppingcart", buylist);
			LOGGER.info("Total Cart size- "+buylist.size());
//			req.getRequestDispatcher("/").forward(req, res);
			res.sendRedirect(req.getContextPath());
			
		}else {
			LOGGER.info("undifned");
		}
		
	
		
	}

}

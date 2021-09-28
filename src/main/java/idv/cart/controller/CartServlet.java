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
		doPost(req,res);
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
			Integer product_no = Integer.valueOf(req.getParameter("prod_no"));
			Integer cart_mount = Integer.valueOf(req.getParameter("quantity"));
			newCart.setCustomer_no(customer.getNo());
			newCart.setProduct_no(product_no);
			newCart.setCart_mount(cart_mount);
			// check buylist if null or add before
			if (buylist == null) {
				buylist = new Vector<CartVO>();
				buylist.add(newCart);
			} else {
				if(buylist.contains(newCart)){
					 CartVO innerCarVO = buylist.get(buylist.indexOf(newCart));
					 innerCarVO.setCart_mount(innerCarVO.getCart_mount()+ newCart.getCart_mount());
				}else{
					buylist.add(newCart);
				}
			}
			session.setAttribute("shoppingcart", newCart);
			LOGGER.info("new Cart no - "+newCart.getProduct_no());
			LOGGER.info("Total Cart size- "+buylist.size());
			res.sendRedirect(req.getContextPath());
			
		}else {
			LOGGER.info("undifned");
		}
		
	
		
	}

}

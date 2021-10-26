package idv.ord.controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import idv.cart.model.CartVO;
import idv.customer.model.CustomerService;
import idv.ord.model.OrdService;
import idv.ord.model.OrdVO;
import idv.ord_details.model.Ord_detailsDAO;
import idv.ord_details.model.Ord_detailsVO;
import idv.utli.IdGen;
import idv.customer.model.CustomerVO;

/**
 * Servlet implementation class OrdServlet
 */
public class OrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson ;
    private StringBuffer gsonStr;
    private Logger LOGGER;
    private CustomerService cusSvc = new CustomerService();

    public OrdServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("action");
		if(method==null ||method.trim().length()==0) {
			LOGGER.warn("no parameters for action");
			res.sendRedirect(req.getContextPath()); //#home
			return;
		}
		if (method.equals("newOrder")) {
			LOGGER.info("===============FROM AddOneOrderPage============");
			 /*********************************1.接收請求參數***********************************/
//			String ord_no = String.valueOf(req.getParameter("ord_no"));
			String ord_no = IdGen.getID();
			LOGGER.info("ord_no - "+ord_no);
			CustomerVO thisCustomer = (CustomerVO)req.getSession().getAttribute("customer");
			Integer customer_no = thisCustomer.getNo();
			Integer ord_status = Integer.valueOf(req.getParameter("ord_status"));
			Integer prod_no = Integer.valueOf(req.getParameter("product_no"));
			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			
			String receiver = String.valueOf(req.getParameter("receiver"));
			String rec_phone = String.valueOf(req.getParameter("rec_phone"));
			String rec_zip = String.valueOf(req.getParameter("rec_zip"));
			String rec_address = String.valueOf(req.getParameter("rec_address"));
	
			String ord_totalStr = String.valueOf(req.getParameter("ord_total"));
			Integer ord_total = Integer.parseInt(ord_totalStr);
			java.sql.Timestamp ord_datetime = new java.sql.Timestamp(System.currentTimeMillis());
			Vector<CartVO> cartlist = (Vector<CartVO>)req.getSession().getAttribute("shoppingcart");
			if(cartlist==null || cartlist.size()==0) {
				LOGGER.info("cartlist exception");
				return;
			}
			OrdVO newOrd = new OrdVO();
			newOrd.setCustomer_no(customer_no);
			newOrd.setOrd_no(ord_no);
			newOrd.setOrd_status(ord_status);
			newOrd.setOrd_datetime(ord_datetime);
			
			OrdService ordSvc = new OrdService();
			ordSvc.insertOrd(ord_no, customer_no, ord_status, receiver, rec_phone, rec_zip, rec_address, ord_total, cartlist);
			//removed cartList
			req.getSession().removeAttribute("shoppingcart");
			res.sendRedirect(req.getContextPath()); //back
			
		}
		if(method.equals("listDetails_ByOrdno")) {
			String requestUrl = req.getParameter("requestURL");
			String ord_noStr = req.getParameter("ord_no");
			Ord_detailsDAO dao = new Ord_detailsDAO();
			List<Ord_detailsVO> ordByCus = dao.getMyOrd_detail(ord_noStr);
			req.setAttribute("listOrderDetails_ByOrdno", ordByCus);
			req.getRequestDispatcher(requestUrl).forward(req, res);
			
		}
	  
	}

}

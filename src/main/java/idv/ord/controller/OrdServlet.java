package idv.ord.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import idv.ord.model.OrdService;
import idv.ord.model.OrdVO;

/**
 * Servlet implementation class OrdServlet
 */
public class OrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson ;
    private StringBuffer gsonStr;
    private Logger LOGGER;

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
			LOGGER.info("即將導入 ----"+req.getContextPath()+"/frontend/addOneOrder.jsp");
			res.sendRedirect(req.getContextPath()+"/frontend/addOneOrder.jsp"); //#至Cart list
			return;
		}
		if (method.equals("AddOneOrder")) {
			LOGGER.info("===============FROM AddOneOrderPage============");
			 /*********************************1.接收請求參數***********************************/
			String ord_no = String.valueOf(req.getParameter("ord_no"));
			Integer ord_status = Integer.valueOf(req.getParameter("ord_status"));
			Integer prod_no = Integer.valueOf(req.getParameter("PRODUCT_NO"));
			Integer quantity = Integer.valueOf(req.getParameter("quantity"));
			
			java.sql.Timestamp ord_datetime = java.sql.Timestamp.valueOf(req.getParameter("ord_date"));
			
			OrdVO newOrd = new OrdVO();
			newOrd.setCustomer_no(1); //fake user
			newOrd.setOrd_no(ord_no);
			newOrd.setOrd_status(ord_status);
			
			newOrd.setOrd_datetime(ord_datetime);
			
			OrdService ordSvc = new OrdService();
//			ordSvc.insertOrd(ord_no, 1, ord_status, "receiver", "rec_phone", "rec_zip", "rec_address", ord_total, cartlist)
			res.sendRedirect(req.getContextPath()+"/frontend/addOneOrder.jsp"); //back
			
		}
	  
	}

}

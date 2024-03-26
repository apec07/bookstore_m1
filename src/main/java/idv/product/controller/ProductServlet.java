package idv.product.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import idv.product.model.ProductService;
import idv.product.model.ProductVO;

@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Logger LOGGER = LogManager.getLogger(this.getClass());

    public ProductServlet() {
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("action");
		
		if(method==null ||method.trim().length()==0) {
			LOGGER.info("no parameters for action");
			res.sendRedirect(req.getContextPath()+"/backend/");
			return;
		}
		LOGGER.info("method "+method);
		//page forward (all / one)
		if(method.contains("selectPage")) {
			String usePage = req.getParameter("usePage");
			LOGGER.info("usePage - "+usePage);
			switch(usePage) {
				case "all":
					req.setAttribute("usePage", "/backend/product/showAllProd.jsp");
					break;
					
				case "one":
					req.setAttribute("usePage", "/backend/product/showOneProd.jsp");
					break;
					
				default:
					LOGGER.info("undefined page");
						
			}
			req.getRequestDispatcher("/backend/index.jsp").forward(req, res);
			LOGGER.info("page forward");
			return;
		}
		//Add one
		if(method.contains("AddOne")) {
			LOGGER.info("Add One Page");
			Integer addCate_no = new Integer(req.getParameter("category_no"));
			String addProd_name = req.getParameter("prod_name");
			String addProd_intro = req.getParameter("prod_introduce");
			Integer addProd_price = new Integer(req.getParameter("prod_price"));
			Integer addProd_stock = new Integer(req.getParameter("prod_stock"));
			Integer addProd_status = new Integer(req.getParameter("prod_status"));
			InputStream is = req.getPart("prod_pic").getInputStream();
			
			byte[] addProd_pic = null;
			if(is.available()!=0 ){
				addProd_pic = new byte[is.available()];
				is.read(addProd_pic);
				is.close();
			} 
			ProductVO addProd = new ProductService().insertProd(addCate_no, addProd_name, addProd_price, addProd_intro, addProd_stock, addProd_status, addProd_pic);
			LOGGER.info("add Product - "+addProd);
			res.sendRedirect(req.getContextPath()+"/backend/");
			return;
		}
		//Go Product page for Update  
		if(method.contains("getOneForUpdate")){
			LOGGER.info("Update One Page");
			String prod_no = req.getParameter("prod_no");
			LOGGER.info("prod_no = "+prod_no);
			ProductService prod_svc = new ProductService();
			ProductVO prod = prod_svc.getOneProd(new Integer(prod_no));
			req.setAttribute("oneProd", prod);
			req.getRequestDispatcher("/backend/product/showOneProd.jsp").forward(req, res);
			return;
		}else if(method.contains("updateOne")) {
			// getSession attribute
			ProductVO prod = (ProductVO)req.getSession().getAttribute("oneProd");
			LOGGER.info("getFrom updateOne - "+prod);
			Integer prod_no = prod.getProduct_no();
			String upProd_name = req.getParameter("prod_name");
			String upProd_intro = req.getParameter("prod_introduce");
			Integer upProd_price = new Integer(req.getParameter("prod_price"));
			Integer upProd_stock = new Integer(req.getParameter("prod_stock"));
			Integer upProd_status = new Integer(req.getParameter("prod_status"));
			Integer upCate_no = new Integer(req.getParameter("category_no"));
		
			InputStream is = req.getPart("prod_pic").getInputStream();
			
			byte[] prod_pic = prod.getProd_pic();
			// Upload image if exists 
			if(is.available()!=0 ){
				prod_pic = new byte[is.available()];
				is.read(prod_pic);
				is.close();
			} 
			
			ProductVO upProd = new ProductService().updateProd(prod_no,upCate_no, upProd_name, upProd_price, upProd_intro, upProd_stock, upProd_status, prod_pic);
			
			LOGGER.info("update Product - "+upProd);
			res.sendRedirect(req.getContextPath()+"/backend/");
//			req.getRequestDispatcher("/backend/index.jsp").forward(req, res);
			return;
		}
		//Delete select One product
		if(method.contains("getOneForDelete")) {
			LOGGER.info("Delete One Product");
			String prod_no = req.getParameter("prod_no");
			LOGGER.info("prod_no = "+prod_no);
			ProductService prod_svc = new ProductService();
			boolean isDelete = prod_svc.deleteProd(new Integer(prod_no));
			req.getSession().setAttribute("isDelete", isDelete);
			res.sendRedirect(req.getContextPath()+"/backend/");
			return;
		}
		LOGGER.info("method not get");
	}

}

package idv.product.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idv.product.model.ProductService;
import idv.product.model.ProductVO;


public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProductServlet() {
        super();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("action");
		
		if(method==null ||method.trim().length()==0) {
			log("no parameters for action");
			res.sendRedirect(req.getContextPath()+"/backend/index.jsp");
			return;
		}
		log("method "+method);
		//商品單一 查詢/更新
		if(method.contains("getOneForUpdate")){
			String prod_no = req.getParameter("prod_no");
			log("prod_no = "+prod_no);
			ProductService prod_svc = new ProductService();
			ProductVO prod = prod_svc.getOneProd(new Integer(prod_no));
			log("prod = "+prod);
			req.setAttribute("oneProd", prod);
			req.getRequestDispatcher("/backend/product/showOneProd.jsp").forward(req, res);
			return;
		}else if(method.contains("updateOne")) {
			Integer prod_no = new Integer(req.getParameter("prod_no"));
			String upProd_name = req.getParameter("prod_name");
			String upProd_intro = req.getParameter("prod_introduce");
			Integer upProd_price = new Integer(req.getParameter("prod_price"));
			Integer upProd_stock = new Integer(req.getParameter("prod_stock"));
			Integer upCate_no = new Integer(req.getParameter("category_no"));
		
//			InputStream is = req.getPart("prod_pic").getInputStream();
			ProductVO upProd = new ProductService().updateProd(prod_no,upCate_no, upProd_name, upProd_price, upProd_intro, upProd_stock, 1, null);
			
			log("update Product - "+upProd);
			req.getRequestDispatcher("/backend/product/showAllProd.jsp").forward(req, res);
			return;
		}
		log("method not get");
	}

}

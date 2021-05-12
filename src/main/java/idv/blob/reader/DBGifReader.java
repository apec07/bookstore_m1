package idv.blob.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idv.product.model.ProductService;
import idv.product.model.ProductVO;


public class DBGifReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DBGifReader() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
		Integer prod_no = new Integer(req.getParameter("product_no"));
		ProductService prod_svc = new ProductService();
		ProductVO product = prod_svc.getOneProd(prod_no);
		out.write(product.getProd_pic());
		}catch(Exception ex) {
			InputStream is = new FileInputStream(getServletContext().getRealPath("/resources/NoData/nopic.jpg"));
			byte[] buf = new byte[is.available()];
			is.read(buf);
			out.write(buf);
			is.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}

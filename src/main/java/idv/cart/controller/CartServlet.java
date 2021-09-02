package idv.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.*;

import com.google.gson.Gson;

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
		String method = req.getParameter("action");
		if(method==null ||method.trim().length()==0) {
			LOGGER.warn("no parameters for action");
			res.sendRedirect(req.getContextPath());//#至Cart list
			return;
		}
	}

}

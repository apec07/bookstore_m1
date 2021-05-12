package idv.backend.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idv.member.model.MemberService;
import idv.member.model.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			
		}
		//登入方法
		if(method.contains("backend_login")) {
			String memNo = req.getParameter("memNo");
			MemberService memSvc = new MemberService();
			Integer no=0;
			try {
				 no = new Integer(memNo);
			}catch (NumberFormatException ex) {
			}
			log("no - "+no);
			if(no ==0) {
				return ;
			}
			MemberVO member = memSvc.getOneMember(no);
			log("member - "+member);
			req.getSession().setAttribute("backend", member); //後者蓋前者
			res.sendRedirect(req.getContextPath()+"/backend/index.jsp");
		//登出
		}else if(method.contains("logout")){
			req.getSession().removeAttribute("backend");
			res.sendRedirect(req.getContextPath()+"/backend/login.jsp");
		}
		
	}

}

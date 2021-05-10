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


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("action");
		if(method.isEmpty()) {
			return ;
		}
		//登入方法
		if(method.contains("backend_login")) {
			String memNo = req.getParameter("mem_no");
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
//			req.getRequestDispatcher("/backend/index.jsp").forward(req, res);
			res.sendRedirect(req.getContextPath()+"/backend/index.jsp");
		}
		
	}

}

package idv.member.model;

import java.util.List;

public class MemberService {
	
	private MemberImp dao = null;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO getOneMember(Integer no) {
		return dao.getOneMember(no);
	}
	
	public List<MemberVO> getAllMember(){
		return dao.getAllMember();
	}

}

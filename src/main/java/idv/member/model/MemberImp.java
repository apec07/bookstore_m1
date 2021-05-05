package idv.member.model;

import java.util.List;

public interface MemberImp {
	
	//SQL STMT
	final String CREATE_ONE_STMT="";
	final String UPDATE_ONE_STMT="";
	final String DELETE_ONE_STMT="";
	final String GET_ONE_STMT="SELECT no,name,password,email FROM member where no=?";
	final String GET_ALL_STMT="SELECT no,name,password,email FROM member ORDER BY name ASC";
	//CRUD
		
	Integer insertMember(MemberVO memberVO);
	Integer updateMember(MemberVO memberVO);
	Integer deleteMember(Integer no);
	MemberVO getOneMember(Integer no);
	List<MemberVO> getAllMember();

}

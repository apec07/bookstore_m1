package idv.member.model;

import java.sql.*;
import java.util.*;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberImp {

	private static DataSource ds = null;

	static {
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestMYSQL");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer insertMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteMember(Integer no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO getOneMember(Integer no) {
		Connection con = null;
		MemberVO member = new MemberVO();

		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ONE_STMT);
			psmt.setInt(1, no);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Integer mem_no = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				member.setNo(no);
				member.setName(name);
				member.setPassword(password);
				member.setEmail(email);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (member.getName().length() > 0) {
			return member;
		} else {
			return null;
		}

	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection con = null;
		List<MemberVO> list = new LinkedList<>();
		
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setNo(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setEmail(rs.getString(4));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(list.size()>0) {
			return list;
		}else {
			return null;	
		}
		
	}

}

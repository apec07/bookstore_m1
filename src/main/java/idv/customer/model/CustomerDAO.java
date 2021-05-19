package idv.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import idv.member.model.MemberVO;

public class CustomerDAO implements CustomerImp {
	
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
	public Integer insertCustomer(CustomerVO customerVO) {
		Connection con = null;
		Integer resultNum = 0;
	
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(CREATE_ONE_STMT);
			psmt.setString(1, customerVO.getName());
			psmt.setString(2, customerVO.getPassword());
			psmt.setString(3, customerVO.getEmail());
			resultNum = psmt.executeUpdate();
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
		return resultNum;
	}

	@Override
	public Integer updateCustomer(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteCustomer(Integer no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerVO getOneCustomer(Integer no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerVO> getAllCustomer() {
		Connection con = null;
		List<CustomerVO> list = new LinkedList<>();
		
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = psmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			//get ColumnNames
			String[] colNames = new String[colCount];
			for(int i=1 ; i<=colCount ; i++) {
				colNames[i-1] = rsmd.getColumnName(i);
			}
			//get ColumnData
			while(rs.next()) {
				CustomerVO customer = new CustomerVO();
				customer.setNo(rs.getInt(colNames[0]));
				customer.setName(rs.getString(colNames[1]));
				customer.setPassword(rs.getString(colNames[2]));
				customer.setEmail(rs.getString(colNames[3]));
				
				list.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}

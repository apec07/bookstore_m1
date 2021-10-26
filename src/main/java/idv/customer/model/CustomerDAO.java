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

import org.apache.logging.log4j.*;

public class CustomerDAO implements CustomerImp {
	
	private static DataSource ds = null;
	public static Logger LOGGER = null;

	static {
		
		LOGGER = LogManager.getLogger();
		
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestMYSQL");
		} catch (NamingException e) {
			LOGGER.error(e);
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
			LOGGER.error(e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
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
		Connection con = null;
		CustomerVO customer = null;
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ONE_STMT);
			psmt.setInt(1, no);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Integer noInt = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				customer = new CustomerVO();
				customer.setNo(noInt);
				customer.setName(name);
				customer.setPassword(password);
				customer.setEmail(email);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		if(customer==null) {
			return null;
		}else {
			return customer;
		}
	
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
			LOGGER.error(e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		return list;
	}

	@Override
	public List<CustomerVO> getSomeCustomer(String like) {
		Connection con = null;
		List<CustomerVO> list = new LinkedList<>();
		
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_SOME_STMT);
			psmt.setString(1, "%"+like+"%");
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				CustomerVO customer = new CustomerVO();
				customer.setNo(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setPassword(rs.getString(3));
				customer.setEmail(rs.getString(4));
				
				list.add(customer);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		return list;
	}

	@Override
	public List<String> getAllNames() {
		Connection con = null;
		List<String> list = new LinkedList<>();
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ALL_NAMES_STMT);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				list.add(name);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		if(list.size()==0) {
			LOGGER.warn("no name from Customer");
		}
		return list;
	}

	@Override
	public List<String> getAllEmails() {
		Connection con = null;
		List<String> list = new LinkedList<>();
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ALL_EMAILS_STMT);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				String email = rs.getString(1);
				list.add(email);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		if(list.size()==0) {
			LOGGER.warn("no Email from Customer");
		}
		return list;
	}


}

package idv.ord.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import idv.cart.model.CartDAO;

public class OrdDAO implements OrdImp{
	
	private static DataSource ds = null;
	static Logger LOGGER = LogManager.getLogger(CartDAO.class);
	static {
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestMYSQL");
		} catch (NamingException e) {
			LOGGER.error("no DataBase defined!\n"+e.getStackTrace());
		}
	}

	@Override
	public Integer insertOrd(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement psmt;
		int updateNum=0;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(CREATE_ONE_STMT);
			psmt.setString(1, ordVO.getOrd_no());
			psmt.setInt(2, ordVO.getCustomer_no());
			psmt.setInt(3, ordVO.getOrd_status());
			psmt.setString(4, ordVO.getReceiver());
			psmt.setString(5, ordVO.getRec_phone());
			psmt.setString(6, ordVO.getRec_zip());
			psmt.setString(7, ordVO.getRec_address());
			psmt.setInt(8, ordVO.getOrd_total());
			updateNum = psmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Insert Exception\n"+e.getStackTrace());
			return 0;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error("Connection closed failed!\n"+e.getStackTrace());
				}
			}
		}
		
		return updateNum;
	}

	@Override
	public Integer updateOrd(OrdVO ordVO) {
		
		// cart - update  
		
		// update cart to ordered
		/*
		    update ord_status 0 - cart
		    update ord_status 1 - ordered
		 */
				
		// order list - disabled 
		return null;
	}

	@Override
	public Integer deleteOrd(Integer ord_no) {
		// cart delete 
		
		// ordered  - disabled (back-end only)
		return null;
	}

	@Override
	public List<OrdVO> getMyOrd(Integer customer_no) {
		Connection con = null;
		PreparedStatement psmt;
		List<OrdVO> list = new LinkedList<>();
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			psmt.setInt(1, customer_no);
			ResultSet rs = psmt.executeQuery();
			//ord_no,customer_no,ord_status,receiver,rec_phone,rec_zip,rec_address,ord_total,ord_datetime 
			while(rs.next()) {
				OrdVO ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ord_no"));//1
				ordVO.setCustomer_no(rs.getInt("customer_no"));//2
				ordVO.setOrd_status(rs.getInt("ord_status"));//3
				ordVO.setReceiver(rs.getString("receiver"));//4
				ordVO.setRec_phone(rs.getString("rec_phone"));//5
				ordVO.setRec_zip(rs.getString("rec_zip"));//6
				ordVO.setRec_address(rs.getString("rec_address"));//7
				ordVO.setOrd_total(rs.getInt("ord_total"));//8
				ordVO.setOrd_datetime(rs.getTimestamp("ord_datetime"));//9
				list.add(ordVO);
			}
		} catch (SQLException e) {
			LOGGER.error("getMyOrd Exception!\n"+e.getStackTrace());
			return null;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error("Connection closed failed!\n"+e.getStackTrace());
				}
			}
		}
		return list;
	}

	@Override
	public List<String> getOrdHeader() {
		Connection con = null;
		PreparedStatement psmt;
		List<String> list = new LinkedList<>();
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			ResultSetMetaData rsmd = psmt.getMetaData();
			int colCount = rsmd.getColumnCount();
			for(int i=1;i<=colCount;i++) {
				String colName = rsmd.getColumnName(i);
				list.add(colName);
			}
		} catch (SQLException e) {
			LOGGER.error("getOrdHeader Exception\n"+e.getStackTrace());
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error("Connection closed failed!\n"+e.getStackTrace());
				}
			}
		}
		return list;
	}

}

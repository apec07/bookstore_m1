package idv.ord_details.model;

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

import com.google.gson.Gson;

import idv.cart.model.CartDAO;
import idv.ord.model.OrdVO;

public class Ord_detailsDAO implements Ord_detailsImp {
	
	private static DataSource ds = null;
	static Logger LOGGER = LogManager.getLogger(CartDAO.class);
	static Gson gson = new Gson();
	static {
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestMYSQL_LOCAL");
		} catch (NamingException e) {
			LOGGER.error("no DataBase defined!\n"+e.getStackTrace());
		}
	}
	
	@Override
	public Integer insertOrd_detail(Ord_detailsVO ord_detailsVO, Connection con) {
		
		PreparedStatement psmt= null;
		int updateNum=0;
		try {
		
			psmt = con.prepareStatement(CREATE_ONE_STMT);
			LOGGER.info("=========ord_detailsVO==========");
			gson.toJson(ord_detailsVO);
			LOGGER.info("ord_detalVO" + ord_detailsVO.toString());
			psmt.setString(1, ord_detailsVO.getOrd_no());
			psmt.setInt(2, ord_detailsVO.getProduct_no());
			psmt.setInt(3, ord_detailsVO.getQuantity());
			psmt.setInt(4, ord_detailsVO.getProd_price());
			updateNum = psmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Transaction is being rolled back-");
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					throw new RuntimeException("rollback error occured. XXX" + e1.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + e.getMessage());	
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					LOGGER.error("psmt closed - "+se);
				}
			}
		}
		
		return updateNum;
	}

	@Override
	public Integer updateOrd_detail(Ord_detailsVO ord_detailsVO, Connection con) {
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
	public Integer deleteOrd_detail(String ord_no, Connection con) {
		// cart delete 
		
		// ordered  - disabled (back-end only)
		return null;
	}

	@Override
	public List<Ord_detailsVO> getMyOrd_detail(String ord_no) {
		Connection con = null;
		PreparedStatement psmt;
		List<Ord_detailsVO> list = new LinkedList<>();
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ONE_STMT);
			psmt.setString(1, ord_no);
			ResultSet rs = psmt.executeQuery();
			// ord_details.ord_no,product_no,quantity,prod_price  
			while(rs.next()) {
				Ord_detailsVO ord_detailsVO = new Ord_detailsVO();
				ord_detailsVO.setOrd_no(rs.getString("ord_no"));
				ord_detailsVO.setProduct_no(rs.getInt("product_no"));
				ord_detailsVO.setQuantity(rs.getInt("quantity"));
				ord_detailsVO.setProd_price(rs.getInt("prod_price"));
				list.add(ord_detailsVO);
			}
		} catch (SQLException e) {
			LOGGER.error("getMyOrd_detail Exception!\n"+e.getStackTrace());
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
	public List<String> getOrd_detailHeader() {
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
			LOGGER.error("getOrd_detailHeader Exception\n"+e.getStackTrace());
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

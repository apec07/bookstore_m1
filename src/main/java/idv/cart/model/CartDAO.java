package idv.cart.model;

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


public class CartDAO implements CartImp{
	
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
	public Integer insertCart(CartVO cartVO) {
		Connection con = null;
		PreparedStatement psmt;
		int updateNum=0;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(CREATE_ONE_STMT);
			psmt.setInt(1, cartVO.getCustomer_no());
			psmt.setInt(2, cartVO.getProduct_no());
			psmt.setString(3, cartVO.getProd_name());
			psmt.setInt(4, cartVO.getProd_price());
			psmt.setInt(5, cartVO.getCart_mount());
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
	public Integer updateCart(CartVO cartVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteCart(Integer customer_no, Integer product_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartVO> getMyCart(Integer customer_no) {
		Connection con = null;
		PreparedStatement psmt;
		List<CartVO> list = new LinkedList<>();
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			psmt.setInt(1, customer_no);
			ResultSet rs = psmt.executeQuery(); 
			while(rs.next()) {
				CartVO cartVO = new CartVO();
				cartVO.setCustomer_no(rs.getInt("customer_no"));
				cartVO.setProduct_no(rs.getInt("product_no"));
				cartVO.setProd_name(rs.getString("prod_name"));
				cartVO.setProd_price(rs.getInt("prod_price"));
				cartVO.setCart_mount(rs.getInt("cart_mount"));
				list.add(cartVO);
			}
		} catch (SQLException e) {
			LOGGER.error("getMyCart Exception!\n"+e.getStackTrace());
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
	public List<String> getCartHeader() {
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
			LOGGER.error("getCartHeader Exception\n"+e.getStackTrace());
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

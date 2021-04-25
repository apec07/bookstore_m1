package idv.product.model;

import java.sql.*;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductImp {
	
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
	public Integer insertProd(ProductVO productVO) {
		Connection con = null;
		PreparedStatement psmt;
		int updateNum=0;
		try {
			con = ds.getConnection();
			//文字資料新增
			psmt = con.prepareStatement(CREATE_ONE_STMT);
			psmt.setInt(1, productVO.getCategory_no());
			psmt.setString(2, productVO.getProd_name());
			psmt.setInt(3, productVO.getProd_price());
			psmt.setString(4, productVO.getProd_introduce());
			psmt.setInt(5, productVO.getProd_stock());
			psmt.setInt(6, productVO.getProd_status());
			//圖片資料新增
			psmt.setBytes(7, productVO.getProd_pic());
			updateNum = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return updateNum;
	}

	@Override
	public Integer deleteProd(Integer product_no) {
		Connection con = null;
		Integer updateNum = new Integer(0);
		
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(DELETE_ONE_STMT);
			psmt.setInt(1, product_no);
			updateNum = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateNum;
	}

	@Override
	public Integer updateProd(ProductVO productVO) {
		Connection con = null;
		Integer updateNum=0;
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(UPDATE_ONE_STMT);
			psmt.setInt(1, productVO.getCategory_no());
			psmt.setString(2, productVO.getProd_name());
			psmt.setInt(3, productVO.getProd_price());
			psmt.setString(4, productVO.getProd_introduce());
			psmt.setInt(5, productVO.getProd_stock());
			psmt.setInt(6, productVO.getProd_status());
			psmt.setInt(7, productVO.getProduct_no());
			updateNum = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return updateNum;
	}

	@Override
	public ProductVO getOneProd(Integer product_no) {
		Connection con = null;
		ProductVO product = new ProductVO();
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ALL_STMT);
			psmt.setInt(1, product_no);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				product.setProduct_no(rs.getInt("product_no"));
				product.setCategory_no(rs.getInt("category_no"));
				product.setProd_name(rs.getString("prod_name"));
				product.setProd_price(rs.getInt("prod_price"));
				product.setProd_introduce(rs.getString("prod_introduce"));
				product.setProd_stock(rs.getInt("prod_stock"));
				product.setProd_status(rs.getInt("prod_status"));
				product.setProd_pic(rs.getBytes("prod_pic"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return product;
	}

	@Override
	public List<ProductVO> getAllProd() {
		Connection con = null;
		List<ProductVO> list = new LinkedList<>();
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				ProductVO product = new ProductVO();
				product.setProduct_no(rs.getInt("product_no"));
				product.setCategory_no(rs.getInt("category_no"));
				product.setProd_name(rs.getString("prod_name"));
				product.setProd_price(rs.getInt("prod_price"));
				product.setProd_introduce(rs.getString("prod_introduce"));
				product.setProd_stock(rs.getInt("prod_stock"));
				product.setProd_status(rs.getInt("prod_status"));
				product.setProd_pic(rs.getBytes("prod_pic"));
				list.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return list;
	}

}

package idv.prod_category.model;

import java.util.*;

import javax.sql.DataSource;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Prod_categoryDAO implements Prod_categoryImp {
	
	private static DataSource ds = null;
	public static Logger LOGGER = LogManager.getLogger();
	
	static {
		
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds  = (javax.sql.DataSource) ctx.lookup("java:comp/env/jdbc/TestMYSQL_LOCAL");
	
		}catch(javax.naming.NamingException e) {
			LOGGER.error("no DataBase defined!\n"+e.getStackTrace());
		}
	
	}

	@Override
	public Prod_categoryVO findByPrimaryIndex(Integer category_no) {
		Connection con = null;
		Prod_categoryVO prodcategoryVO =null;;
	
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ONE_STMT);
			psmt.setInt(1, category_no);
			ResultSet rs = psmt.executeQuery();
			prodcategoryVO = new Prod_categoryVO(); 
			while(rs.next()) {
				Integer categoryNo = rs.getInt(1);
				String categoryName = rs.getString(2);
				String categoryDescr = rs.getString(3);
				prodcategoryVO.setCategory_no(categoryNo);
				prodcategoryVO.setCategory_name(categoryName);
				prodcategoryVO.setCategory_descr(categoryDescr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prodcategoryVO;
	}

	@Override
	public List<Prod_categoryVO> findAll() {
		Connection con = null;
		List<Prod_categoryVO> list = new LinkedList<>();
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Prod_categoryVO prodcategoryVO = new Prod_categoryVO();
				Integer categoryNo = rs.getInt(1);
				String categoryName = rs.getString(2);
				String categoryDescr = rs.getString(3);
				prodcategoryVO.setCategory_no(categoryNo);
				prodcategoryVO.setCategory_name(categoryName);
				prodcategoryVO.setCategory_descr(categoryDescr);
				list.add(prodcategoryVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public Integer insertProd_category(Prod_categoryVO prod_categoryVO) {
		
		Connection con = null;
		
		Integer resultNum =0;
		
		String category_name = prod_categoryVO.getCategory_name();
		String category_descr = prod_categoryVO.getCategory_descr();
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(CREATE_ONE_STMT);
			psmt.setString(1, category_name);
			psmt.setString(2, category_descr);
			resultNum = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultNum;
	}

	@Override
	public Integer deleteProd_category(Integer category_no) {
		
		Connection con = null;
		Integer resultNum =0;
		
		try {
			con = ds.getConnection();
			PreparedStatement psmt = con.prepareStatement(DELETE_ONE_STMT);
			psmt.setInt(1, category_no);
			resultNum = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultNum;
	}
	

}

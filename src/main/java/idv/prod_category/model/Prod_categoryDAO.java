package idv.prod_category.model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Prod_categoryDAO implements Prod_categoryImp {
	
	private static Connection con = null;
	public static Logger logger = LogManager.getLogger();
	
	static {
		
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			javax.sql.DataSource ds  = (javax.sql.DataSource) ctx.lookup("");
			con = ds.getConnection();
		}catch(javax.naming.NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public Prod_categoryVO findByPrimaryIndex(Integer category_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prod_categoryVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

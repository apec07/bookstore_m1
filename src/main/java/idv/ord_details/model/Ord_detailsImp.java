package idv.ord_details.model;

import java.util.List;

public interface Ord_detailsImp {
	
	//SQL STMT
	// Insert into ORD_DETAILS (ORD_NO,PRODUCT_NO,QUANTITY,PROD_PRICE) values ('A0001',1001,1,199);
    //MUST SYNC with ORD
	final String CREATE_ONE_STMT="INSERT INTO ord_details (ord_no,product_no,quantity,prod_price)"
				+" VALUES(?,?,?,?)";
    
	//MUST SYNC with ORD
	final String UPDATE_ONE_STMT="UPDATE ord_details SET"
			+" product_no=?," 
			+" quantity=?,"
			+" prod_price=?" // sync with product.prod_price 
			+" WHERE ord_no=?"; 
    
	//MUST DELETE ORD THEN... 
	final String DELETE_ONE_STMT="DELETE FROM ord_details WHERE ord_no=?";
    
	//Filter WITH current Customer
	final String GET_ALL_STMT="SELECT ord_details.ord_no,product_no,quantity,prod_price"
				+ " FROM ord_details"
				+ " INNER JOIN ord ON ord.ord_no = ord_details.ord_no"
				+ " WHERE ord.customer_no=?";
	
	final String GET_ONE_STMT="SELECT ord_no,product_no,quantity,prod_price"
			+ "  FROM ord_details WHERE ord_no = ? ORDER BY product_no";
	  
	//CRUD
	Integer insertOrd_detail(Ord_detailsVO ord_detailsVO, java.sql.Connection con);
	Integer updateOrd_detail(Ord_detailsVO ord_detailsVO, java.sql.Connection con);
	Integer deleteOrd_detail(String ord_no, java.sql.Connection con);
	List<Ord_detailsVO> getMyOrd_detail(String ord_no);
	List<String> getOrd_detailHeader();
}

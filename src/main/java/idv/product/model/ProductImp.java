package idv.product.model;

import java.util.List;

public interface ProductImp {
	
	//SQL STMT
	final String CREATE_ONE_STMT="INSERT INTO product (category_no,prod_name,prod_price,prod_introduce,prod_stock,prod_status,prod_pic)"
			+ " VALUES (?,?,?,?,?,?,?)";
	final String UPDATE_ONE_STMT="UPDATE product SET"
			+ " category_no=?,"
			+ " prod_name=?,"
			+ " prod_price=?,"
			+ " prod_introduce=?,"
			+ " prod_stock=?,"
			+ " prod_status=?"
			+ " WHERE product_no=?";
	final String DELETE_ONE_STMT="DELETE FROM product WHERE product_no=?";
	final String GET_ONE_STMT="SELECT product_no,category_no,prod_name,prod_price,prod_introduce,prod_stock,prod_status,prod_pic FROM product WHERE category_no=?";
	final String GET_ALL_STMT="SELECT product_no,category_no,prod_name,prod_price,prod_introduce,prod_stock,prod_status,prod_pic FROM product ORDER BY product_no ASC";
	//CRUD
	
	Integer insertProd(ProductVO productVO);
	Integer updateProd(ProductVO productVO);
	Integer deleteProd(Integer product_no);
	ProductVO getOneProd(Integer product_no);
	List<ProductVO> getAllProd();
	List<String> getProdHeader();
}

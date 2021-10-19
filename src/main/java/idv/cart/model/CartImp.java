package idv.cart.model;

import java.util.List;

public interface CartImp {
	
	
	//SQL STMT
	final String CREATE_ONE_STMT="INSERT INTO cart (customer_no,product_no,product_name,product_price,cart_mount)"
				+" VALUES(?,?,?,?,?)";
	final String UPDATE_ONE_STMT="UPDATE cart SET"
				+ " cart_mount=?"
				+ " WHERE customer_no=? AND product_no=?";
	final String DELETE_ONE_STMT="DELETE FROM cart WHERE customer_no=? AND product_no=?";
	final String GET_ALL_STMT="SELECT customer_no,product_no,product_name,product_price,cart_mount FROM cart WHERE customer_no=? ORDER BY product_no ASC";
	//CRUD
	Integer insertCart(CartVO cartVO);
	Integer updateCart(CartVO cartVO);
	Integer deleteCart(Integer customer_no,Integer product_no);
	List<CartVO> getMyCart(Integer customer_no);
	List<String> getCartHeader();

}

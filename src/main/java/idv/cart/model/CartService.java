package idv.cart.model;

import java.util.List;

public class CartService {
	
	private CartImp dao = null;
	
	public CartService() {
		dao = new CartDAO();
	}

	public CartVO insertCart(Integer customer_no,Integer product_no,Integer cart_mount) {
		CartVO cartVO = new CartVO();
		cartVO.setCustomer_no(customer_no);
		cartVO.setProduct_no(product_no);
		cartVO.setCart_mount(cart_mount);
		if(dao.insertCart(cartVO)==0)
			return null;
		else
			return cartVO;
	}
	
	public CartVO updateCart(Integer customer_no,Integer product_no,Integer cart_mount) {
		
		return null;
	}
	
	public Boolean deleteCart(Integer customer_no, Integer product_no) {
		return false;
	}
	
	public List<CartVO> getMyCart(Integer customer_no) {
		return dao.getMyCart(customer_no);
	}
	
	public List<String> getCartHeader(){
		return dao.getCartHeader();
	}
}

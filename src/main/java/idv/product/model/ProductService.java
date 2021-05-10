package idv.product.model;

import java.util.List;

public class ProductService {
	
	private ProductImp dao = null;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	private Integer getProductNo() {
		long now = System.currentTimeMillis();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		return 1111;
	}
	
	public ProductVO insertProd(Integer category_no, String prod_name, Integer prod_price, String prod_introduce, 
			Integer prod_stock, Integer prod_status, byte[] prod_pic ) {
		ProductVO productVO = new ProductVO();
		productVO.setCategory_no(category_no);
		productVO.setProd_name(prod_name);
		productVO.setProd_price(prod_price);
		productVO.setProd_introduce(prod_introduce);
		productVO.setProd_stock(prod_stock);
		productVO.setProd_status(prod_status);
		productVO.setProd_pic(prod_pic);
		Integer insertNum = 0;
		insertNum = dao.insertProd(productVO);
		if(insertNum>0) {
			return productVO;
		}else {
			return null;
		}
		
	}
	
	public ProductVO updateProd (Integer category_no, String prod_name, Integer prod_price, String prod_introduce, 
			Integer prod_stock, Integer prod_status, byte[] prod_pic ) {
		ProductVO productVO = new ProductVO();
		productVO.setProduct_no(category_no);
		productVO.setProd_name(prod_name);
		productVO.setProd_price(prod_price);
		productVO.setProd_introduce(prod_introduce);
		productVO.setProd_stock(prod_stock);
		productVO.setProd_status(prod_status);
		productVO.setProd_pic(prod_pic);
		Integer updateNum = 0;
		updateNum = dao.updateProd(productVO);
		if(updateNum>0) {
			return productVO;
		}else {
			return null;
		}
		
	}
	
	public Boolean deleteProd(Integer product_no) {
		Integer delNum = new Integer(0);
		delNum = dao.deleteProd(product_no);
		if(delNum>0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public ProductVO getOneProd(Integer product_no) {
		return dao.getOneProd(product_no);
	}
	
	public List<ProductVO> getAllProd(){
		return dao.getAllProd();
	}
	
	public List<String> getProdHeader(){
		return dao.getProdHeader();
	}

}

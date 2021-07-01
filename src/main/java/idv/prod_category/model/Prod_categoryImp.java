package idv.prod_category.model;

import java.util.List;

public interface Prod_categoryImp {
	
	final String GET_ONE_STMT="SELECT category_no,category_name,category_descr FROM prod_category WHERE category_no=?";
	final String GET_ALL_STMT="SELECT category_no,category_name,category_descr FROM prod_category ORDER BY category_no ASC";
	final String CREATE_ONE_STMT="INSERT INTO prod_category(category_name,category_descr) VALUES(?,?)";
	final String DELETE_ONE_STMT="DELETE FROM prod_category WHERE category_no=?";
	
	Prod_categoryVO findByPrimaryIndex(Integer category_no);
	List<Prod_categoryVO> findAll();
	Integer insertProd_category(Prod_categoryVO prod_categoryVO);
	Integer deleteProd_category(Integer category_no);
	

}

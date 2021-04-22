package idv.prod_category.model;

import java.util.List;

public interface Prod_categoryImp {
	
	final String GET_ONE_STMT="SELECT category_no,category_name,category_descr FROM prod_category WHERE category_no=?";
	final String GET_ALL_STMT="SELECT category_no,category_name,category_descr FROM prod_category ORDER BY category_no ASC";
	
	Prod_categoryVO findByPrimaryIndex(Integer category_no);
	List<Prod_categoryVO> findAll();

}

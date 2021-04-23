package idv.prod_category.model;

import java.util.List;

public class Prod_categoryService {
	
	private Prod_categoryImp dao=null;
	
	public Prod_categoryService() {
		dao = new Prod_categoryDAO();
	}
	
	public Prod_categoryVO getOneCategory(Integer category_no) {
		return dao.findByPrimaryIndex(category_no);
	}
	
	public List<Prod_categoryVO> getAll(){
		return dao.findAll();
	}

}

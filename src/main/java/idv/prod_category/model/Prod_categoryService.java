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
	
	public boolean addOneCategory(String category_name,String category_descr) {
		Prod_categoryVO prod_categoryVO = new Prod_categoryVO();
		String name = category_name.trim();
		String descr = category_descr.trim();
		if(name.length()==0 || descr.length()==0) {
			return false;
		}
		prod_categoryVO.setCategory_name(name);
		prod_categoryVO.setCategory_descr(descr);
		
		return dao.insertProd_category(prod_categoryVO)>0;
	}
	
	public boolean delOneCategory(Integer category_no) {
		return dao.deleteProd_category(category_no)>0;
	}

}

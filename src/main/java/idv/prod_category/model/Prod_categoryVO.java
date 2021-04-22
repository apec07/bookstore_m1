package idv.prod_category.model;

/*
  CREATE TABLE PROD_CATEGORY (
  CATEGORY_NO    SMALLINT  NOT NULL AUTO_INCREMENT, 
  CATEGORY_NAME  VARCHAR(50)  NOT NULL, 
  CATEGORY_DESCR VARCHAR(600) NOT NULL, 
  CONSTRAINT PROD_CATEGORY_PK PRIMARY KEY (CATEGORY_NO));
 */

public class Prod_categoryVO implements java.io.Serializable{
	
	private Integer category_no;
	private String category_name;
	private String category_descr;
	
	public Prod_categoryVO() {}
	
	public Integer getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Integer category_no) {
		this.category_no = category_no;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_descr() {
		return category_descr;
	}
	public void setCategory_descr(String category_descr) {
		this.category_descr = category_descr;
	}

	@Override
	public String toString() {
		return "Prod_categoryVO [category_no=" + category_no + ", category_name=" + category_name + "]";
	}


	
	
}

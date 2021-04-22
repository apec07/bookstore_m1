package idv.product.model;

/*
 CREATE TABLE PRODUCT (
  PRODUCT_NO     SMALLINT   NOT NULL , 
  CATEGORY_NO    SMALLINT   NOT NULL, 
  PROD_NAME      VARCHAR(50)   NOT NULL, 
  PROD_PRICE     SMALLINT   NOT NULL, 
  PROD_INTRODUCE VARCHAR(1000) NOT NULL, 
  PROD_STOCK     SMALLINT   NOT NULL, 
  PROD_STATUS    TINYINT    NOT NULL, 
  PROD_PIC       BLOB,
  CONSTRAINT PRODUCT_FK FOREIGN KEY (CATEGORY_NO) REFERENCES PROD_CATEGORY (CATEGORY_NO),
  CONSTRAINT PRODUCT_PK PRIMARY KEY (PRODUCT_NO));
 */

public class ProductVO implements java.io.Serializable{
	
	private Integer product_no;  //PK
	private Integer category_no; //FK
	private String prod_name;
	private Integer prod_price;
	private String prod_introduce;
	private Integer prod_stock;
	private Integer prod_status; 
	private byte[] prod_pic; // may try java.nio ?
	
	public ProductVO() {}

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}

	public Integer getCategory_no() {
		return category_no;
	}

	public void setCategory_no(Integer category_no) {
		this.category_no = category_no;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public Integer getProd_price() {
		return prod_price;
	}

	public void setProd_price(Integer prod_price) {
		this.prod_price = prod_price;
	}

	public String getProd_introduce() {
		return prod_introduce;
	}

	public void setProd_introduce(String prod_introduce) {
		this.prod_introduce = prod_introduce;
	}

	public Integer getProd_stock() {
		return prod_stock;
	}

	public void setProd_stock(Integer prod_stock) {
		this.prod_stock = prod_stock;
	}

	public Integer getProd_status() {
		return prod_status;
	}

	public void setProd_status(Integer prod_status) {
		this.prod_status = prod_status;
	}

	public byte[] getProd_pic() {
		return prod_pic;
	}

	public void setProd_pic(byte[] prod_pic) {
		this.prod_pic = prod_pic;
	}

	@Override
	public String toString() {
		return "ProductVO [product_no=" + product_no + ", prod_name=" + prod_name + ", prod_price=" + prod_price + "]";
	}
	
	

}

package idv.ord_details.model;

/*
--------------------------------------------------------
--  for Table ORD_DETAILS
--------------------------------------------------------
CREATE TABLE ORD_DETAILS(	
  ORD_NO     VARCHAR(15) NOT NULL, 
  PRODUCT_NO SMALLINT NOT NULL,
  QUANTITY   INT,
  PROD_PRICE INT NOT NULL,
  CONSTRAINT ORD_DETAILS_FK1 FOREIGN KEY (ORD_NO) REFERENCES ORD (ORD_NO),
  CONSTRAINT ORD_DETAILS_FK2 FOREIGN KEY (PRODUCT_NO) REFERENCES PRODUCT (PRODUCT_NO),
  CONSTRAINT ORD_DETAILS_PK PRIMARY KEY(ORD_NO, PRODUCT_NO));
*/

public class Ord_detailsVO implements java.io.Serializable {
	
	private String ord_no;
	private Integer product_no;
	private Integer quantity;
	private Integer prod_price;
	
	public Ord_detailsVO() {}

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProd_price() {
		return prod_price;
	}

	public void setProd_price(Integer prod_price) {
		this.prod_price = prod_price;
	}

	@Override
	public String toString() {
		return "Ord_detailsVO [ord_no=" + ord_no + ", product_no=" + product_no + ", quantity=" + quantity
				+ ", prod_price=" + prod_price + "]";
	}
	
}

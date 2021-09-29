package idv.cart.model;
/*
  CREATE TABLE cart (
    customer_no  SMALLINT NOT NULL,
    product_no   SMALLINT NOT NULL,
    cart_mount INT NOT NULL,
   CONSTRAINT dual_pk PRIMARY KEY (customer_no,PRODUCT_NO),
   CONSTRAINT cust_FK FOREIGN KEY (customer_no) REFERENCES customer (NO),
   CONSTRAINT PROD_FK FOREIGN KEY (product_no) REFERENCES product (PRODUCT_NO)
  );
 */

public class CartVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Modified first !
	private Integer customer_no;
	private Integer product_no;
	private Integer cart_mount;
	
	public CartVO() {}

	public Integer getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(Integer customer_no) {
		this.customer_no = customer_no;
	}

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}

	public Integer getCart_mount() {
		return cart_mount;
	}

	public void setCart_mount(Integer cart_mount) {
		this.cart_mount = cart_mount;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		
		if(this == obj ) return true;
		
		if(obj !=null && this.getClass() == obj.getClass()) {
			if(obj instanceof CartVO) {
				CartVO cartVO = (CartVO) obj;
				if(this.product_no.equals(cartVO.product_no)) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "CartVO [customer_no=" + customer_no + ", product_no=" + product_no + ", cart_mount=" + cart_mount + "]";
	}
	
}

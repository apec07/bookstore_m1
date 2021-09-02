package idv.ord.model;

import java.sql.Timestamp;

/*
--Ord modified by cart--
--------------------------------------------------------
--  for Table ORDER
--------------------------------------------------------
CREATE TABLE ORD (
  ORD_NO      VARCHAR(15) NOT NULL, 
  CUSTOMER_NO SMALLINT NOT NULL,
  ORD_STATUS  TINYINT NOT NULL, 
  RECEIVER    VARCHAR(50), 
  REC_PHONE   VARCHAR(20), 
  REC_ZIP     VARCHAR(10), 
  REC_ADDRESS VARCHAR(200), 
  ORD_TOTAL   INT, 
  ORD_DATETIME    TIMESTAMP DEFAULT NOW(),
  CONSTRAINT ORDER_FK FOREIGN KEY (CUSTOMER_NO) REFERENCES customer (NO),
  CONSTRAINT ORDER_PK PRIMARY KEY (ORD_NO));
*/

public class OrdVO implements java.io.Serializable {
	
	private String ord_no;
	private Integer customer_no;
	private Integer ord_status;
	private String receiver;
	private String rec_phone;
	private String rec_zip;
	private String rec_address;
	private Integer ord_total;
	private Timestamp ord_datetime;
	
	public OrdVO() {}

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}

	public Integer getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(Integer customer_no) {
		this.customer_no = customer_no;
	}

	public Integer getOrd_status() {
		return ord_status;
	}

	public void setOrd_status(Integer ord_status) {
		this.ord_status = ord_status;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRec_phone() {
		return rec_phone;
	}

	public void setRec_phone(String rec_phone) {
		this.rec_phone = rec_phone;
	}

	public String getRec_zip() {
		return rec_zip;
	}

	public void setRec_zip(String rec_zip) {
		this.rec_zip = rec_zip;
	}

	public String getRec_address() {
		return rec_address;
	}

	public void setRec_address(String rec_address) {
		this.rec_address = rec_address;
	}

	public Integer getOrd_total() {
		return ord_total;
	}

	public void setOrd_total(Integer ord_total) {
		this.ord_total = ord_total;
	}

	public Timestamp getOrd_datetime() {
		return ord_datetime;
	}

	public void setOrd_datetime(Timestamp ord_datetime) {
		this.ord_datetime = ord_datetime;
	}

	@Override
	public String toString() {
		return "OrdVO [ord_no=" + ord_no + ", customer_no=" + customer_no + ", ord_status=" + ord_status + ", receiver="
				+ receiver + ", rec_phone=" + rec_phone + ", rec_zip=" + rec_zip + ", rec_address=" + rec_address
				+ ", ord_total=" + ord_total + ", ord_datetime=" + ord_datetime + "]";
	}


	
}

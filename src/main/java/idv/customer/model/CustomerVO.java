package idv.customer.model;

/*
  CREATE TABLE CUSTOMER (	
 	NO                   SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 	NAME                 VARCHAR(20) NOT NULL UNIQUE,
 	PASSWORD             VARCHAR(200) NOT NULL,
 	EMAIL                VARCHAR(320) NOT NULL UNIQUE
 	);
 */

public class CustomerVO implements java.io.Serializable {
	
	private Integer no;
	private String name;
	private String password;
	private String email;
	
	public CustomerVO() {}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerVO [no=" + no + ", name=" + name + "]";
	}
	
	

}

package idv.customer.model;

import java.util.List;

public interface CustomerImp {
	
	//SQL STMT
	final String CREATE_ONE_STMT="INSERT INTO customer(name,password,email) VALUES (?,?,?)";
	final String UPDATE_ONE_STMT="UPDATE customer SET name=?, password=?, email=? WHERE no=?";
	final String DELETE_ONE_STMT="DELETE FROM customer WHERE no=?";
	final String GET_ONE_STMT="SELECT no,name,password,email FROM customer WHERE no=?";
	final String GET_SOME_STMT="SELECT no,name,password,email from customer WHERE name LIKE ?";
	final String GET_ALL_STMT="SELECT no,name,password,email FROM customer ORDER BY name ASC";
	final String GET_ALL_NAMES_STMT="SELECT name FROM customer";
	final String GET_ALL_EMAILS_STMT="SELECT email FROM customer";
	//CRUD
		
	Integer insertCustomer(CustomerVO customerVO);
	Integer updateCustomer(CustomerVO customerVO);
	Integer deleteCustomer(Integer no);
	CustomerVO getOneCustomer(Integer no);
	List<CustomerVO> getAllCustomer();
	List<CustomerVO> getSomeCustomer(String like);
	List<String> getAllNames();
	List<String> getAllEmails();

}

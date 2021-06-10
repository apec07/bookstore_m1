package idv.customer.model;

import java.util.List;
import org.apache.logging.log4j.*;

public class CustomerService {
	
	private CustomerImp dao;
	public Logger LOGGER;
	
	public CustomerService() {
		dao = new CustomerDAO();
		LOGGER = LogManager.getLogger();

	}
	
	public CustomerVO insertCustomer(String name,String password, String email) {
		if(name.length()==0 || password.length()==0 || email.length()==0) {
			//error handler
			LOGGER.error("input empty");
			return null;
		}
		CustomerVO customer = new CustomerVO();
		customer.setName(name);
		customer.setPassword(password);
		customer.setEmail(email);
		
		Integer updateNum = dao.insertCustomer(customer);
		
		if(updateNum==0) {
			//error handler
			LOGGER.error("SQL exception");
			return null;
		}else {
			return customer;
		}
		
	}
	
	public List<CustomerVO> getAllCustomer(){
		return dao.getAllCustomer();
	}
	
	public List<String> getAllNames(){
		return dao.getAllNames();
	}
	
	public List<String> getAllEmails(){
		return dao.getAllEmails();
	}
}

package idv.customer.model;

import java.util.List;

public class CustomerService {
	
	private CustomerImp dao;
	
	public CustomerService() {
		dao = new CustomerDAO();
	}
	
	public CustomerVO insertCustomer(String name,String password, String email) {
		if(name.length()==0 || password.length()==0 || email.length()==0) {
			//error handler
			System.out.println("Service - input Error");
			return null;
		}
		CustomerVO customer = new CustomerVO();
		customer.setName(name);
		customer.setPassword(password);
		customer.setEmail(email);
		Integer customerSize = dao.getAllCustomer().size();
		String[] names = new String[customerSize];
		
		for(int i=0; i<customerSize;i++)
			names[i] = dao.getAllCustomer().get(i).getName();
		
		for(int i=0; i<customerSize;i++)
			System.out.println("check - "+names[i].equals(name));
		
		Integer updateNum = dao.insertCustomer(customer);
		
		if(updateNum==0) {
			//error handler
			System.out.println("Service - SQL exception");
			return null;
		}else {
			return customer;
		}
		
	}
	
	public List<CustomerVO> getAllCustomer(){
		
		return dao.getAllCustomer();
	}

}

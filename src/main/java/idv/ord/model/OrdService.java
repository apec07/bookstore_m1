package idv.ord.model;

import java.util.List;

public class OrdService {
	
	private OrdImp dao = null;

	public OrdService() {
		dao = new OrdDAO();
	}
	
	public OrdVO insertOrd(String ord_no,Integer customer_no,Integer ord_status,String receiver,
			String rec_phone,String rec_zip,String rec_address,Integer ord_total) {
		OrdVO ord = new OrdVO();
		ord.setOrd_no(ord_no);
		ord.setCustomer_no(customer_no);
		ord.setOrd_status(ord_status);
		ord.setReceiver(receiver);
		ord.setRec_phone(rec_phone);
		ord.setRec_zip(rec_zip);
		ord.setRec_address(rec_address);
		ord.setOrd_total(ord_total);
		if(dao.insertOrd(ord)==0) 
			return null;
		else 
			return ord;
		
	}
	
	public OrdVO updateOrd(Integer customer_no,Integer ord_status,String receiver,
			String rec_phone,String rec_zip,String rec_address,Integer ord_total) {
		
		return null;
	}
	
	public Boolean deleteOrd(String ord_no) {
		
		return false;
	}
	
	public List<OrdVO> getMyOrd(Integer customer_no){
		return dao.getMyOrd(customer_no);
	}
	
	public List<String> getOrdHeader(){
		return dao.getOrdHeader();
	}

}

package idv.ord.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import idv.cart.model.CartVO;
import idv.ord_details.model.Ord_detailsVO;

public class OrdService {
	
	private OrdImp dao = null;

	public OrdService() {
		dao = new OrdDAO();
	}
	
	public OrdVO insertOrd(String ord_no,Integer customer_no,Integer ord_status,String receiver,
			String rec_phone,String rec_zip,String rec_address,Integer ord_total ,Vector<CartVO> cartlist) {
		OrdVO ord = new OrdVO();
		ord.setOrd_no(ord_no);
		ord.setCustomer_no(customer_no);
		ord.setOrd_status(ord_status);
		ord.setReceiver(receiver);
		ord.setRec_phone(rec_phone);
		ord.setRec_zip(rec_zip);
		ord.setRec_address(rec_address);
		ord.setOrd_total(ord_total);

		// Cart List to Order List <have to ChecK!!>
		List<Ord_detailsVO> addList = new ArrayList<Ord_detailsVO>();
		for (CartVO cartVO : cartlist) {
			Ord_detailsVO ord_listVO = new Ord_detailsVO();
			
			ord_listVO.setOrd_no(ord_no);
			ord_listVO.setProduct_no(cartVO.getProduct_no());
			ord_listVO.setProd_price(cartVO.getProd_price());
			ord_listVO.setQuantity(cartVO.getCart_mount());
			
			//.....cartVO modify first
			addList.add(ord_listVO);
		}
		return dao.insertWithOrdDetails(ord, addList);

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

package idv.ord.model;

import java.util.List;

public interface OrdImp {
	/*
  Insert into ORD (ORD_NO,CUSTOMER_NO,ORD_STATUS,RECEIVER,REC_PHONE,REC_ZIP,REC_ADDRESS,ORD_TOTAL,ORD_DATETIME) 
  values ('A0001',1,0,'湯姆貓','0988888888','320','桃園市中壢區福德一路177巷60弄2號',60000,NOW());
	 */
	
	//SQL STMT
	    //MUST SYNC with ORD_DETAILS
		final String CREATE_ONE_STMT="INSERT INTO ord (ord_no,customer_no,ord_status,receiver,rec_phone,rec_zip,rec_address,ord_total,ord_datetime)"
					+" VALUES(?,?,?,?,?,?,?,?,NOW())";
	    
		//MUST SYNC with ORD_DETAILS
		final String UPDATE_ONE_STMT="UPDATE ord SET"
				+" ord_status=?," //0 is cart 1 is ordered!
				+" receiver=?,"
				+" rec_phone=?,"
				+" rec_zip=?,"
				+" rec_address=?,"
				+" ord_total=?," // total count by calculated 
				+" WHERE ord_no=?"; 
	    
		//MUST DELETE ORD_DETAILS FIRST!! 
		final String DELETE_ONE_STMT="DELETE FROM ord WHERE ord_no=?";
	    
		//Filter WITH current Customer
		final String GET_ALL_STMT="SELECT ord_no,customer_no,ord_status,receiver,rec_phone,rec_zip,rec_address,ord_total,ord_datetime"
					+ " FROM ord"
					+ " WHERE customer_no=?";
		  
		//CRUD
		Integer insertOrd(OrdVO ordVO);
		Integer updateOrd(OrdVO ordVO);
		Integer deleteOrd(Integer ord_no);
		List<OrdVO> getMyOrd(Integer customer_no);
		List<String> getOrdHeader();

}

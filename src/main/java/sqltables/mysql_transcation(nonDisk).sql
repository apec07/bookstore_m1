CREATE DATABASE db_morgan_bookstore;
use db_morgan_bookstore;

START TRANSACTION
    [transaction_characteristic [, transaction_characteristic] ...]

transaction_characteristic: {
    WITH CONSISTENT SNAPSHOT
  | READ WRITE
  | READ ONLY
}


INSERT INTO ord (ord_no,customer_no,ord_status,receiver,rec_phone,rec_zip,rec_address,ord_total,ord_datetime) 
            VALUES('111',1,0,'morgan001','0912456789','123','TPE',5000,NOW());

INSERT INTO ord_details (ord_no,product_no,quantity,prod_price) 
            VALUES('111','1002',1,2000);
INSERT INTO ord_details (ord_no,product_no,quantity,prod_price) 
            VALUES('111',2001,2,999);
            
--Error starting at line : 68 in command -
--ROLLBACK
--Error report -
--Can't call rollback when autocommit=true

--Commit complete.
------------------------------------------
START TRANSACTION;
INSERT INTO ord (ord_no,customer_no,ord_status,receiver,rec_phone,rec_zip,rec_address,ord_total,ord_datetime) 
            VALUES('111',1,0,'morgan001','0912456789','123','TPE',5000,NOW());

INSERT INTO ord_details (ord_no,product_no,quantity,prod_price) 
            VALUES('112','1002',1,2000);
INSERT INTO ord_details (ord_no,product_no,quantity,prod_price) 
            VALUES('111',2001,2,999);
ROLLBACK;
COMMIT;


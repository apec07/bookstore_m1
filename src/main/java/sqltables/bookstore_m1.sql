CREATE DATABASE db_morgan_bookstore;
use db_morgan_bookstore;


--Front-End User--
 CREATE TABLE customer (	
 NO                   SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 NAME                 VARCHAR(20) NOT NULL UNIQUE,
 PASSWORD             VARCHAR(200) NOT NULL,
 EMAIL                VARCHAR(320) NOT NULL UNIQUE
 );
 
 
 --Back-End User--
 CREATE TABLE member (	
 NO                   SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 NAME                 VARCHAR(20) NOT NULL UNIQUE,
 PASSWORD             VARCHAR(200) NOT NULL,
 EMAIL                VARCHAR(320) NOT NULL
 );
 
--Product Category--
  CREATE TABLE prod_category (
  CATEGORY_NO    SMALLINT  NOT NULL AUTO_INCREMENT, 
  CATEGORY_NAME  VARCHAR(50)  NOT NULL, 
  CATEGORY_DESCR VARCHAR(600) NOT NULL, 
  CONSTRAINT PROD_CATEGORY_PK PRIMARY KEY (CATEGORY_NO));
 
 --Product--
 CREATE TABLE product (
  PRODUCT_NO     SMALLINT   AUTO_INCREMENT, 
  CATEGORY_NO    SMALLINT   NOT NULL, 
  PROD_NAME      VARCHAR(50)   NOT NULL, 
  PROD_PRICE     SMALLINT   NOT NULL, 
  PROD_INTRODUCE VARCHAR(1000) NOT NULL, 
  PROD_STOCK     SMALLINT   NOT NULL, 
  PROD_STATUS    TINYINT    NOT NULL, 
  PROD_PIC       LONGBLOB,
  CONSTRAINT PRODUCT_FK FOREIGN KEY (CATEGORY_NO) REFERENCES prod_category (CATEGORY_NO),
  CONSTRAINT PRODUCT_PK PRIMARY KEY (PRODUCT_NO));

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


SELECT * FROM product;
SELECT * FROM ord;
SELECT * FROM ord_details;
DELETE FROM ord;
DELETE FROM ord_details;
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
  
  --------------------------------------------------------
--  for Table ORD_DETAILS
--------------------------------------------------------
CREATE TABLE ORD_DETAILS(	
  ORD_NO     VARCHAR(15) NOT NULL, 
  PRODUCT_NO SMALLINT NOT NULL,
  QUANTITY   INT,
  PROD_PRICE INT NOT NULL,
  CONSTRAINT ORD_DETAILS_FK1 FOREIGN KEY (ORD_NO) REFERENCES ORD (ORD_NO),
  CONSTRAINT ORD_DETAILS_FK2 FOREIGN KEY (PRODUCT_NO) REFERENCES PRODUCT (PRODUCT_NO),
  CONSTRAINT ORD_DETAILS_PK PRIMARY KEY(ORD_NO, PRODUCT_NO));
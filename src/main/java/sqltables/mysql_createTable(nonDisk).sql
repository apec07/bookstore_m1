CREATE DATABASE db_morgan_bookstore;
use db_morgan_bookstore;


DROP TABLE ord_details;
DROP TABLE ord;
DROP TABLE cart;
DROP TABLE product;
DROP TABLE prod_category;
DROP TABLE member;
DROP TABLE customer;

--------------------------------------------------------
--  for Table CUSTOMER (front-end)
--------------------------------------------------------
 CREATE TABLE customer (	
 NO                   SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 NAME                 VARCHAR(20) NOT NULL UNIQUE,
 PASSWORD             VARCHAR(200) NOT NULL,
 EMAIL                VARCHAR(320) NOT NULL UNIQUE
 );
 
 
--------------------------------------------------------
--  for Table MEMBER (back-end)
--------------------------------------------------------
 CREATE TABLE member (	
 NO                   SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 NAME                 VARCHAR(20) NOT NULL UNIQUE,
 PASSWORD             VARCHAR(200) NOT NULL,
 EMAIL                VARCHAR(320) NOT NULL
 );
 
 INSERT INTO member (name,password,email)
VALUES ('a01','1111','a01@.gmail.com');

INSERT INTO member (name,password,email)
VALUES ('a02','1111','a02@.gmail.com');

INSERT INTO member (name,password,email)
VALUES ('a03','1111','a03@.gmail.com');
 
--------------------------------------------------------
--  for Table PROD_CATEGORY
--------------------------------------------------------
  CREATE TABLE prod_category (
  CATEGORY_NO    SMALLINT  NOT NULL AUTO_INCREMENT, 
  CATEGORY_NAME  VARCHAR(50)  NOT NULL, 
  CATEGORY_DESCR VARCHAR(600) NOT NULL, 
  CONSTRAINT PROD_CATEGORY_PK PRIMARY KEY (CATEGORY_NO));
  
Insert into prod_category (CATEGORY_NAME,CATEGORY_DESCR) values ('登山','所有登山用具都在這');
Insert into prod_category (CATEGORY_NAME,CATEGORY_DESCR) values ('露營','露營用具包含營帳');
Insert into prod_category (CATEGORY_NAME,CATEGORY_DESCR) values ('燈具','頭燈/線燈等LED');

Insert into prod_category (CATEGORY_NAME,CATEGORY_DESCR) values ('A1','an');
Insert into prod_category (CATEGORY_NAME,CATEGORY_DESCR) values ('A2','ccc');
Insert into prod_category (CATEGORY_NAME,CATEGORY_DESCR) values ('B3','ccc1');

delete from  prod_category;
delete from product;
SELECT * FROM prod_category;
 
--------------------------------------------------------
--  for Table PRODUCT
--------------------------------------------------------
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

Insert into product (CATEGORY_NO,PROD_NAME,PROD_PRICE,PROD_INTRODUCE,PROD_STOCK,PROD_STATUS) values (9,'Alias',30000,'Alias Sport',100,1);
Insert into product (CATEGORY_NO,PROD_NAME,PROD_PRICE,PROD_INTRODUCE,PROD_STOCK,PROD_STATUS) values (9,'Diverge',30000,'Diverge Comp',100,1);
Insert into product (CATEGORY_NO,PROD_NAME,PROD_PRICE,PROD_INTRODUCE,PROD_STOCK,PROD_STATUS) values (10,'Power Pro',2000,'',100,1);
Insert into product (CATEGORY_NO,PROD_NAME,PROD_PRICE,PROD_INTRODUCE,PROD_STOCK,PROD_STATUS) values (10,'Roval CLX64',2000,'Roval CLX64',100,1);
Insert into product (CATEGORY_NO,PROD_NAME,PROD_PRICE,PROD_INTRODUCE,PROD_STOCK,PROD_STATUS) values (11,'S-Works',1000,'S-Work desc',100,1);
Insert into product (CATEGORY_NO,PROD_NAME,PROD_PRICE,PROD_INTRODUCE,PROD_STOCK,PROD_STATUS) values (11,'中文測試',1000,'這是中文敘述',100,1);


--------------------------------------------------------
--  for Table CART
--------------------------------------------------------

  CREATE TABLE cart (
    customer_no  SMALLINT NOT NULL,
    product_no   SMALLINT NOT NULL,
    prod_name VARCHAR(50) NOT NULL,
    prod_price INT NOT NULL,
    cart_mount INT NOT NULL,
   CONSTRAINT dual_pk PRIMARY KEY (customer_no,product_no),
   CONSTRAINT cust_FK FOREIGN KEY (customer_no) REFERENCES customer (NO),
   CONSTRAINT PROD_FK FOREIGN KEY (product_no) REFERENCES product (PRODUCT_NO)
  );


--------------------------------------------------------
--  for Table ORDER
--------------------------------------------------------

CREATE TABLE ord (
  ORD_NO      VARCHAR(20) NOT NULL, 
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
CREATE TABLE ord_details (	
  ORD_NO     VARCHAR(20) NOT NULL, 
  PRODUCT_NO SMALLINT NOT NULL,
  QUANTITY   INT,
  PROD_PRICE INT NOT NULL,
  CONSTRAINT ORD_DETAILS_FK1 FOREIGN KEY (ORD_NO) REFERENCES ord (ORD_NO),
  CONSTRAINT ORD_DETAILS_FK2 FOREIGN KEY (PRODUCT_NO) REFERENCES product (PRODUCT_NO),
  CONSTRAINT ORD_DETAILS_PK PRIMARY KEY(ORD_NO, PRODUCT_NO));
  
  
---------------------------------------------------------




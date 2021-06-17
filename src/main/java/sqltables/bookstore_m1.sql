CREATE DATABASE db_morgan_bookstore;
use db_morgan_bookstore;


--Front-End User--
 CREATE TABLE CUSTOMER (	
 NO                   SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 NAME                 VARCHAR(20) NOT NULL UNIQUE,
 PASSWORD             VARCHAR(200) NOT NULL,
 EMAIL                VARCHAR(320) NOT NULL UNIQUE
 );
 
 
 --Back-End User--
 CREATE TABLE MEMBER (	
 NO                   SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 NAME                 VARCHAR(20) NOT NULL UNIQUE,
 PASSWORD             VARCHAR(200) NOT NULL,
 EMAIL                VARCHAR(320) NOT NULL
 );
 
--Product Category--
  CREATE TABLE PROD_CATEGORY (
  CATEGORY_NO    SMALLINT  NOT NULL AUTO_INCREMENT, 
  CATEGORY_NAME  VARCHAR(50)  NOT NULL, 
  CATEGORY_DESCR VARCHAR(600) NOT NULL, 
  CONSTRAINT PROD_CATEGORY_PK PRIMARY KEY (CATEGORY_NO));
 
 --Product--
 CREATE TABLE PRODUCT (
  PRODUCT_NO     SMALLINT   AUTO_INCREMENT, 
  CATEGORY_NO    SMALLINT   NOT NULL, 
  PROD_NAME      VARCHAR(50)   NOT NULL, 
  PROD_PRICE     SMALLINT   NOT NULL, 
  PROD_INTRODUCE VARCHAR(1000) NOT NULL, 
  PROD_STOCK     SMALLINT   NOT NULL, 
  PROD_STATUS    TINYINT    NOT NULL, 
  PROD_PIC       LONGBLOB,
  CONSTRAINT PRODUCT_FK FOREIGN KEY (CATEGORY_NO) REFERENCES PROD_CATEGORY (CATEGORY_NO),
  CONSTRAINT PRODUCT_PK PRIMARY KEY (PRODUCT_NO));

  
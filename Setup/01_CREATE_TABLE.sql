USE PIZZA_HOUSE;

CREATE TABLE IF NOT EXISTS USER (
   USER_UUID   VARCHAR(100) NOT NULL UNIQUE,
   USER_NAME  VARCHAR(40) NOT NULL UNIQUE,
   FIRST_NAME VARCHAR(100) NOT NULL,
   LAST_NAME  VARCHAR(100) NOT NULL,
   PASSWORD   VARCHAR(100) NOT NULL,
   PRIMARY KEY ( USER_UUID )
);

CREATE TABLE IF NOT EXISTS SESSION (
   USER_UUID            VARCHAR(100)  NOT NULL UNIQUE,
   SESSION_TOKEN       VARCHAR(100) NOT NULL,
   CREATION_TIME       DATETIME NOT NULL,
   CREATION_EPOCH_TIME INT NOT NULL,
   FOREIGN KEY (USER_UUID) REFERENCES USER(USER_UUID)
);


CREATE TABLE IF NOT EXISTS PIZZA_TYPE (
   PIZZA_TYPE_UUID VARCHAR(100)  NOT NULL UNIQUE,
   NAME          VARCHAR(100) NOT NULL,
   DESCRIPTION   VARCHAR(1000) NOT NULL,
   PRIMARY KEY ( PIZZA_TYPE_UUID )
);

CREATE TABLE IF NOT EXISTS PIZZA_SIZE (
   PIZZA_SIZE_UUID VARCHAR(100)  NOT NULL UNIQUE,
   NAME          VARCHAR(100) NOT NULL,
   DESCRIPTION   VARCHAR(1000) NOT NULL,
   PRICE         FLOAT(5,2) NOT NULL,
   PRIMARY KEY ( PIZZA_SIZE_UUID )
);

CREATE TABLE IF NOT EXISTS PIZZA_TOPPING (
   PIZZA_TOPPING_UUID VARCHAR(100)  NOT NULL UNIQUE,
   NAME             VARCHAR(100) NOT NULL,
   DESCRIPTION      VARCHAR(1000) NOT NULL,
   PRICE            FLOAT(5,2) NOT NULL,
   PRIMARY KEY ( PIZZA_TOPPING_UUID )
);

CREATE TABLE IF NOT EXISTS PIZZA (
   PIZZA_TYPE_UUID    VARCHAR(100)  NOT NULL,
   PIZZA_SIZE_UUID    VARCHAR(100)  NOT NULL,
   PIZZA_TOPPING_UUID VARCHAR(100)  NOT NULL,
   FOREIGN KEY (PIZZA_TYPE_UUID) REFERENCES PIZZA_TYPE(PIZZA_TYPE_UUID),
   FOREIGN KEY (PIZZA_SIZE_UUID) REFERENCES PIZZA_SIZE(PIZZA_SIZE_UUID),
   FOREIGN KEY (PIZZA_TOPPING_UUID) REFERENCES PIZZA_TOPPING(PIZZA_TOPPING_UUID)
);

CREATE TABLE IF NOT EXISTS PURCHASE (
   PURCHASE_UUID         VARCHAR(100)  NOT NULL UNIQUE,
   USER_UUID             VARCHAR(100) NOT NULL,
   TOTAL_AMOUNT        FLOAT(5,2) NOT NULL,
   CREATION_TIME       DATETIME NOT NULL,
   CREATION_EPOCH_TIME INT NOT NULL,
   PRIMARY KEY (PURCHASE_UUID)
   -- ,
   -- FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);

CREATE TABLE IF NOT EXISTS SUB_ITEM_CATEGORY (
   SUB_ITEM_CATEGORY_ID    INT NOT NULL AUTO_INCREMENT,
   SUB_ITEM_NAME           VARCHAR(100) NOT NULL,
   PRIMARY KEY (SUB_ITEM_CATEGORY_ID)
);

CREATE TABLE IF NOT EXISTS PURCHASE_DETAIL (
   PURCHASE_DETAIL_UUID     VARCHAR(100)  NOT NULL UNIQUE,
   PURCHASE_UUID            VARCHAR(100)  NOT NULL,
   PIZZA_TYPE_UUID          VARCHAR(100)  NOT NULL,
   SUB_ITEM_REFERENCE_UUID  VARCHAR(100)  NOT NULL,
   SUB_ITEM_CATEGORY_ID   INT NOT NULL,
   QUANTITY               INT NOT NULL,
   SINGLE_PRICE           FLOAT(5,2) NOT NULL,
   SUB_TOTAL              FLOAT(5,2) NOT NULL,
   PRIMARY KEY (PURCHASE_DETAIL_UUID),
   FOREIGN KEY (PURCHASE_UUID) REFERENCES PURCHASE(PURCHASE_UUID)
);

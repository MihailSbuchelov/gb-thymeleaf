CREATE TABLE PRODUCT
(
    ID                 BIGSERIAL      NOT NULL PRIMARY KEY,
    TITLE              VARCHAR(255)   NOT NULL,
    COST               DECIMAL(10, 2) NOT NULL,
    MANUFACTURE_DATE   DATE           NOT NULL,
    VERSION            INT            NOT NULL DEFAULT 0,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       TIMESTAMP,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE TIMESTAMP,
    STATUS             VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE',

    UNIQUE (TITLE)
);

CREATE TABLE CART
(
    ID     BIGSERIAL NOT NULL PRIMARY KEY,
    STATUS VARCHAR(255) DEFAULT ''
);

CREATE TABLE CART_PRODUCT
(
    CART_ID    BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,

    CONSTRAINT fk_cart_product_cart
        FOREIGN KEY (CART_ID)
            REFERENCES CART (ID),

    CONSTRAINT fk_cart_product_product
        FOREIGN KEY (PRODUCT_ID)
            REFERENCES PRODUCT (ID)
);

CREATE TABLE ACCOUNT_USER
(
    ID                      BIGSERIAL    NOT NULL PRIMARY KEY,
    PASSWORD                VARCHAR(255) NOT NULL,
    USERNAME                VARCHAR(255) NOT NULL,
    firstname               VARCHAR(255) NOT NULL,
    lastname                VARCHAR(255) NOT NULL,
    account_non_expired     BOOLEAN      NOT NULL,
    account_non_locked      BOOLEAN      NOT NULL,
    credentials_non_expired BOOLEAN      NOT NULL,
    enabled                 BOOLEAN      NOT NULL
);

CREATE TABLE AUTHORITY
(
    ID   BIGSERIAL    NOT NULL PRIMARY KEY,
    ROLE VARCHAR(255) NOT NULL
);

CREATE TABLE USER_AUTHORITY
(
    USER_ID      BIGINT NOT NULL,
    AUTHORITY_ID BIGINT NOT NULL,

    PRIMARY KEY (USER_ID, AUTHORITY_ID),

    CONSTRAINT fk_user_authority_account_user
        FOREIGN KEY (USER_ID)
            REFERENCES ACCOUNT_USER (ID),

    CONSTRAINT fk_user_authority_authority
        FOREIGN KEY (AUTHORITY_ID)
            REFERENCES AUTHORITY (ID)

)
CREATE TABLE IF NOT EXISTS customers
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    bank_id             VARCHAR(255),
    name                VARCHAR(25),
    surname             VARCHAR(25),
    patronymic          VARCHAR(25),
    birth_date           DATE,
    passport_number      VARCHAR(25),
    birth_place          VARCHAR(255),
    phone_number         VARCHAR(15),
    email               VARCHAR(55),
    registration_address VARCHAR(255),
    residential_address  VARCHAR(255),
    CONSTRAINT pk_customers PRIMARY KEY (id)
);
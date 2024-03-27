CREATE SCHEMA IF NOT EXISTS reactive_spring_schema;

CREATE TABLE IF NOT EXISTS reactive_spring_schema.products
(
    id    BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    name  VARCHAR(255)                 NOT NULL,
    price NUMERIC                      NOT NULL
);

CREATE TABLE IF NOT EXISTS reactive_spring_schema.customers
(
    id    BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    name  VARCHAR(255)                 NOT NULL,
    email VARCHAR(255) UNIQUE          NOT NULL
);
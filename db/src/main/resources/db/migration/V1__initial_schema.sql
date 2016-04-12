CREATE TABLE product (
  id SERIAL,
  name VARCHAR(512) NOT NULL,
  CONSTRAINT product_pk PRIMARY KEY (id)
);

COMMENT ON TABLE product IS 'Contains info about a product';
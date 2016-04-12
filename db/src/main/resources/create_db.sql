

CREATE USER shop WITH password '<placeholder>';

CREATE DATABASE shopdb
  WITH OWNER = shop
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;

--GRANT ALL privileges ON DATABASE shopdb TO shop;

CREATE SCHEMA AUTHORIZATION shop; -- schema shop for user shop


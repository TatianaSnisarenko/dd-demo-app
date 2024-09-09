CREATE TABLE if not exists category
(
      id BIGSERIAL PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      description TEXT
);

CREATE TABLE if not exists accessory
(
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       description TEXT,
       category_id BIGINT NOT NULL,
       price DOUBLE PRECISION,
       discount DOUBLE PRECISION,
       CONSTRAINT fk_category
           FOREIGN KEY(category_id)
               REFERENCES category(id)
);

CREATE TABLE if not exists accessory_attribute
(
     id BIGSERIAL PRIMARY KEY,
     type VARCHAR(100) NOT NULL,
     value VARCHAR(255) NOT NULL,
     accessory_id BIGINT,
     CONSTRAINT fk_accessory
         FOREIGN KEY(accessory_id)
             REFERENCES accessory(id)
);

CREATE TABLE if not exists inventory
(
       id BIGSERIAL PRIMARY KEY,
       accessory_id BIGINT UNIQUE,
       quantity INT NOT NULL,
       CONSTRAINT fk_inventory_accessory
           FOREIGN KEY(accessory_id)
               REFERENCES accessory(id)
);




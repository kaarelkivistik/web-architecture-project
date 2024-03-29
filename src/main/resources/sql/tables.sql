DROP TABLE IF EXISTS service_action;
DROP TABLE IF EXISTS service_part;
DROP TABLE IF EXISTS service_device;
DROP TABLE IF EXISTS service_order;
DROP TABLE IF EXISTS service_request;

DROP TABLE IF EXISTS device;

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS customer;

DROP TABLE IF EXISTS device_type;
DROP TABLE IF EXISTS service_request_status_type;
DROP TABLE IF EXISTS service_device_status_type;
DROP TABLE IF EXISTS so_status_type;
DROP TABLE IF EXISTS service_action_status_type;
DROP TABLE IF EXISTS service_unit_type;
DROP TABLE IF EXISTS service_type;

/* klassifikaatorid */

CREATE TABLE device_type 
( device_type_id int NOT NULL,
  super_type_id int,
  level int NOT NULL,
  type_name varchar(200) NOT NULL,
  CONSTRAINT device_type_pk PRIMARY KEY (device_type_id),
  CONSTRAINT device_super_type_fk FOREIGN KEY (super_type_id) REFERENCES device_type (device_type_id)
);

CREATE TABLE service_device_status_type 
( service_device_status_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT service_device_status_type_pk PRIMARY KEY (service_device_status_type_id)
);

CREATE TABLE service_request_status_type 
( service_request_status_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT service_request_status_type_pk PRIMARY KEY (service_request_status_type_id)
);

CREATE TABLE so_status_type 
( so_status_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT so_status_type_pk PRIMARY KEY (so_status_type_id)
);

CREATE TABLE service_action_status_type 
( service_action_status_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT service_action_status_type_pk PRIMARY KEY (service_action_status_type_id)
);

CREATE TABLE service_unit_type 
( service_unit_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT service_unit_pk PRIMARY KEY (service_unit_type_id)
);

CREATE TABLE service_type 
( service_type_id int NOT NULL,
  service_unit_type_id int,
  type_name varchar(200),
  service_price numeric,
  CONSTRAINT service_type_pk PRIMARY KEY (service_type_id),
  CONSTRAINT service_unit_type_fk FOREIGN KEY (service_unit_type_id) REFERENCES service_unit_type
);

/* põhiolemid */

DROP SEQUENCE IF EXISTS customer_id;
CREATE SEQUENCE customer_id;

CREATE TABLE customer
( customer_id int NOT NULL DEFAULT nextval('customer_id'),
  name varchar(100) NOT NULL UNIQUE,
  CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

DROP SEQUENCE IF EXISTS employee_id;
CREATE SEQUENCE employee_id;

CREATE TABLE employee
( employee_id int NOT NULL DEFAULT nextval('employee_id'),
  name varchar(100) NOT NULL UNIQUE,
  CONSTRAINT employee_pk PRIMARY KEY (employee_id)
);

DROP SEQUENCE IF EXISTS device_id;
CREATE SEQUENCE device_id;

/* device olem */

CREATE TABLE device 
( device_id int NOT NULL DEFAULT nextval('device_id'),
  device_type_id int NOT NULL,
  name varchar(100) NOT NULL,
  reg_no varchar(100) NOT NULL,
  description text,
  model varchar(100),
  manufacturer varchar(100) NOT NULL,
  CONSTRAINT device_pk PRIMARY KEY (device_id),
  CONSTRAINT device_type_fk FOREIGN KEY (device_type_id) REFERENCES device_type
);

/* service olem */

DROP SEQUENCE IF EXISTS service_request_id;
CREATE SEQUENCE service_request_id;

CREATE TABLE service_request 
( service_request_id int NOT NULL DEFAULT nextval('service_request_id'),
  customer_id int NOT NULL,
  created_by int NOT NULL,
  created timestamp NOT NULL,
  service_desc_by_customer text NOT NULL,
  service_desc_by_employee text NOT NULL,
  service_request_status_type_id int,
  CONSTRAINT service_request_pk PRIMARY KEY (service_request_id),
  CONSTRAINT customer_fk FOREIGN KEY (customer_id) REFERENCES customer,
  CONSTRAINT created_by_employee_fk FOREIGN KEY (created_by) REFERENCES employee (employee_id),
  CONSTRAINT service_request_status_type_fk FOREIGN KEY (service_request_status_type_id) REFERENCES service_request_status_type
);

DROP SEQUENCE IF EXISTS service_order_id;
CREATE SEQUENCE service_order_id;

CREATE TABLE service_order 
( service_order_id int NOT NULL DEFAULT nextval('service_order_id'),
  so_status_type_id int NOT NULL,
  created_by int NOT NULL,
  service_request_id int NOT NULL,
  updated_by int NOT NULL,
  created timestamp NOT NULL,
  updated timestamp NOT NULL,
  status_changed timestamp NOT NULL,
  status_changed_by int NOT NULL,
  price_total numeric NOT NULL DEFAULT 0,
  note text,
  CONSTRAINT service_order_pk PRIMARY KEY (service_order_id),
  CONSTRAINT so_status_type_fk FOREIGN KEY (so_status_type_id) REFERENCES so_status_type,
  CONSTRAINT service_request_fk FOREIGN KEY (service_request_id) REFERENCES service_request ON DELETE CASCADE,
  CONSTRAINT created_by_employee_fk FOREIGN KEY (created_by) REFERENCES employee (employee_id),
  CONSTRAINT updated_by_employee_fk FOREIGN KEY (updated_by) REFERENCES employee (employee_id),
  CONSTRAINT status_changed_by_employee_fk FOREIGN KEY (status_changed_by) REFERENCES employee (employee_id)
);

DROP SEQUENCE IF EXISTS service_device_id;
CREATE SEQUENCE service_device_id;

CREATE TABLE service_device 
( service_device_id int NOT NULL DEFAULT nextval('service_device_id'),
  service_device_status_type_id int NOT NULL,
  device_id int NOT NULL,
  service_order_id int NOT NULL,
  to_store timestamp,
  from_store timestamp,
  status_changed timestamp,
  CONSTRAINT service_device_pk PRIMARY KEY (service_device_id),
  CONSTRAINT device_id FOREIGN KEY (device_id) REFERENCES device,
  CONSTRAINT service_device_status_type_fk FOREIGN KEY (service_device_status_type_id) REFERENCES service_device_status_type,
  CONSTRAINT service_order_fk FOREIGN KEY (service_order_id) REFERENCES service_order
);

DROP SEQUENCE IF EXISTS service_action_id;
CREATE SEQUENCE service_action_id;

CREATE TABLE service_action 
( service_action_id int NOT NULL DEFAULT nextval('service_action_id'),
  service_action_status_type_id int NOT NULL,
  service_type_id int NOT NULL,
  service_device_id int,
  service_order_id int NOT NULL,
  service_amount numeric NOT NULL,
  price numeric,
  action_description text,
  created timestamp NOT NULL,
  created_by int NOT NULL,
  CONSTRAINT service_action_pk PRIMARY KEY (service_action_id),
  CONSTRAINT service_action_status_type_fk FOREIGN KEY (service_action_status_type_id) REFERENCES service_action_status_type,
  CONSTRAINT service_type_fk FOREIGN KEY (service_type_id) REFERENCES service_type,
  CONSTRAINT service_device_fk FOREIGN KEY (service_device_id) REFERENCES service_device ON DELETE CASCADE,
  CONSTRAINT service_order_fk FOREIGN KEY (service_order_id) REFERENCES service_order,
  CONSTRAINT created_by_employee_fk FOREIGN KEY (created_by) REFERENCES employee (employee_id)
);

DROP SEQUENCE IF EXISTS service_part_id;
CREATE SEQUENCE service_part_id;

CREATE TABLE service_part
( service_part_id int NOT NULL DEFAULT nextval('service_part_id'),
  service_order_id int NOT NULL,
  service_device_id int,
  part_name text NOT NULL,
  serial_no text,
  part_count int NOT NULL,
  part_price numeric NOT NULL,
  created timestamp NOT NULL,
  created_by int NOT NULL,
  CONSTRAINT service_part_pk PRIMARY KEY (service_part_id),
  CONSTRAINT service_order_fk FOREIGN KEY (service_order_id) REFERENCES service_order,
  CONSTRAINT service_device_fk FOREIGN KEY (service_device_id) REFERENCES service_device ON DELETE CASCADE,
  CONSTRAINT created_by_employee_fk FOREIGN KEY (created_by) REFERENCES employee (employee_id)
);
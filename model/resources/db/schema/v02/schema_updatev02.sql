
ALTER TABLE crm.role DROP COLUMN IF EXISTS user_user_id;

ALTER TABLE crm.grant RENAME COLUMN object_class TO object_class_id;

-- Table: object_class
CREATE TABLE crm.object_class (
  object_class_id int  NOT NULL,
  name varchar  NOT NULL,
  CONSTRAINT object_class_pk PRIMARY KEY (object_class_id)
);

-- Reference:  grant_object_class (table: "grant")
ALTER TABLE crm."grant" ADD CONSTRAINT grant_object_class
FOREIGN KEY (object_class_id)
REFERENCES crm.object_class (object_class_id)
NOT DEFERRABLE
INITIALLY IMMEDIATE
;

-- Table: phone_type
CREATE TABLE crm.phone_type (
  phone_type int  NOT NULL,
  name varchar  NOT NULL,
  CONSTRAINT phone_type_pk PRIMARY KEY (phone_type)
);

-- Reference:  phone_phone_type (table: phone)
ALTER TABLE crm.phone ADD CONSTRAINT phone_phone_type
FOREIGN KEY (phone_type)
REFERENCES crm.phone_type (phone_type)
NOT DEFERRABLE
INITIALLY IMMEDIATE
;

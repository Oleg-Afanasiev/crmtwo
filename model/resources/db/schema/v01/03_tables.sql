-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2015-08-27 19:12:52.92


set schema 'crm';

-- tables
-- Table: comment
CREATE TABLE comment (
    comment_id bigserial  NOT NULL,
    name varchar  NOT NULL,
    comment varchar  NOT NULL,
    created timestamp  NOT NULL,
    updated timestamp  NOT NULL,
    CONSTRAINT comment_pk PRIMARY KEY (comment_id)
);



-- Table: company
CREATE TABLE company (
    company_id bigserial  NOT NULL,
    responsible_user_id bigint  NOT NULL,
    name varchar  NOT NULL,
    email varchar  NOT NULL,
    web_address varchar  NOT NULL,
    address varchar  NOT NULL,
    created timestamp  NOT NULL,
    updated timestamp  NOT NULL,
    is_deleted boolean  NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY (company_id)
);



-- Table: company_comment
CREATE TABLE company_comment (
    company_id bigint  NOT NULL,
    comment_id bigint  NOT NULL,
    CONSTRAINT company_comment_pk PRIMARY KEY (company_id,comment_id)
);



-- Table: company_file
CREATE TABLE company_file (
    company_id bigint  NOT NULL,
    file_id bigint  NOT NULL,
    CONSTRAINT company_file_pk PRIMARY KEY (company_id,file_id)
);



-- Table: company_phone
CREATE TABLE company_phone (
    company_id bigint  NOT NULL,
    phone_number_id bigint  NOT NULL,
    CONSTRAINT company_phone_pk PRIMARY KEY (company_id,phone_number_id)
);



-- Table: company_tag
CREATE TABLE company_tag (
    company_id bigint  NOT NULL,
    tag_id bigint  NOT NULL,
    CONSTRAINT company_tag_pk PRIMARY KEY (company_id,tag_id)
);



-- Table: contact
CREATE TABLE contact (
    contact_id bigserial  NOT NULL,
    company_id bigint  NOT NULL,
    responsible_user_id bigint  NOT NULL,
    name varchar  NOT NULL,
    job_position varchar  NOT NULL,
    email varchar  NOT NULL,
    skype varchar  NOT NULL,
    created timestamp  NOT NULL,
    updated timestamp  NOT NULL,
    is_deleted boolean  NOT NULL,
    CONSTRAINT contact_pk PRIMARY KEY (contact_id)
);



-- Table: contact_comment
CREATE TABLE contact_comment (
    contact_id bigint  NOT NULL,
    comment_id bigint  NOT NULL,
    CONSTRAINT contact_comment_pk PRIMARY KEY (contact_id,comment_id)
);



-- Table: contact_file
CREATE TABLE contact_file (
    file_id bigint  NOT NULL,
    contact_id bigint  NOT NULL,
    CONSTRAINT contact_file_pk PRIMARY KEY (file_id,contact_id)
);



-- Table: contact_phone
CREATE TABLE contact_phone (
    phone_number_id bigint  NOT NULL,
    contact_id bigint  NOT NULL,
    CONSTRAINT contact_phone_pk PRIMARY KEY (phone_number_id,contact_id)
);



-- Table: contact_tag
CREATE TABLE contact_tag (
    contact_id bigint  NOT NULL,
    tag_id bigint  NOT NULL,
    CONSTRAINT contact_tag_pk PRIMARY KEY (contact_id,tag_id)
);



-- Table: deal
CREATE TABLE deal (
    deal_id bigserial  NOT NULL,
    responsible_user_id bigint  NOT NULL,
    status_id int  NOT NULL,
    name varchar  NOT NULL,
    budget money  NOT NULL,
    created timestamp  NOT NULL,
    updated timestamp  NOT NULL,
    is_deleted boolean  NOT NULL,
    CONSTRAINT deal_pk PRIMARY KEY (deal_id)
);



-- Table: deal_comment
CREATE TABLE deal_comment (
    comment_id bigint  NOT NULL,
    deal_id bigint  NOT NULL,
    CONSTRAINT deal_comment_pk PRIMARY KEY (comment_id,deal_id)
);



-- Table: deal_company
CREATE TABLE deal_company (
    company_id bigint  NOT NULL,
    deal_id bigint  NOT NULL,
    CONSTRAINT deal_company_pk PRIMARY KEY (company_id,deal_id)
);



-- Table: deal_contact
CREATE TABLE deal_contact (
    deal_id bigint  NOT NULL,
    contact_id bigint  NOT NULL,
    CONSTRAINT deal_contact_pk PRIMARY KEY (deal_id,contact_id)
);



-- Table: deal_file
CREATE TABLE deal_file (
    deal_id bigint  NOT NULL,
    file_id bigint  NOT NULL,
    CONSTRAINT deal_file_pk PRIMARY KEY (deal_id,file_id)
);



-- Table: deal_status
CREATE TABLE deal_status (
    status_id serial  NOT NULL,
    name varchar  NOT NULL,
    CONSTRAINT deal_status_pk PRIMARY KEY (status_id)
);



-- Table: deal_tag
CREATE TABLE deal_tag (
    deal_id bigint  NOT NULL,
    tag_id bigint  NOT NULL,
    CONSTRAINT deal_tag_pk PRIMARY KEY (deal_id,tag_id)
);



-- Table: file
CREATE TABLE file (
    file_id bigserial  NOT NULL,
    file_path varchar  NOT NULL,
    file_mime_type varchar  NOT NULL,
    created timestamp  NOT NULL,
    updated timestamp  NOT NULL,
    CONSTRAINT file_pk PRIMARY KEY (file_id)
);



-- Table: "grant"
CREATE TABLE "grant" (
    grant_id bigserial  NOT NULL,
    role_id int  NOT NULL,
    object_class int  NOT NULL,
    is_deleted boolean  NOT NULL,
    CONSTRAINT grant_pk PRIMARY KEY (grant_id)
);



-- Table: phone
CREATE TABLE phone (
    phone_number_id bigserial  NOT NULL,
    phone_type int  NOT NULL,
    phone_number varchar  NOT NULL,
    CONSTRAINT phone_pk PRIMARY KEY (phone_number_id)
);



-- Table: role
CREATE TABLE role (
    role_id serial  NOT NULL,
    name varchar  NOT NULL,
    user_user_id int  NOT NULL,
    is_deleted boolean  NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (role_id)
);



-- Table: tag
CREATE TABLE tag (
    tag_id bigserial  NOT NULL,
    name varchar  NOT NULL,
    CONSTRAINT tag_pk PRIMARY KEY (tag_id)
);



-- Table: task
CREATE TABLE task (
    task_id bigserial  NOT NULL,
    task_type_id int  NOT NULL,
    responsible_user_id bigint  NOT NULL,
    company_id bigint  NOT NULL,
    deal_id bigint  NOT NULL,
    contact_id bigint  NOT NULL,
    period_id int  NOT NULL,
    due_date timestamp  NOT NULL,
    comment varchar  NULL,
    created timestamp  NOT NULL,
    updated timestamp  NOT NULL,
    is_deleted boolean  NOT NULL,
    CONSTRAINT task_pk PRIMARY KEY (task_id)
);



-- Table: task_comment
CREATE TABLE task_comment (
    task_id bigint  NOT NULL,
    comment_id bigint  NOT NULL,
    CONSTRAINT task_comment_pk PRIMARY KEY (task_id,comment_id)
);



-- Table: task_period
CREATE TABLE task_period (
    period_id serial  NOT NULL,
    period_name varchar  NOT NULL,
    CONSTRAINT task_period_pk PRIMARY KEY (period_id)
);



-- Table: task_type
CREATE TABLE task_type (
    task_type_id serial  NOT NULL,
    name varchar(30)  NOT NULL,
    CONSTRAINT task_type_pk PRIMARY KEY (task_type_id)
);



-- Table: "user"
CREATE TABLE "user" (
    user_id bigserial  NOT NULL,
    role_id int  NOT NULL,
    username varchar(30)  NOT NULL,
    last_name varchar(30)  NOT NULL,
    first_name varchar(30)  NOT NULL,
    email varchar(30)  NOT NULL,
    created timestamp  NOT NULL,
    updated timestamp  NOT NULL,
    is_deleted boolean  NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (user_id)
);







-- foreign keys
-- Reference:  comment_comment_to_company (table: company_comment)


ALTER TABLE company_comment ADD CONSTRAINT comment_comment_to_company
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  comment_contact_to_comment (table: contact_comment)


ALTER TABLE contact_comment ADD CONSTRAINT comment_contact_to_comment
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  comment_task_comment (table: task_comment)


ALTER TABLE task_comment ADD CONSTRAINT comment_task_comment
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  comment_to_company_company (table: company_comment)


ALTER TABLE company_comment ADD CONSTRAINT comment_to_company_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  company_company_to_file (table: company_file)


ALTER TABLE company_file ADD CONSTRAINT company_company_to_file
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  company_to_file_file (table: company_file)


ALTER TABLE company_file ADD CONSTRAINT company_to_file_file
    FOREIGN KEY (file_id)
    REFERENCES file (file_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  company_to_phone_company (table: company_phone)


ALTER TABLE company_phone ADD CONSTRAINT company_to_phone_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  company_to_tag_company (table: company_tag)


ALTER TABLE company_tag ADD CONSTRAINT company_to_tag_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_company (table: contact)


ALTER TABLE contact ADD CONSTRAINT contact_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_to_comment_contact (table: contact_comment)


ALTER TABLE contact_comment ADD CONSTRAINT contact_to_comment_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_to_file_contact (table: contact_file)


ALTER TABLE contact_file ADD CONSTRAINT contact_to_file_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_to_phone_contact (table: contact_phone)


ALTER TABLE contact_phone ADD CONSTRAINT contact_to_phone_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_to_phone_phone (table: contact_phone)


ALTER TABLE contact_phone ADD CONSTRAINT contact_to_phone_phone
    FOREIGN KEY (phone_number_id)
    REFERENCES phone (phone_number_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_to_tag_contact (table: contact_tag)


ALTER TABLE contact_tag ADD CONSTRAINT contact_to_tag_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_comment_deal_to_comment (table: deal_comment)


ALTER TABLE deal_comment ADD CONSTRAINT deal_comment_deal_to_comment
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_deal_status (table: deal)


ALTER TABLE deal ADD CONSTRAINT deal_deal_status
    FOREIGN KEY (status_id)
    REFERENCES deal_status (status_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_deal_to_company (table: deal_company)


ALTER TABLE deal_company ADD CONSTRAINT deal_deal_to_company
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_deal_to_tag (table: deal_tag)


ALTER TABLE deal_tag ADD CONSTRAINT deal_deal_to_tag
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_file_deal (table: deal_file)


ALTER TABLE deal_file ADD CONSTRAINT deal_file_deal
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_to_comment_deal (table: deal_comment)


ALTER TABLE deal_comment ADD CONSTRAINT deal_to_comment_deal
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_to_company_company (table: deal_company)


ALTER TABLE deal_company ADD CONSTRAINT deal_to_company_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_to_contact_contact (table: deal_contact)


ALTER TABLE deal_contact ADD CONSTRAINT deal_to_contact_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_to_contact_deal (table: deal_contact)


ALTER TABLE deal_contact ADD CONSTRAINT deal_to_contact_deal
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_to_tag_tag (table: deal_tag)


ALTER TABLE deal_tag ADD CONSTRAINT deal_to_tag_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (tag_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_user (table: deal)


ALTER TABLE deal ADD CONSTRAINT deal_user
    FOREIGN KEY (responsible_user_id)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  file_contact_to_file (table: contact_file)


ALTER TABLE contact_file ADD CONSTRAINT file_contact_to_file
    FOREIGN KEY (file_id)
    REFERENCES file (file_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  file_deal_file (table: deal_file)


ALTER TABLE deal_file ADD CONSTRAINT file_deal_file
    FOREIGN KEY (file_id)
    REFERENCES file (file_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  grant_role (table: "grant")


ALTER TABLE "grant" ADD CONSTRAINT grant_role
    FOREIGN KEY (role_id)
    REFERENCES role (role_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  phone_company_to_phone (table: company_phone)


ALTER TABLE company_phone ADD CONSTRAINT phone_company_to_phone
    FOREIGN KEY (phone_number_id)
    REFERENCES phone (phone_number_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  role_user (table: "user")


ALTER TABLE "user" ADD CONSTRAINT role_user
    FOREIGN KEY (role_id)
    REFERENCES role (role_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  tag_company_to_tag (table: company_tag)


ALTER TABLE company_tag ADD CONSTRAINT tag_company_to_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (tag_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  tag_contact_to_tag (table: contact_tag)


ALTER TABLE contact_tag ADD CONSTRAINT tag_contact_to_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (tag_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  task_comment_task (table: task_comment)


ALTER TABLE task_comment ADD CONSTRAINT task_comment_task
    FOREIGN KEY (task_id)
    REFERENCES task (task_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  task_period_task (table: task)


ALTER TABLE task ADD CONSTRAINT task_period_task
    FOREIGN KEY (period_id)
    REFERENCES task_period (period_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  tasks_companies (table: task)


ALTER TABLE task ADD CONSTRAINT tasks_companies
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  tasks_contacts (table: task)


ALTER TABLE task ADD CONSTRAINT tasks_contacts
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  tasks_deals (table: task)


ALTER TABLE task ADD CONSTRAINT tasks_deals
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  tasks_task_types (table: task)


ALTER TABLE task ADD CONSTRAINT tasks_task_types
    FOREIGN KEY (task_type_id)
    REFERENCES task_type (task_type_id)
    ON DELETE  RESTRICT
    ON UPDATE  RESTRICT
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  tasks_users (table: task)


ALTER TABLE task ADD CONSTRAINT tasks_users
    FOREIGN KEY (responsible_user_id)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  user_company (table: company)


ALTER TABLE company ADD CONSTRAINT user_company
    FOREIGN KEY (responsible_user_id)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  user_contact (table: contact)


ALTER TABLE contact ADD CONSTRAINT user_contact
    FOREIGN KEY (responsible_user_id)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;






-- End of file.


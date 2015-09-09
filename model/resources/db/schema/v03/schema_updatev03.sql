
ALTER TABLE crmtwo.crm.task ALTER COLUMN contact_id DROP NOT NULL;
ALTER TABLE crmtwo.crm.task ALTER COLUMN deal_id DROP NOT NULL;
ALTER TABLE crmtwo.crm.task ALTER COLUMN company_id DROP NOT NULL;

ALTER TABLE crmtwo.crm.contact ALTER COLUMN company_id DROP NOT NULL;

ALTER TABLE crmtwo.crm.user ALTER COLUMN created SET DEFAULT now();
ALTER TABLE crmtwo.crm.user ALTER COLUMN updated SET DEFAULT now();
ALTER TABLE crmtwo.crm.user ALTER COLUMN is_deleted SET DEFAULT FALSE;

ALTER TABLE crmtwo.crm.task ALTER COLUMN is_deleted SET DEFAULT FALSE;
ALTER TABLE crmtwo.crm.task ALTER COLUMN created SET DEFAULT now();
ALTER TABLE crmtwo.crm.task ALTER COLUMN updated SET DEFAULT now();

ALTER TABLE crmtwo.crm.contact ALTER COLUMN is_deleted SET DEFAULT FALSE;
ALTER TABLE crmtwo.crm.contact ALTER COLUMN created SET DEFAULT now();
ALTER TABLE crmtwo.crm.contact ALTER COLUMN updated SET DEFAULT now();

ALTER TABLE crmtwo.crm.company ALTER COLUMN is_deleted SET DEFAULT FALSE;
ALTER TABLE crmtwo.crm.company ALTER COLUMN created SET DEFAULT now();
ALTER TABLE crmtwo.crm.company ALTER COLUMN updated SET DEFAULT now();

ALTER TABLE crmtwo.crm.deal ALTER COLUMN is_deleted SET DEFAULT FALSE;
ALTER TABLE crmtwo.crm.deal ALTER COLUMN created SET DEFAULT now();
ALTER TABLE crmtwo.crm.deal ALTER COLUMN updated SET DEFAULT now();

ALTER TABLE crmtwo.crm.comment ADD COLUMN is_deleted BOOL;
ALTER TABLE crmtwo.crm.comment ALTER COLUMN is_deleted SET DEFAULT FALSE;
ALTER TABLE crmtwo.crm.comment ALTER COLUMN created SET DEFAULT now();
ALTER TABLE crmtwo.crm.comment ALTER COLUMN updated SET DEFAULT now();

ALTER TABLE crm.deal ALTER COLUMN budget TYPE NUMERIC USING budget::NUMERIC;

ALTER TABLE crmtwo.crm.task RENAME COLUMN comment TO description;

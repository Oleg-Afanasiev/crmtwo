ALTER TABLE crm.file ADD content BYTEA NULL;
ALTER TABLE crm.file ALTER COLUMN file_path DROP NOT NULL;
ALTER TABLE crm.file RENAME COLUMN file_path TO file_name;

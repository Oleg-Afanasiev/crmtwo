TRUNCATE TABLE crm.role RESTART IDENTITY CASCADE;
INSERT INTO crm.role (name, is_deleted) VALUES ('admin', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('operator', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('engineer', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('read_only', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('sysadm', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('mpp', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('gsdt', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('backup', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('guest', false);
INSERT INTO crm.role (name, is_deleted) VALUES ('custom', false);

TRUNCATE TABLE crm.user RESTART IDENTITY CASCADE;
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'puva', 'Vasiliy', 'Pupkin', 'puva@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'siiva', 'Sidorov', 'Ivan', 'siiva@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'dmma', 'Dmitriev', 'Maxim', 'dmma@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'vlbe', 'Vladimir', 'Beluy', 'vlbe@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'sema', 'Sergienko', 'Maxim', 'sema@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'kaal', 'Kartomin', 'Alexandr', 'kaal@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'alel', 'Alekseeva', 'Elena', 'alel@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'aban', 'Abahina', 'Anastasia', 'aban@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'avma', 'Avdeeva', 'Mariya', 'avma@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'miev', 'Mironov', 'Evgeniy', 'miev@mail.ru', current_timestamp, current_timestamp, FALSE);

TRUNCATE TABLE crm.file RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\file1.txt', 'text/plain', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\spravka0.pdf', 'application/pdf', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\screenshot003.jpg', 'image/jpeg', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\screenshot9.png', 'image/png', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\config33.xml', 'text/xml', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\review.docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\teachers.xlsx', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\presentation.pptx', 'application/vnd.openxmlformats-officedocument.presentationml.presentation', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\screencast.avi', 'video/avi', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.file (file_path, file_mime_type, created, updated)
VALUES ('c:\files\video.mp4', 'video/mp4', current_timestamp, current_timestamp);

TRUNCATE TABLE crm.comment RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('task review', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('change entity comment', 'Hello  I am robot!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('Comment about little traktorists', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('new commewnt', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('Where is Comment?', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('tests failed', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('We are butterflies', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('What is this?', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('task review2', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);
INSERT INTO crmtwo.crm.comment (name, comment, created, updated)
VALUES ('task review1', 'bla-bla-bla ololololo 111111!!!!!', current_timestamp, current_timestamp);

TRUNCATE TABLE crmtwo.crm.phone_type RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.phone_type (phone_type, name) VALUES (1, '???????');
INSERT INTO crmtwo.crm.phone_type (phone_type, name) VALUES (2, '?????????');
INSERT INTO crmtwo.crm.phone_type (phone_type, name) VALUES (3, '????????');

TRUNCATE TABLE crmtwo.crm.phone RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (1, '1354326755');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '5423626234');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (2, '4345654379');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '5454354365');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (2, '4765283673');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '7654937058');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (2, '6753455697');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '5487635947');

TRUNCATE TABLE crmtwo.crm.tag RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.tag (name) VALUES ('urgent');
INSERT INTO crmtwo.crm.tag (name) VALUES ('new deal');
INSERT INTO crmtwo.crm.tag (name) VALUES ('BSDM');
INSERT INTO crmtwo.crm.tag (name) VALUES ('important');
INSERT INTO crmtwo.crm.tag (name) VALUES ('high costs');
INSERT INTO crmtwo.crm.tag (name) VALUES ('low costs');
INSERT INTO crmtwo.crm.tag (name) VALUES ('global');
INSERT INTO crmtwo.crm.tag (name) VALUES ('Asia');
INSERT INTO crmtwo.crm.tag (name) VALUES ('CIS region');
INSERT INTO crmtwo.crm.tag (name) VALUES ('Africa');

TRUNCATE TABLE crmtwo.crm.task_type RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.task_type (task_type_id, name) VALUES (1, 'Follow-up');
INSERT INTO crmtwo.crm.task_type (task_type_id, name) VALUES (2, 'Встреча');
INSERT INTO crmtwo.crm.task_type (task_type_id, name) VALUES (3, 'Другой');

TRUNCATE TABLE crmtwo.crm.task_period RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (1, 'Сегодня');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (2, 'Завтра');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (3, 'Следующая неделя');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (4, 'Следующий месяц');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (5, 'Следующий год');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (6, 'Календарь (выбор даты)');

TRUNCATE TABLE crmtwo.crm.company RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (1, 'Goodle', 'goodle@doodle.com', 'goodle.com', '123, Pushkin str., San-Francisco', '2015-08-21 18:00:00', '2015-08-21 18:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (2, 'Yampex', 'yampex@yanpex.com', 'yampex.com', '124, Long str., Dehli', '2015-08-21 19:00:00', '2015-08-21 19:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (3, 'Duck', 'Duck@doodle.com', 'Duck.com', '125, High str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');

TRUNCATE TABLE crmtwo.crm.company_phone RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (1, 1);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (1, 2);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (1, 3);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (2, 4);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (2, 5);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (3, 6);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (3, 7);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (3, 8);

TRUNCATE TABLE crmtwo.crm.company_comment RESTART IDENTITY CASCADE;
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (1, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (1, 2);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (2, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (2, 3);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (3, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (3, 2);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (4, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (4, 2);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (5, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (5, 2);

INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (2, 23, 'Vasiliy Pupkin', 'manager', 'pupkav@gmail.com', 'vpupka_skyp', current_timestamp, current_timestamp, FALSE );

INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (5, 24, 'Sidiriv', 'operator', 'siop@gmail.com', 'siop_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (7, 25, 'Petrov', 'director', 'peda@gmail.com', 'peda_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (6, 26, 'water clear', 'salesman', 'sasma@gmail.com', 'sasam_skyp', current_timestamp, current_timestamp, FALSE );

INSERT INTO crmtwo.crm.deal_status (name) VALUES ('идут переговоры');
INSERT INTO crmtwo.crm.deal_status (name) VALUES ('сделали предложение');
INSERT INTO crmtwo.crm.deal_status (name) VALUES ('ожидаем ответ клиента');

INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (23, 1, 'сделка на миллион', 1000000, current_timestamp, current_timestamp, FALSE);

INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, comment, created, updated, is_deleted)
VALUES (1, 23, 2, 2, 2, 5, current_timestamp, 'olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, comment, created, updated, is_deleted)
VALUES (2, 23, 2, 2, 3, 4, current_timestamp , '222olollo comment', current_timestamp, current_timestamp, FALSE );



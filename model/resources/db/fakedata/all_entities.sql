TRUNCATE TABLE crmtwo.crm.role RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.user RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.file RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.comment RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.phone_type RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.phone RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.tag RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.task_type RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.task_period RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.company_comment RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.company_phone RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.company RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.task RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.contact RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.deal RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.deal_contact RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.contact_phone RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.task_comment RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.contact_tag RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.contact_comment RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.deal_comment RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.deal_file RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.deal_tag RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.deal_status RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.deal_company RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.company_tag RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.company_file RESTART IDENTITY CASCADE;
TRUNCATE TABLE crmtwo.crm.contact_file RESTART IDENTITY CASCADE;




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


INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (1, 'puva', 'Vasiliy', 'Pupkin', 'puva@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (2, 'siiva', 'Sidorov', 'Ivan', 'siiva@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (3, 'dmma', 'Dmitriev', 'Maxim', 'dmma@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (4, 'vlbe', 'Vladimir', 'Beluy', 'vlbe@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (5, 'sema', 'Sergienko', 'Maxim', 'sema@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (6, 'kaal', 'Kartomin', 'Alexandr', 'kaal@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (7, 'alel', 'Alekseeva', 'Elena', 'alel@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (8, 'aban', 'Abahina', 'Anastasia', 'aban@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (9, 'avma', 'Avdeeva', 'Mariya', 'avma@mail.ru', current_timestamp, current_timestamp, FALSE);
INSERT INTO crm."user" (role_id, username, last_name, first_name, email, created, updated, is_deleted)
VALUES (10, 'miev', 'Mironov', 'Evgeniy', 'miev@mail.ru', current_timestamp, current_timestamp, FALSE);


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


INSERT INTO crmtwo.crm.phone_type (phone_type, name) VALUES (1, 'Рабочий');
INSERT INTO crmtwo.crm.phone_type (phone_type, name) VALUES (2, 'Мобильный');
INSERT INTO crmtwo.crm.phone_type (phone_type, name) VALUES (3, 'Домашний');


INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (1, '1354326755');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '5423626234');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (2, '4345654379');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '5454354365');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (2, '4765283673');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '7654937058');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (2, '6753455697');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '5487635947');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (2, '9998');
INSERT INTO crmtwo.crm.phone (phone_type, phone_number) VALUES (3, '78787');

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


INSERT INTO crmtwo.crm.task_type (task_type_id, name) VALUES (1, 'Follow-up');
INSERT INTO crmtwo.crm.task_type (task_type_id, name) VALUES (2, 'Встреча');
INSERT INTO crmtwo.crm.task_type (task_type_id, name) VALUES (3, 'Другой');


INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (1, 'Сегодня');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (2, 'Завтра');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (3, 'Следующая неделя');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (4, 'Следующий месяц');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (5, 'Следующий год');
INSERT INTO crmtwo.crm.task_period (period_id, period_name) VALUES (6, 'Календарь (выбор даты)');


INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (1, 'Goodle', 'goodle@doodle.com', 'goodle.com', '123, Pushkin str., San-Francisco', '2015-08-21 18:00:00', '2015-08-21 18:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (2, 'Yampex', 'yampex@yanpex.com', 'yampex.com', '124, Long str., Dehli', '2015-08-21 19:00:00', '2015-08-21 19:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (3, 'Duck', 'Duck@doodle.com', 'Duck.com', '125, High str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (4, 'KFC', 'KFC@doodle.com', 'KFC.com', '125, High str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (5, 'McDonalds', 'McDonalds@McDonalds.com', 'McDonalds.com', '125, High str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (6, 'BMS-Consulting', 'BMS-Consulting@BMS-Consulting.com', 'BMS-Consulting.com', '125, High str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (7, 'Incom', 'Incom@Incom.com', 'Incom.com', '125, High str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (8, 'FireFly', 'FireFly@FireFly.com', 'FireFly.com', '125, High str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (9, 'Cisco', 'Cisco@Cisco.com', 'Cisco.com', '125, Cisco str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');
INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)
VALUES (10, 'IBM', 'IBM@IBM.com', 'IBM.com', '125, IBM str., Trenchtown', '2015-08-22 14:00:00', '2015-08-22 14:00:00', 'false');




INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (1, 1);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (2, 2);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (3, 3);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (4, 4);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (5, 5);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (6, 6);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (7, 7);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (8, 8);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (8, 1);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (7, 2);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (6, 3);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (5, 4);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (4, 5);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (3, 6);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (2, 7);
INSERT INTO crmtwo.crm.company_phone (company_id, phone_number_id) VALUES (1, 8);


INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (1, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (2, 2);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (3, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (4, 3);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (5, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (6, 2);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (7, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (8, 2);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (9, 1);
INSERT INTO crmtwo.crm.company_comment (company_id, comment_id) VALUES (10, 2);

INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (1, 1, 'Vasiliy Pupkin', 'manager', 'pupkav@gmail.com', 'vpupka_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (2, 2, 'Dato Pun', 'manager', 'Pun@gmail.com', 'Punka_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (3, 3, 'Nevedita Govda', 'engineer', 'Govda@gmail.com', 'Govda_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (4, 4, 'Robin Kumar', 'engineer', 'Kumar@gmail.com', 'Kumar_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (5, 5, 'Vankar Pruphabdi', 'Porno star', 'Vankar@gmail.com', 'Vankar_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (6, 6, 'Naomi Russel', 'Porno star', 'Naomi@gmail.com', 'Naomi_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (7, 7, 'Sergey Grytsuk', 'QA eng.', 'Grytsuk@gmail.com', 'Grytsuk', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (8, 8, 'Andey Odesskiy', 'QA Lead eng.', 'Odesskiy@gmail.com', 'Odesskiy', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (9, 9, 'Стив Джобс', 'CEO', 'stiv@gmail.com', 'stiv0007', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (10, 10, 'Freddy', 'CEO', 'Freddy@gmail.com', 'Freddy', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (5, 4, 'Sidiriv', 'operator', 'siop@gmail.com', 'siop_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (7, 5, 'Petrov', 'director', 'peda@gmail.com', 'peda_skyp', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)
VALUES (6, 6, 'water clear', 'salesman', 'sasma@gmail.com', 'sasam_skyp', current_timestamp, current_timestamp, FALSE );

INSERT INTO crmtwo.crm.deal_status (name) VALUES ('идут переговоры');
INSERT INTO crmtwo.crm.deal_status (name) VALUES ('сделали предложение');
INSERT INTO crmtwo.crm.deal_status (name) VALUES ('ожидаем ответ клиента');

INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (1, 1, 'сделка на миллион', 1000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (2, 2, 'сделка на 2 миллионa', 2000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (3, 3, 'сделка на 3 миллионa', 4000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (4, 3, 'сделка на 4 миллионa', 3000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (5, 2, 'сделка на 5 миллионов', 5000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (6, 1, 'сделка на 6 миллионов', 6000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (7, 1, 'сделка на 7 миллионов', 7000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (8, 2, 'сделка на 8 миллионов', 8000000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (9, 3, 'сделка на 9 миллионов', 900000, current_timestamp, current_timestamp, FALSE);
INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)
VALUES (10, 3, 'сделка на 1000 миллионов', 1000, current_timestamp, current_timestamp, FALSE);


INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (1, 1, 1, 1, 1, 1, current_timestamp, 'olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (2, 2, 2, 2, 2, 2, current_timestamp , '222olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (3, 3, 3, 3, 3, 3, current_timestamp , '333olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (1, 4, 4, 4, 4, 3, current_timestamp , '444olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (2, 5, 5, 5, 5, 3, current_timestamp , '555olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (3, 6, 6, 6, 6, 3, current_timestamp , '666olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (3, 7, 7, 7, 7, 3, current_timestamp , '777olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (3, 8, 8, 8, 8, 3, current_timestamp , '888olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (3, 9, 9, 9, 9, 3, current_timestamp , '999olollo comment', current_timestamp, current_timestamp, FALSE );
INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)
VALUES (3, 10, 10, 10, 10, 3, current_timestamp , '1000olollo comment', current_timestamp, current_timestamp, FALSE );


INSERT INTO contact_comment (contact_id, comment_id) VALUES (1, 1);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (2, 2);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (3, 3);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (4, 4);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (5, 5);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (6, 6);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (7, 7);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (8, 8);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (9, 9);
INSERT INTO contact_comment (contact_id, comment_id) VALUES (10, 10);


INSERT INTO deal_comment (deal_id, comment_id) VALUES (1, 1);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (2, 2);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (3, 3);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (4, 4);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (5, 5);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (6, 6);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (7, 7);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (8, 8);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (9, 9);
INSERT INTO deal_comment (deal_id, comment_id) VALUES (10, 10);

INSERT INTO task_comment (task_id, comment_id) VALUES (1, 1);
INSERT INTO task_comment (task_id, comment_id) VALUES (2, 2);
INSERT INTO task_comment (task_id, comment_id) VALUES (3, 3);
INSERT INTO task_comment (task_id, comment_id) VALUES (4, 4);
INSERT INTO task_comment (task_id, comment_id) VALUES (5, 5);
INSERT INTO task_comment (task_id, comment_id) VALUES (6, 6);
INSERT INTO task_comment (task_id, comment_id) VALUES (7, 7);
INSERT INTO task_comment (task_id, comment_id) VALUES (8, 8);
INSERT INTO task_comment (task_id, comment_id) VALUES (9, 9);
INSERT INTO task_comment (task_id, comment_id) VALUES (10, 10);

INSERT INTO deal_contact (contact_id, deal_id) VALUES (1, 1);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (2, 2);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (3, 3);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (4, 4);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (5, 5);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (6, 6);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (7, 7);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (8, 8);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (9, 9);
INSERT INTO deal_contact (contact_id, deal_id) VALUES (10, 10);

INSERT INTO deal_company (company_id, deal_id) VALUES (1, 1);
INSERT INTO deal_company (company_id, deal_id) VALUES (2, 2);
INSERT INTO deal_company (company_id, deal_id) VALUES (3, 3);
INSERT INTO deal_company (company_id, deal_id) VALUES (4, 4);
INSERT INTO deal_company (company_id, deal_id) VALUES (5, 5);
INSERT INTO deal_company (company_id, deal_id) VALUES (6, 6);
INSERT INTO deal_company (company_id, deal_id) VALUES (7, 7);
INSERT INTO deal_company (company_id, deal_id) VALUES (8, 8);
INSERT INTO deal_company (company_id, deal_id) VALUES (9, 9);
INSERT INTO deal_company (company_id, deal_id) VALUES (10, 10);

INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (1, 1);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (2, 2);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (3, 3);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (4, 4);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (5, 5);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (6, 6);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (7, 7);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (8, 8);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (9, 9);
INSERT INTO contact_phone (contact_id, phone_number_id) VALUES (10, 10);

INSERT INTO contact_tag (contact_id, tag_id) VALUES (1, 1);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (2, 2);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (3, 3);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (4, 4);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (5, 5);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (6, 6);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (7, 7);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (8, 8);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (9, 9);
INSERT INTO contact_tag (contact_id, tag_id) VALUES (10, 10);

INSERT INTO deal_tag (deal_id, tag_id) VALUES (1, 1);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (2, 2);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (3, 3);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (4, 4);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (5, 5);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (6, 6);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (7, 7);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (8, 8);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (9, 9);
INSERT INTO deal_tag (deal_id, tag_id) VALUES (10, 10);

INSERT INTO company_tag (company_id, tag_id) VALUES (1, 1);
INSERT INTO company_tag (company_id, tag_id) VALUES (2, 2);
INSERT INTO company_tag (company_id, tag_id) VALUES (3, 3);
INSERT INTO company_tag (company_id, tag_id) VALUES (4, 4);
INSERT INTO company_tag (company_id, tag_id) VALUES (5, 5);
INSERT INTO company_tag (company_id, tag_id) VALUES (6, 6);
INSERT INTO company_tag (company_id, tag_id) VALUES (7, 7);
INSERT INTO company_tag (company_id, tag_id) VALUES (8, 8);
INSERT INTO company_tag (company_id, tag_id) VALUES (9, 9);
INSERT INTO company_tag (company_id, tag_id) VALUES (10, 10);

INSERT INTO company_file (company_id, file_id) VALUES (1, 1);
INSERT INTO company_file (company_id, file_id) VALUES (2, 2);
INSERT INTO company_file (company_id, file_id) VALUES (3, 3);
INSERT INTO company_file (company_id, file_id) VALUES (4, 4);
INSERT INTO company_file (company_id, file_id) VALUES (5, 5);
INSERT INTO company_file (company_id, file_id) VALUES (6, 6);
INSERT INTO company_file (company_id, file_id) VALUES (7, 7);
INSERT INTO company_file (company_id, file_id) VALUES (8, 8);
INSERT INTO company_file (company_id, file_id) VALUES (9, 9);
INSERT INTO company_file (company_id, file_id) VALUES (10, 10);

INSERT INTO contact_file (contact_id, file_id) VALUES (1, 1);
INSERT INTO contact_file (contact_id, file_id) VALUES (2, 2);
INSERT INTO contact_file (contact_id, file_id) VALUES (3, 3);
INSERT INTO contact_file (contact_id, file_id) VALUES (4, 4);
INSERT INTO contact_file (contact_id, file_id) VALUES (5, 5);
INSERT INTO contact_file (contact_id, file_id) VALUES (6, 6);
INSERT INTO contact_file (contact_id, file_id) VALUES (7, 7);
INSERT INTO contact_file (contact_id, file_id) VALUES (8, 8);
INSERT INTO contact_file (contact_id, file_id) VALUES (9, 9);
INSERT INTO contact_file (contact_id, file_id) VALUES (10, 10);

INSERT INTO deal_file (deal_id, file_id) VALUES (1, 1);
INSERT INTO deal_file (deal_id, file_id) VALUES (2, 2);
INSERT INTO deal_file (deal_id, file_id) VALUES (3, 3);
INSERT INTO deal_file (deal_id, file_id) VALUES (4, 4);
INSERT INTO deal_file (deal_id, file_id) VALUES (5, 5);
INSERT INTO deal_file (deal_id, file_id) VALUES (6, 6);
INSERT INTO deal_file (deal_id, file_id) VALUES (7, 7);
INSERT INTO deal_file (deal_id, file_id) VALUES (8, 8);
INSERT INTO deal_file (deal_id, file_id) VALUES (9, 9);
INSERT INTO deal_file (deal_id, file_id) VALUES (10, 10);

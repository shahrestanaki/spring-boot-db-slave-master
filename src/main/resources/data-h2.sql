set mode Oracle;
create table SCHEMA_USERS.USERS (
                                 user_id numeric(19) not null,
                                 creation_date timestamp(6) ,
                                 creator_user_id numeric(19) ,
                                 last_update timestamp(6),
                                 updater_user_id numeric(19),
                                 logged_in numeric(1),
                                 current_login timestamp(6),
                                 current_login_ip varchar(15),
                                 dashboard_id numeric(19),
                                 deactivation_reason numeric(10),
                                 doc_category_dashboard_id numeric(19),
                                 doc_category_report_id numeric(19),
                                 first_name varchar(50),
                                 given_name varchar(100),
                                 is_active numeric(10),
                                 last_login timestamp(6),
                                 last_login_ip varchar(15),
                                 last_name varchar(50),
                                 org_id numeric(19),
                                 otp varchar(100),
                                 password varchar(255) ,
                                 preferred_locale varchar(5),
                                 principle_id numeric(19),
                                 rescue_cell_phone varchar(20),
                                 rescue_cellphone_invalid varchar(1),
                                 rescue_email varchar(100),
                                 rescue_email_invalid varchar(50),
                                 security_question numeric(10),
                                 security_question_answer varchar(255),
                                 security_token varchar(100),
                                 status numeric(10) ,
                                 token_expiry_date timestamp(6),
                                 unsuccessful_tries numeric(19) ,
                                 user_name varchar(50) ,
                                 valid_ip_address varchar(15),
                                 valid_officer numeric(1),
                                 valid_rescue_cellphone numeric(1),
                                 valid_rescue_email numeric(1),
                                 user_causer numeric(1) ,
                                 description varchar(255),
                                 require_change_password numeric(1),
                                 unblock_time timestamp(6),
                                 USER_TYPE numeric(1)
);
/*alter table SCHEMA_USERS.USERS add primary key (USER_ID);*/

CREATE SEQUENCE SCHEMA_USERS.USER_SEQ
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE
NOCYCLE;

insert into SCHEMA_USERS.USERS (USER_ID, CREATION_DATE, CREATOR_USER_ID, LAST_UPDATE, UPDATER_USER_ID, LOGGED_IN, CURRENT_LOGIN, CURRENT_LOGIN_IP, DASHBOARD_ID, DEACTIVATION_REASON, DOC_CATEGORY_DASHBOARD_ID, DOC_CATEGORY_REPORT_ID, FIRST_NAME, GIVEN_NAME, IS_ACTIVE, LAST_LOGIN, LAST_LOGIN_IP, LAST_NAME, ORG_ID, OTP, PASSWORD, PREFERRED_LOCALE, PRINCIPLE_ID, RESCUE_CELL_PHONE, RESCUE_CELLPHONE_INVALID, RESCUE_EMAIL, RESCUE_EMAIL_INVALID, SECURITY_QUESTION, SECURITY_QUESTION_ANSWER, SECURITY_TOKEN, STATUS, TOKEN_EXPIRY_DATE, UNSUCCESSFUL_TRIES, USER_NAME, VALID_IP_ADDRESS, VALID_OFFICER, VALID_RESCUE_CELLPHONE, VALID_RESCUE_EMAIL, USER_CAUSER, DESCRIPTION, REQUIRE_CHANGE_PASSWORD, UNBLOCK_TIME)
values (1, CURRENT_DATE(), 1, null, null, null, null, '0:0:0:0:0:0:0:1', null, null, null, null, 'محمد', 'شهرستانکی', 1, null, 'x.x.x.x', 'محمد', null, null, '$2a$10$4cgI1cj47.hr5t15ZuM/.u1bFZal8ttdEUWhmWlq6O5v/a0fu9DwW', 'fa', 1, '09100000000', null, 'shahrestanaky_..@yahoo.com', null, null, null, null, 0, null, 0, 'shahrestanaki', null, null, null, null, 0, null, 0,null);
CREATE TABLE IF NOT EXISTS contact (
                         id int NOT NULL AUTO_INCREMENT,
                         cellphone_number varchar(15) NOT NULL,
                         cellphone_number_verified bit not NULL,
                         email_address varchar(100) NOT NULL,
                         email_address_verified bit not NULL,
                         `status` bit(1) NOT NULL DEFAULT b'0',
                         PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user  (
                      id int NOT NULL AUTO_INCREMENT,
                      contact_id int NOT NULL,
                      user_name varchar(200) NOT NULL,
                      last_modified_on datetime NOT NULL,
                      `status` bit(1) NOT NULL DEFAULT b'0',
                      PRIMARY KEY (id),
                      KEY fk_user_contact (contact_id),
                      CONSTRAINT fk_user_contact FOREIGN KEY (contact_id) REFERENCES contact (id)
) ;

CREATE TABLE IF NOT EXISTS user_device (
                             id int NOT NULL AUTO_INCREMENT,
                             user_id int NOT NULL,
                             last_modified_on datetime NOT NULL,
                             device_key varchar(200)  NULL,
                             device_name varchar(200) NOT NULL,
                             `status` bit(1) NOT NULL DEFAULT b'0',
                             PRIMARY KEY (id),
                             KEY fk_user_device_user (user_id),
                             CONSTRAINT fk_user_device_user FOREIGN KEY (user_id) REFERENCES user (id)
) ;

CREATE TABLE IF NOT EXISTS user_password (
                               id int NOT NULL AUTO_INCREMENT,
                               user_id int NOT NULL,
                               password varchar(500) NOT NULL,
                               last_modified_on datetime NOT NULL,
                               `status` bit(1) NOT NULL DEFAULT b'0',
                               PRIMARY KEY (id),
                               KEY fk_user_password_user (user_id),
                               CONSTRAINT fk_user_user_password FOREIGN KEY (user_id) REFERENCES user (id)
) ;

CREATE TABLE IF NOT EXISTS otp (
                     id int NOT NULL AUTO_INCREMENT,
                     user_id int NOT NULL,
                     otp int NOT NULL,
                     expiry_date datetime NOT NULL,
                     last_modified_on datetime NOT NULL,
                     `status` bit(1) NOT NULL DEFAULT b'0',
                     PRIMARY KEY (id),
                     UNIQUE KEY (user_id),
                     KEY fk_otp_user (user_id),
                     CONSTRAINT fk_otp_user FOREIGN KEY (user_id) REFERENCES user (id)
) ;
CREATE TABLE IF NOT EXISTS client (
                         id int NOT NULL AUTO_INCREMENT,
                         cellphone_number varchar(15) NOT NULL,
                         cellphone_number_verified bit not NULL,
                         email_address varchar(100) NOT NULL,
                         email_address_verified bit not NULL,
                         `status` bit(1) NOT NULL DEFAULT b'0',
                         PRIMARY KEY (id)
);
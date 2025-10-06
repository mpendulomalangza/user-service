CREATE TABLE IF NOT EXISTS user_activity  (
                                     id int NOT NULL AUTO_INCREMENT,
                                     user_id int NOT NULL,
    activity_type ENUM('login', 'password_reset', 'otp','failed_login'),
    last_modified_on datetime NOT NULL,
    `status` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (id),
    KEY fk_user_activity_user (user_id),
    CONSTRAINT fk_user_activity_user FOREIGN KEY (user_id) REFERENCES user (id)
    ) ;
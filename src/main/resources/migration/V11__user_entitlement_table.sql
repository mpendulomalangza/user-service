CREATE TABLE IF NOT EXISTS role  (
    id int NOT NULL AUTO_INCREMENT,
    system_name ENUM('farming-service', 'school-management', 'food-delivery'),
    role_name varchar(200) not null,
    last_modified_on datetime NOT NULL,
    `status` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (id),
    unique KEY unique_role (system_name,role_name)
    ) ;
CREATE TABLE IF NOT EXISTS entitlement  (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(100) not null,
    last_modified_on datetime NOT NULL,
    `status` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (id),
    unique KEY unique_entitlement (name)
    ) ;

CREATE TABLE IF NOT EXISTS role_entitlement  (
    id int NOT NULL AUTO_INCREMENT,
    role_id int NOT NULL,
    entitlement_id int NOT NULL,
    last_modified_on datetime NOT NULL,
    `status` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (id),
    KEY fk_role_entitlement_role (role_id),
    CONSTRAINT fk_role_entitlement_role FOREIGN KEY (role_id) REFERENCES role (id),
    KEY fk_role_entitlement_entitlement (entitlement_id),
    CONSTRAINT fk_role_entitlement_entitlement FOREIGN KEY (entitlement_id) REFERENCES entitlement (id)
    ) ;

CREATE TABLE IF NOT EXISTS user_role  (
                                                 id int NOT NULL AUTO_INCREMENT,
                                                 role_id int NOT NULL,
                                                 user_id int NOT NULL,
                                                 last_modified_on datetime NOT NULL,
                                                 `status` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (id),
    KEY fk_user_role_role (role_id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role (id),
    KEY fk_user_role_user (user_id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES user (id),
    unique KEY unique_user_role (user_id,role_id)
    ) ;
CREATE TABLE `user_avatar`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `user_id`     int          NOT NULL,
    `avatar`      blob         NOT NULL,
    `eff_from`    datetime     NOT NULL,
    `eff_to`      datetime     NOT NULL DEFAULT '9999-12-31 23:59:59',
    `created_by`  varchar(255) NOT NULL,
    `created_on`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modified_by` varchar(255)          DEFAULT NULL,
    `modified_on` datetime              DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `fk_user_avatar_1` (`user_id`),
    CONSTRAINT `fk_user_avatar_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);
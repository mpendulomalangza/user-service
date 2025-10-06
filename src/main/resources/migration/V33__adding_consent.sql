CREATE TABLE IF NOT EXISTS user_consent
(
    id       int                             NOT NULL AUTO_INCREMENT,
    user_id  int                             not null,
    consent_type  enum ('marketing','promotion') not NULL,
     consent_granted bit(1) not null,
    eff_from datetime                        not null,
    eff_to   datetime                        not null,
    status bit(1)                          NOT NULL DEFAULT b'0',
    PRIMARY KEY (id),
    constraint fk_user_consent_1 foreign key (user_id) references user (id)

    );
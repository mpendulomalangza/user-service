alter table contact
    add `eff_from` datetime NOT NULL default current_timestamp;
alter table contact
    add `eff_to` datetime NOT NULL DEFAULT '9999-12-31 23:59:59';
alter table contact
    add `created_by` varchar(255) NOT NULL default 'system';
alter table contact
    add `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP;
alter table contact
    add `modified_by` varchar(255) DEFAULT NULL;
alter table contact
    add `modified_on` datetime DEFAULT NULL;
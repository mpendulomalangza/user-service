alter table role change `system_name` `system_name` enum('farm-management','school-management','food-delivery') DEFAULT NULL;
insert
ignore into role (system_name,role_name,status,last_modified_on) values
       ('farm-management','admin',1,now()),
       ('school-management','admin',1,now()),
       ('food-delivery','admin',1,now());
insert
ignore into entitlement (name,last_modified_on,status) values
       ('edit-role',now(),1),
       ('find-role',now(),1),
       ('find-all-role',now(),1);

insert
ignore into role_entitlement(role_id,entitlement_id,last_modified_on,status) values
       ((select id from role where system_name='farm-management' and role_name='admin'),(select id from entitlement where name='edit-role'),now(),1),
       ((select id from role where system_name='farm-management' and role_name='admin'),(select id from entitlement where name='find-role'),now(),1),
       ((select id from role where system_name='farm-management' and role_name='admin'),(select id from entitlement where name='find-all-role'),now(),1),

       ((select id from role where system_name='school-management' and role_name='admin'),(select id from entitlement where name='edit-role'),now(),1),
       ((select id from role where system_name='school-management' and role_name='admin'),(select id from entitlement where name='find-role'),now(),1),
       ((select id from role where system_name='school-management' and role_name='admin'),(select id from entitlement where name='find-all-role'),now(),1);




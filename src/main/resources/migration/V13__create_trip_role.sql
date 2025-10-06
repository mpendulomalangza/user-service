alter table role change `system_name` `system_name` enum('farm-management','school-management','food-delivery','uride') DEFAULT NULL;
insert ignore into role (system_name,role_name,status,last_modified_on) values
    ('uride','admin',1,now()),
    ('uride','passenger',1,now()),
    ('uride','driver',1,now());

insert
ignore into entitlement (name,last_modified_on,status) values
    ('request-trip',now(),1),
    ('search-trip',now(),1),
    ('cancel-trip',now(),1),
    ('start-trip',now(),1),
    ('edit-personal',now(),1),
    ('end-trip',now(),1),
    ('rate-trip',now(),1),
    ('view-trip-list',now(),1),
    ('edit-user',now(),1),
    ('change-destination',now(),1),
    ('add-payment-method',now(),1);

insert
ignore into role_entitlement(role_id,entitlement_id,last_modified_on,status) values
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='request-trip'),now(),1),
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='cancel-trip'),now(),1),
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='search-trip'),now(),1),
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='edit-user'),now(),1),
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='add-payment-method'),now(),1),
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='change-destination'),now(),1),
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='view-trip-list'),now(),1),
        ((select id from role where system_name='uride' and role_name='passenger'),(select id from entitlement where name='rate-trip'),now(),1),

        ((select id from role where system_name='uride' and role_name='driver'),(select id from entitlement where name='end-trip'),now(),1),
        ((select id from role where system_name='uride' and role_name='driver'),(select id from entitlement where name='start-trip'),now(),1),
        ((select id from role where system_name='uride' and role_name='driver'),(select id from entitlement where name='rate-trip'),now(),1),

        ((select id from role where system_name='uride' and role_name='driver'),(select id from entitlement where name='end-trip'),now(),1),
        ((select id from role where system_name='uride' and role_name='driver'),(select id from entitlement where name='start-trip'),now(),1);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-trip-stop', sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-stop', sysdate(), true);
set @Id=(select id from entitlement where name='find-trip-stop');
set @roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

set @roleId = (select id from role where role_name='driver');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

set @Id=(select id from entitlement where name='edit-trip-stop');
set @roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-trip-note', sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-note', sysdate(), true);
set @Id=(select id from entitlement where name='find-trip-note');
set @roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

set @roleId = (select id from role where role_name='driver');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

set @Id=(select id from entitlement where name='edit-trip-note');
set @roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

set @Id=(select id from entitlement where name='find-trip-fee');
set @roleId = (select id from role where role_name='driver');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);
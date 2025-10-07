INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-user-avatar', sysdate(), true);
set @Id=(select id from entitlement where name='edit-user-avatar');
set @roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);
set @roleId = (select id from role where role_name='driver');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-user-avatar', sysdate(), true);
set @Id=(select id from entitlement where name='find-user-avatar');
set @roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);
set @roleId = (select id from role where role_name='driver');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-contact', sysdate(), true);
set @Id=LAST_INSERT_ID();
set @roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);
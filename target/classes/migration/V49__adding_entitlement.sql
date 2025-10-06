set @Id=(select id from entitlement where name='edit-contact');
set @roleId = (select id from role where role_name='driver');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

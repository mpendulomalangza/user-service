INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-route', sysdate(), true);

INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
select id,LAST_INSERT_ID(),sysdate(), true from role where system_name='uride' and role_name in ('passenger','driver');
set
@roleId= (select id from role where role_name='passenger' and system_name='uride');
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-all-card', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-card', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('delete-card', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

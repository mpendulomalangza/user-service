set
@roleId= (select id from role where role_name='passenger' and system_name='uride');
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-trip-type', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-type', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-all-trip-type', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-payment-type', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-all-payment-type', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-address', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-address', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

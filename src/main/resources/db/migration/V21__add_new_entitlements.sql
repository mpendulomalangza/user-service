set
@roleId= (select id from role where role_name='passenger' and system_name='uride');
INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-quotation', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-trip-quotation', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-all-trip-quotation', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
 VALUES ('find-trip-request', sysdate(), true);
 INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
 VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
 VALUES ('find-all-trip-request', sysdate(), true);
 INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
 VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
 VALUES ('edit-trip-request', sysdate(), true);
 INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
 VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
 VALUES ('find-trip-type-fee', sysdate(), true);
 INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
 VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-all-trip-type-fee', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);
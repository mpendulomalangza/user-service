set
@driverRoleId= (select id from role where role_name='driver' and system_name='uride');
set
@passengerRoleId= (select id from role where role_name='passenger' and system_name='uride');
INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-payment', sysdate(), true);

set
@entitlmentId =  LAST_INSERT_ID();

INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@passengerRoleId, @entitlmentId, sysdate(), true);

INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@driverRoleId, @entitlmentId, sysdate(), true);

INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-trip-payment', sysdate(), true);
set
@entitlmentId =  LAST_INSERT_ID();
INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@driverRoleId, @entitlmentId, sysdate(), true);

INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@passengerRoleId, @entitlmentId, sysdate(), true);

set
@entitlmentId =  (select id from user.entitlement where name ='edit-address');

INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@driverRoleId, @entitlmentId, sysdate(), true);

INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@passengerRoleId, @entitlmentId, sysdate(), true);

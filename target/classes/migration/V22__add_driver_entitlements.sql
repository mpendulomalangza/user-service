set
@roleId= (select id from role where role_name='driver' and system_name='uride');
set
@passengerRoleId= (select id from role where role_name='passenger' and system_name='uride');
delete
from role_entitlement
where role_id = @roleId;

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-entity-avatar', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-entity-avatar', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-entity-vehicle', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-entity-vehicle', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-vehicle-trip-type-status', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-vehicle-trip-type-status', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-vehicle-license', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-vehicle-license', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-license', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-license', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-status', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

set
@entitlementId =(select id from entitlement where name='edit-trip-status');

INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@passengerRoleId, @entitlementId, sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-trip', sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, LAST_INSERT_ID(), sysdate(), true);

set
@entitlementId =(select id from entitlement where name='find-trip');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@passengerRoleId, @entitlementId, sysdate(), true);

delete
from role_entitlement
where entitlement_id in (select id
                         from entitlement
                         where name in ('find-entity-avatar', 'edit-entity-avatar'));
delete
from entitlement
where name in ('find-entity-avatar', 'edit-entity-avatar');

set
@roleId= (select id from role where role_name='admin' and system_name='uride');

set
@driverId= (select id from role where role_name='driver' and system_name='uride');

set
@passengerId= (select id from role where role_name='passenger' and system_name='uride');

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-entity-avatar', sysdate(), true);
set
@Id=LAST_INSERT_ID();
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@driverId, @Id, sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@passengerId, @Id, sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-entity-avatar', sysdate(), true);
set
@Id=LAST_INSERT_ID();
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@driverId, @Id, sysdate(), true);
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@passengerId, @Id, sysdate(), true);

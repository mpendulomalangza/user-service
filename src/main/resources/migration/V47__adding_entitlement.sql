INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-all-trip-fee', sysdate(), true);
set
@Id=(select id from entitlement where name='find-all-trip-fee');
set
@roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-trip-fee', sysdate(), true);
set
@Id=(select id from entitlement where name='find-trip-fee');
set
@roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

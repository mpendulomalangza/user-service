INSERT INTO user.entitlement (name, last_modified_on, status)
VALUES ('accept-trip-quotation', sysdate(), true);
set
@Id=(select id from entitlement where name='accept-trip-quotation');
set
@roleId = (select id from role where role_name='passenger');
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @Id, sysdate(), true);

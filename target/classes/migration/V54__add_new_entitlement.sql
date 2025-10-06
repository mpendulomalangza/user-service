set
@roleId = (select id
               from role
               where role_name = 'passenger'
                 and system_name = 'uride');
INSERT ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-fee', sysdate(), true);
INSERT ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, (select id from entitlement where name = 'edit-trip-fee'), sysdate(), true);

INSERT ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-trip-payment', sysdate(), true);
INSERT ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, (select id from entitlement where name = 'edit-trip-fee'), sysdate(), true);

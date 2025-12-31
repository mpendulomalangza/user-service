set
@roleId= (select id from role where role_name='driver' and system_name='uride');
INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-contact-avatar', sysdate(), true);
set
@entitlmentId =   LAST_INSERT_ID();
INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @entitlmentId, sysdate(), true);

set
@roleId= (select id from role where role_name='passenger' and system_name='uride');
INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
VALUES (@roleId, @entitlmentId, sysdate(), true);
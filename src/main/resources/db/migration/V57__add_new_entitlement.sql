set
@roleId= (select id from role where role_name='driver' and system_name='uride');
INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('set-message', sysdate(), true);
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

set
@roleId= (select id from role where role_name='driver' and system_name='uride');
INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('edit-message', sysdate(), true);
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

set
@roleId= (select id from role where role_name='driver' and system_name='uride');
INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('read-message', sysdate(), true);
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

set
@roleId= (select id from role where role_name='driver' and system_name='uride');
INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-all-message', sysdate(), true);
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

set
@roleId= (select id from role where role_name='driver' and system_name='uride');
INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-latest-message', sysdate(), true);
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
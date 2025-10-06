INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('find-card', sysdate(), true);

INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
select id, LAST_INSERT_ID(), sysdate(), true
from role
where system_name = 'uride'
  and role_name in ('passenger');

INSERT
ignore INTO user.entitlement (name, last_modified_on, status)
VALUES ('delete-card', sysdate(), true);

INSERT
ignore INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
select id, LAST_INSERT_ID(), sysdate(), true
from role
where system_name = 'uride'
  and role_name in ('passenger');
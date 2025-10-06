set
@roleId= (select id from role where role_name='admin' and system_name='uride');
delete
from user.role_entitlement
where role_id = @roleId;
INSERT INTO user.role_entitlement (role_id, entitlement_id, last_modified_on, status)
select @roleId, id, sysdate(), true
from entitlement;
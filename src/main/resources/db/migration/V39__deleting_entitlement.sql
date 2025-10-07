delete
from role_entitlement
where entitlement_id in
      (select id from entitlement where entitlement.name in ('find-entity-avatar', 'edit-entity-avatar'));

delete from entitlement where entitlement.name in ('find-entity-avatar', 'edit-entity-avatar');
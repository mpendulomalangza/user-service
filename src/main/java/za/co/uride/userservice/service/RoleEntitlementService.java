package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.RoleEntitlementDto;

public interface RoleEntitlementService {

    void save(RoleEntitlementDto roleEntitlement);

    void delete(RoleEntitlementDto roleEntitlement);
}

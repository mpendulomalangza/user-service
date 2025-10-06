package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.BaseFilter;
import za.co.uride.userservice.dto.EntitlementDto;
import za.co.uride.userservice.dto.ListData;

public interface EntitlementService {
    EntitlementDto save(EntitlementDto entitlement) throws Exception;

    ListData<EntitlementDto> findAll(BaseFilter baseFilter);

    EntitlementDto findByNameAndStatus(String name, boolean status);
}

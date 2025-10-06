package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.RoleEntitlement;
import za.co.uride.userservice.domain.repository.RoleEntitlementRepository;
import za.co.uride.userservice.dto.RoleEntitlementDto;
import za.co.uride.userservice.service.RoleEntitlementService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleEntitlementServiceImpl implements RoleEntitlementService {
    private final RoleEntitlementRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public void save(RoleEntitlementDto roleEntitlementDto) {
        RoleEntitlement roleEntitlement = modelMapper.map(roleEntitlementDto, RoleEntitlement.class);
        Optional<RoleEntitlement> optional = repository.findByRoleAndEntitlementAndStatus(roleEntitlement.getRole(), roleEntitlement.getEntitlement(), true);
        if (optional.isEmpty()) {
            //Check for inactive role to be reinstated
            optional = repository.findByRoleAndEntitlementAndStatus(roleEntitlement.getRole(), roleEntitlement.getEntitlement(), false);
        }
        optional.ifPresent(entitlement -> roleEntitlement.setId(entitlement.getId()));
        repository.save(roleEntitlement);
    }

    @Override
    public void delete(RoleEntitlementDto roleEntitlementDto) {
        RoleEntitlement roleEntitlement = modelMapper.map(roleEntitlementDto, RoleEntitlement.class);
        Optional<RoleEntitlement> optional = repository.findByRoleAndEntitlementAndStatus(roleEntitlement.getRole(), roleEntitlement.getEntitlement(), true);
        if (optional.isPresent()) {
            optional.get().setStatus(false);
            optional.get().setLastModifiedOn(LocalDateTime.now());
            repository.save(optional.get());
        }
    }
}

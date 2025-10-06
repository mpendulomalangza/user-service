package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.Entitlement;
import za.co.uride.userservice.domain.repository.EntitlementRepository;
import za.co.uride.userservice.dto.BaseFilter;
import za.co.uride.userservice.dto.EntitlementDto;
import za.co.uride.userservice.dto.ListData;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.EntitlementService;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntitlementServiceImpl implements EntitlementService {
    final ModelMapper modelMapper;
    final EntitlementRepository repository;

    @Override
    public EntitlementDto save(final EntitlementDto entitlementDto) throws Exception {
        Optional<Entitlement> optionalEntitlement = repository.findByNameAndStatus(entitlementDto.getName(), true);
        if (optionalEntitlement.isPresent() && entitlementDto.getId() != optionalEntitlement.get().getId()) {
            throw new Exception(String.format("Entitlement [%s] already exists", entitlementDto.getName()));
        }
        Entitlement entitlement = modelMapper.map(entitlementDto, Entitlement.class);
        repository.save(entitlement);
        return modelMapper.map(entitlement, EntitlementDto.class);
    }

    @Override
    public ListData<EntitlementDto> findAll(BaseFilter baseFilter) {
        Pageable pageable = PageRequest.of(baseFilter.getPageNumber(), baseFilter.getPageSize());
        Page<Entitlement> entitlementPage = repository.findByNameLikeAndStatus(baseFilter.getFilter(), baseFilter.isStatus(), pageable);
        return new ListData<>(entitlementPage.getContent().stream().map(entitlement -> modelMapper.map(entitlement, EntitlementDto.class)).collect(Collectors.toList()),
                entitlementPage.getTotalPages());
    }

    @Override
    public EntitlementDto findByNameAndStatus(String name, boolean status) {
        Optional<Entitlement> optional = repository.findByNameAndStatus(name, status);
        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), EntitlementDto.class);
        }
        throw new FindException(String.format("Entitlement [%s] does not exist", name));
    }
}

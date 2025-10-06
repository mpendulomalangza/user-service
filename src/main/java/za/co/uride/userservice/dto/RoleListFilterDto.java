package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.uride.userservice.enums.ESystem;

@AllArgsConstructor
@Setter
@Getter
public class RoleListFilterDto extends ListFilterDto {
    private ESystem system;

    @Builder
    public RoleListFilterDto(final ESystem system, final int pageNumber, final int totalPerPage,
                             final String filter, final boolean status) {
        super(pageNumber, totalPerPage, filter, status);
        this.system = system;
    }
}

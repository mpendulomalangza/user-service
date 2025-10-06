package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ListFilterDto {
    private int pageNumber;
    private int totalPerPage;
    private String filter;
    private boolean status;
}

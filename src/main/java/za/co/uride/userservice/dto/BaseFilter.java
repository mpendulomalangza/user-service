package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BaseFilter {
    private int pageNumber;
    private int pageSize;
    private String filter;
    private boolean status;
}

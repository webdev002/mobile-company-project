package pdp.uz.mobilecompanyspringbootproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchesDto {
    @NotNull
    private String address;

    @NotNull
    private String managerDirector;

    @NotNull
    private List<EmployeeDto> employeeDtoList;

}

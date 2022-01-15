package pdp.uz.mobilecompanyspringbootproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.mobilecompanyspringbootproject.entity.Branches;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Integer branches;
}

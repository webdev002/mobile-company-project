package pdp.uz.mobilecompanyspringbootproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.mobilecompanyspringbootproject.entity.Customer;
import pdp.uz.mobilecompanyspringbootproject.entity.Definition;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimCardDto {

    @NotNull
    private String number;

    @NotNull
    private Double balance;

    @NotNull
    private List<DefinitionDto> definition;

    @NotNull
    private Integer customer;

    @NotNull
    private boolean active;
}

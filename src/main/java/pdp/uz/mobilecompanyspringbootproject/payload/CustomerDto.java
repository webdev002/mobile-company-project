package pdp.uz.mobilecompanyspringbootproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.mobilecompanyspringbootproject.entity.SimCard;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private List<SimCardDto> simCardDtoList;
}

package pdp.uz.mobilecompanyspringbootproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.mobilecompanyspringbootproject.entity.Paket;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefinitionDto {

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Integer paket;

    @NotNull
    private Integer simCard;
}

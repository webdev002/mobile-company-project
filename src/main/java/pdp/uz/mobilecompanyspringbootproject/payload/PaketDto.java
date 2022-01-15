package pdp.uz.mobilecompanyspringbootproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaketDto {

    @NotNull
    private String types;

    @NotNull
    private Double price;


    private String amalQilishMuddati;

    @NotNull
    private boolean active;

    @NotNull
    private List<DefinitionDto> definitionDtoList;
}

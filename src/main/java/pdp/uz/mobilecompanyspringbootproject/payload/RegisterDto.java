package pdp.uz.mobilecompanyspringbootproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotNull
    @Size(min = 3,max = 50)
    private String firstname;

    @NotNull
    @Size(min = 3,max = 50)
    private String lastname;

    @NotNull
    private String password;
}

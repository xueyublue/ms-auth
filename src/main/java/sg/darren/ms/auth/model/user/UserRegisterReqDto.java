package sg.darren.ms.auth.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReqDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private String fullName;
    @Email
    private String email;
    @Size(min = 1)
    private List<String> roles;

}

package sg.darren.ms.auth.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResetPasswordReqDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String password2;

}

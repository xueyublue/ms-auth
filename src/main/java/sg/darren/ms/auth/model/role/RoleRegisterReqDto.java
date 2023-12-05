package sg.darren.ms.auth.model.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRegisterReqDto {

    @NotBlank
    private String roleId;
    @NotBlank
    private String roleName;

}

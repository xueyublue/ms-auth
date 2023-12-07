package sg.darren.ms.auth.model.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.darren.ms.auth.model.enums.TokenValidUnitEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateReqDto {

    @NotBlank
    private String roleId;
    @NotBlank
    private String roleName;
    @Positive
    private int tokenValidValue;
    @NotNull
    private TokenValidUnitEnum tokenValidUnit;

}

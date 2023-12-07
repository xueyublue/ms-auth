package sg.darren.ms.auth.model.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.darren.ms.auth.model.enums.TokenValidUnitEnum;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResDto {

    private int id;
    private String roleId;
    private String roleName;
    private int tokenValidValue;
    private TokenValidUnitEnum tokenValidUnit;
    private Date createDate;
    private Date updateDate;

}

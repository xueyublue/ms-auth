package sg.darren.ms.auth.model.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date createDate;
    private Date updateDate;

}

package sg.darren.ms.auth.model.user;

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
public class UserResDto {

    private String username;
    private String fullName;
    private String email;
    private List<String> roles;
    private Date createDate;
    private Date updateDate;

}

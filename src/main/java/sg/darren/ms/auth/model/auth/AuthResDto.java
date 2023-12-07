package sg.darren.ms.auth.model.auth;

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
public class AuthResDto {

    private String token;
    private String username;
    private List<String> roles;
    private Date validationDate;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean accountCredentialsExpired;
    private boolean accountEnabled;

}

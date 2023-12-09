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
public class AuthValidateTokenResDto {

    private String token;
    private Date tokenIssuedDate;
    private Date tokenExpiryDate;
    private String username;
    private List<String> roles;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean accountDisabled;
    private Date validationDate;

}

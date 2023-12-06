package sg.darren.ms.auth.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class DataNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6796055067130923090L;

    public DataNotFoundException(String message) {
        super(message);
    }

    public static DataNotFoundException usernameNotFound(String username) {
        return new DataNotFoundException(String.format("Username %s not found!", username));
    }

    public static DataNotFoundException roleIdNotFound(String roleId) {
        return new DataNotFoundException(String.format("Role ID %s not found!", roleId));
    }

}

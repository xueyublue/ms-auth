package sg.darren.ms.auth.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class DataDuplicateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3740866481481658658L;

    public DataDuplicateException(String message) {
        super(message);
    }
}

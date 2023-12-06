package sg.darren.ms.auth.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class InvalidInputException extends RuntimeException {
	
	@Serial
    private static final long serialVersionUID = -8066847725642447290L;

	public InvalidInputException(String message) {
        super(message);
    }
}
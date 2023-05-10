package smartstore.exception;


import smartstore.util.Message;

public class InputTypeException extends RuntimeException {
    public InputTypeException() {
        super(Message.ERR_MSG_INVALID_INPUT_TYPE);
    }

    public InputTypeException(String message) {
        super(message);
    }
}

package smartstore.exception;

import smartstore.util.Message;

public class NullArgumentException extends RuntimeException {
    public NullArgumentException(){
        super(Message.ERR_MSG_INVALID_INPUT_NULL);
    }

    public NullArgumentException(String message){
        super(message);
    }
}

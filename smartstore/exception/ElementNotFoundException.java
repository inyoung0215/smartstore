package smartstore.exception;
import smartstore.util.Message;
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(){
        super(Message.ERR_MSG_NULL_ARR_ELEMENT);
    }

    public ElementNotFoundException(String message){
        super(message);
    }

}

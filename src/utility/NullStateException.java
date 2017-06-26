package utility;

public class NullStateException extends Exception{
    public NullStateException(){
        super();
    }

    public NullStateException(String message){
        super(message);
    }
}

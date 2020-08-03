package following.client.exception;

public class PlaceNotFoundException extends Exception {
    public PlaceNotFoundException(String msg) {
        super(msg);
    }
    public PlaceNotFoundException() {
        super();
    }
}
package service;

/**
 * @author tackedev
 */
public class CanceledRequestException extends Exception {

    public CanceledRequestException() {
    }

    public CanceledRequestException(String message) {
        super(message);
    }

}

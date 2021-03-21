package computerplus.com.pl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//404 - RESOURCE NOT FOUND
//400 - BAD REQUEST
//401 - UNAUTHORIZED
//415 - UNSUPPORTED TYPE - Representation not supported for the resource
//500 - SERVER ERROR

@ResponseStatus(HttpStatus.NOT_FOUND)
@RequestMapping("/error")
public class NotFoundException extends Exception{
        private int errorCode;
    private String errorMessage;

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }

    public NotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }


    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String message, int errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return this.errorCode + " : " + this.getErrorMessage();
    }    
}

package com.szf.exception;

public class NotloggedException extends RuntimeException {

    public NotloggedException(){
        super();
    }

    public NotloggedException(String message){
        super(message);
    }

}

package org.blz.product.exception;

public class DataIntegratyViolationException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public DataIntegratyViolationException(String message) {
        super(message);
    }

}

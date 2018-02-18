package com.cryptocloud.currencies;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ItemNotFoundException() {

    }

	public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
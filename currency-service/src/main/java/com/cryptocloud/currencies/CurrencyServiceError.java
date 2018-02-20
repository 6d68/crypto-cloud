package com.cryptocloud.currencies;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

class CurrencyServiceError {
    private HttpStatus status;
    
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

	private CurrencyServiceError() {
        timestamp = LocalDateTime.now();
    }

    CurrencyServiceError(HttpStatus status) {
        this();
        this.status = status;
    }

    CurrencyServiceError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getMessage() {
		return message;
	}
}
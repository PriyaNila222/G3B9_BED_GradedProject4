package com.greatlearning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IllegalAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleInavlidException(IllegalArgumentException exception) {
		return new Error(100, exception.getMessage());
	}

}

@AllArgsConstructor
@Getter
class Error {
	private int code;
	private String message;
}

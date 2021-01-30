package com.example.demo.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.domain.ProductException;

@RestControllerAdvice
public class WebRequestExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public RestResponseError handleException(ProductException e) {
		return RestResponseError.fromMessage(e.getMessage().toString());
	}
}

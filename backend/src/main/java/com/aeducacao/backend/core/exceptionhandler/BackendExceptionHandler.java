package com.aeducacao.backend.core.exceptionhandler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aeducacao.backend.service.exception.StudentNotFoundExeption;

@ControllerAdvice
public class BackendExceptionHandler extends ResponseEntityExceptionHandler {

		
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> DataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String msn = ExceptionUtils.getRootCauseMessage(ex);
		return handleExceptionInternal(ex, msn, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}	
	
	@ExceptionHandler({ StudentNotFoundExeption.class })
	public ResponseEntity<Object> StudentNotFoundExeption(StudentNotFoundExeption ex, WebRequest request) {
		String msn = ExceptionUtils.getRootCauseMessage(ex);
		return handleExceptionInternal(ex, msn, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}	
	
}

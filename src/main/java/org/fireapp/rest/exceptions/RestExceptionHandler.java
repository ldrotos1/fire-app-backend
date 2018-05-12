package org.fireapp.rest.exceptions;

import org.fireapp.model.exceptions.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler( Exception.class )
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException( Exception ex, WebRequest request ) {
		
		ex.printStackTrace();
		
	    ErrorDetails errorDetails = new ErrorDetails( "Unable to process your request at this time", request.getDescription( false ) );
	    return new ResponseEntity<>( errorDetails, HttpStatus.SERVICE_UNAVAILABLE );
	}
}

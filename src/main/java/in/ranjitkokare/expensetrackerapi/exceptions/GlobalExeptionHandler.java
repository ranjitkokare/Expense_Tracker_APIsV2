package in.ranjitkokare.expensetrackerapi.exceptions;

import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.ranjitkokare.expensetrackerapi.entity.ErrorObject;

@ControllerAdvice
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler{ //as soon as we run app spring will create obj. of this class
	
	//method to catch the exception for expense not found
	@ExceptionHandler(ResourceNotFoundException.class) //multilevel annotation, pass exception name
	public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex,WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		errorObject.setMessage(ex.getMessage());
		
		errorObject.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
	}
	
	//Bad request exception
	@ExceptionHandler(MethodArgumentTypeMismatchException.class) //multilevel annotation, pass exception name
	public ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException ex,WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		
		errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		errorObject.setMessage(ex.getMessage());
		
		errorObject.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
	}
	
	//Generalized exception
		@ExceptionHandler(Exception.class) //multilevel annotation, pass exception name
		public ResponseEntity<ErrorObject> handleGeneralException(Exception ex,WebRequest request) {
			ErrorObject errorObject = new ErrorObject();
			
			errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			errorObject.setMessage(ex.getMessage());
			
			errorObject.setTimestamp(new Date());
			
			return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
					HttpHeaders headers, HttpStatus status, WebRequest request) {
			
				Map<String, Object> body = new HashMap<String, Object>();
				
				body.put("timestamp", new Date());
				
				body.put("statusCode", HttpStatus.BAD_REQUEST.value());
				
				List<String> errors = ex.getBindingResult()
					.getFieldErrors()
					.stream()
					.map(x -> x.getDefaultMessage())
					.collect(Collectors.toList());
				
				body.put("messages", errors);
				
				return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
			}
}

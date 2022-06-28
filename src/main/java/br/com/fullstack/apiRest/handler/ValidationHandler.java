package br.com.fullstack.apiRest.handler;

import java.util.List;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ExceptionDTO>> handler(MethodArgumentNotValidException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getBindingResult().getFieldErrors().stream()
				.map(e -> new ExceptionDTO(e.getField(), e.getDefaultMessage())).toList());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ExceptionDTO> handler(MissingServletRequestParameterException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionDTO(exception.getParameterName(), exception.getMessage()));
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ExceptionDTO> handler(PropertyReferenceException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionDTO(exception.getPropertyName(), exception.getMessage()));
	}

}

class ExceptionDTO {

	private String field;
	private String errorMessage;

	public ExceptionDTO(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

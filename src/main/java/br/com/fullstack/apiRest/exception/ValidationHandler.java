package br.com.fullstack.apiRest.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<FormExceptionDTO>> handler(MethodArgumentNotValidException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(exception.getBindingResult().getFieldErrors().stream().map(FormExceptionDTO::new).toList());
	}
}

class FormExceptionDTO {

	private String field;
	private String errorMessage;

	public FormExceptionDTO(FieldError invalidField) {
		this.field = invalidField.getField();
		this.errorMessage = invalidField.getDefaultMessage();
	}

	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

package com.user.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.exception.DeleteUserNotFoundException;
import com.user.exception.DuplicateUserException;
import com.user.exception.DuplicateUserUpdateException;
import com.user.exception.UpdateUserIdNotFound;
import com.user.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvaludArguments(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleIdException(UserNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(DeleteUserNotFoundException.class)
	public Map<String, String> handleDeleteIdException(DeleteUserNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
	
	 
	 
	 
		@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
		@org.springframework.web.bind.annotation.ExceptionHandler(DuplicateUserException.class)
		public Map<String, String> handleEmailIdException(DuplicateUserException ex) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("errorMessage", ex.getMessage());
			return errorMap;
		}
		
		
		@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
		@org.springframework.web.bind.annotation.ExceptionHandler(UpdateUserIdNotFound.class)
		public Map<String, String> handleUpdateUserIdException(UpdateUserIdNotFound ex) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("errorMessage", ex.getMessage());
			return errorMap;
		}
		
		@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
		@org.springframework.web.bind.annotation.ExceptionHandler(DuplicateUserUpdateException.class)
		public Map<String, String> handleUpdateDuplicateUserException(DuplicateUserUpdateException ex) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("errorMessage", ex.getMessage());
			return errorMap;
		}
}

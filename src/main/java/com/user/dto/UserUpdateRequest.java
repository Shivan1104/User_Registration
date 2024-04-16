package com.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class UserUpdateRequest {
	@Id
	@GeneratedValue
	private int userId;
	@Size(min = 1,max = 23)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "only enter alphabets")
	private String firstName;
	@Size(min = 2,max = 23)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "only enter alphabets")
	private String middleName;
	@Size(min = 2,max = 23)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "only enter alphabets")
	private String lastName;
	
	@Email(message = "Invalid emailId")
	
	@Column(unique = true)
	private String emailId;
	
	@Pattern(regexp = "^(male|female)$")
	private String gender;
	
	@Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
	@Column(unique = true)
	private String mobileNumber;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace")
	@Column(unique = true)
	private String password;

}

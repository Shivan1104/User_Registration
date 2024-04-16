package com.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class UserRequest {
	@Id
	@GeneratedValue
	private int userId;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "only enter alphabets")
	@Size(min = 1,max = 23)
	private String firstName;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "only enter alphabets")
	@Size(min = 2,max = 23)
	private String middleName;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "only enter alphabets")
	@Size(min = 2,max = 23)
	private String lastName;
	@NotBlank
	@Email(message = "Invalid emailId")
	@Column(unique = true)
	private String emailId;
	@NotBlank
	@Pattern(regexp = "^(male|female)$")
	private String gender;
	@NotBlank
	@Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
	@Column(unique = true)
	private String mobileNumber;
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace")
	@Column(unique = true)
	private String password;

}

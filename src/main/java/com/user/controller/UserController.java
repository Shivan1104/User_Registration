package com.user.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserLoginRequest;
import com.user.dto.UserLoginResponse;
import com.user.dto.UserRegisteredResponse;
import com.user.dto.UserRequest;
import com.user.dto.UserUpdateRequest;
import com.user.entity.User;
import com.user.exception.DeleteUserNotFoundException;
import com.user.exception.DuplicateUserException;
import com.user.exception.DuplicateUserUpdateException;
import com.user.exception.UserNotFoundException;
import com.user.jwt.JWTTokenHelper;
import com.user.service.UserService;

import jakarta.validation.Valid;


@RestController

@RequestMapping("/user")

public class UserController {

	Logger  logger =LoggerFactory.getLogger(UserController.class);
	@Autowired

	private UserService userService;
	
	@Autowired
	JWTTokenHelper  jwtTokenHelper;
	@Autowired
	AuthenticationManager authenticationManager;
@Autowired
BCryptPasswordEncoder  passwordEncoder;

	@PostMapping("/register")

	public ResponseEntity<UserRegisteredResponse> registerUserDetails(@Valid @RequestBody UserRequest useRequest) throws DuplicateUserException  {
		useRequest.setPassword(passwordEncoder.encode(useRequest.getPassword()));
	    String savedResult= userService.saveUser(useRequest);
	    return ResponseEntity.ok(new UserRegisteredResponse(useRequest.getEmailId(),savedResult) );
	}
	
	// Login User
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponse>  loginUser(@RequestBody UserLoginRequest userLoginRequest )
	{
		//logic for authentication of user login time
		this.doAuthenticate(userLoginRequest.getEmailId(),userLoginRequest.getPassword());
		String token=this.jwtTokenHelper.generateToken(userLoginRequest.getEmailId());
		return ResponseEntity.ok(new UserLoginResponse(token,userLoginRequest.getEmailId())); 
		
	}
	
	
	private void doAuthenticate(String emailId, String password) {
       
		    logger.info("Authentication of User Credentials");
		    
		    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(emailId,password);
		    try { 
		    	  authenticationManager.authenticate(authentication); 
		    	 } catch (BadCredentialsException e) { 
		    	   throw new RuntimeException("Invalid UserName and Password"); 
		    	 } 
	
	}

	// To get User Details

	@GetMapping("{userId}")

	public ResponseEntity<User> viewUserDetails(@PathVariable("userId") int userId) throws UserNotFoundException {

		return ResponseEntity.ok(userService.viewUserDetails(userId));
	}

	// To get All User Details

	@GetMapping

	public ResponseEntity<List<User>> viewAllUserDetails() {

		return ResponseEntity.ok(userService.viewAllUserDetails());
	}

	// To delete User Details

	@DeleteMapping("{userId}")

	public ResponseEntity<User> deleteUserDetails(@PathVariable("userId") int userId)
			throws DeleteUserNotFoundException {

		User deletedUser = userService.deleteUserDetails(userId);
		if (deletedUser != null) {
			return ResponseEntity.ok(deletedUser);
		} else {
			throw new DeleteUserNotFoundException("user not found with Id:" + userId);
		}
	}

	

	    
	    
	    
	    @PutMapping("/{userId}")
	    
	           public User  updatingUser(@PathVariable("userId") int userId,@Valid @RequestBody UserUpdateRequest updateRequest) throws DuplicateUserUpdateException, UserNotFoundException  {
	    	User user=userService.updateUser(userId, updateRequest);
	    	return user;
	    	
	    }
	    
	  
	    
	    

}

/*
 * //To update User Details // via ID
 * 
 * @PutMapping("/{userId}") public void updateUserDetails(@PathVariable int
 * userId, @RequestBody UserUpdateDetailsRequest request) {
 * 
 * // userService.updateUserfName(userId, request) ; //
 * userService.updateUsermName(userId, request); //
 * userService.updateUserlName(userId, request); //
 * userService.updateUserGender(userId, request); //
 * userService.updateUserEmailId(userId, request); //
 * userService.updateUserMobileNumber(userId, request);
 * 
 * 
 }
 */
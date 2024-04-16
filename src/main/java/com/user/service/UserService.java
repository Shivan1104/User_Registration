package com.user.service;
// Package declaration indicating the location of the class

import java.util.List;
// Importing the List interface from java.util package

import org.springframework.beans.factory.annotation.Autowired;
// Importing annotation for autowiring
import org.springframework.stereotype.Service;
// Importing annotation to denote this class as a service

import com.user.dto.UserRequest;
import com.user.dto.UserUpdateRequest;
import com.user.entity.User;
import com.user.exception.DeleteUserNotFoundException;
import com.user.exception.DuplicateUserException;
import com.user.exception.DuplicateUserUpdateException;
import com.user.exception.UserNotFoundException;
// Importing the User entity class
import com.user.repository.UserRepository;
// Importing the UserRepository interface

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
// Annotation to denote that this class is a service component
@Log4j2
@Transactional
// Annotation to denote that transactions will be managed automatically

public class UserService {
	// Declaration of the UserService class

	@Autowired
// Annotation to automatically wire (inject) the UserRepository bean into this class

	UserRepository userRepository;
// Declaration of the UserRepository field

	// To create User Details
/*
	public User saveUser(UserRequest userRequest) throws DuplicateUserException {
		User user = User.build(0, userRequest.getFirstName(), userRequest.getMiddleName(), userRequest.getLastName(),
				userRequest.getEmailId(), userRequest.getGender(), userRequest.getMobileNumber(),
				userRequest.getPassword());

		if (userRepository.existsByEmailId(userRequest.getEmailId())) {
			throw new DuplicateUserException("EmailId already used: " + userRequest.getEmailId());
		}
		if (userRepository.existsByPassword(userRequest.getPassword())) {
			throw new DuplicateUserException("password already used: " + userRequest.getPassword());
		}

		if (userRepository.existsByMobileNumber(userRequest.getMobileNumber())) {
			throw new DuplicateUserException("mobileNumber already used: " + userRequest.getMobileNumber());
		}

		return userRepository.save(user);
	}*/
	public String saveUser(UserRequest userRequest) throws DuplicateUserException {
	         
		 User user=User.builder()
				 .firstName(userRequest.getFirstName())
				 .middleName(userRequest.getMiddleName())
				 .lastName(userRequest.getLastName())
				 .gender(userRequest.getGender())
				 .emailId(userRequest.getEmailId())
				 .mobileNumber(userRequest.getMobileNumber())
				 .password(userRequest.getPassword())
				 .build();
		       
		 if (userRepository.existsByEmailId(userRequest.getEmailId())) {
				throw new DuplicateUserException("EmailId already used: " + userRequest.getEmailId());
			}
			if (userRepository.existsByPassword(userRequest.getPassword())) {
				throw new DuplicateUserException("password already used: " + userRequest.getPassword());
			}

			if (userRepository.existsByMobileNumber(userRequest.getMobileNumber())) {
				throw new DuplicateUserException("mobileNumber already used: " + userRequest.getMobileNumber());
			}
		
			 userRepository.save(user);
		return "Registerded Successfully";
	}
	
	
	/*
	 * public User registerUserDetails(UserRequest request) { // Method signature to
	 * register user details, taking a request object as input
	 * 
	 * User user=new User(); // Instantiating a new User object
	 * 
	 * user.setUserId(request.getUserId()); // Setting user ID from the request
	 * 
	 * user.setFirstName(request.getFirstName()); // Setting first name from the
	 * request
	 * 
	 * user.setMiddleName(request.getMiddleName()); // Setting middle name from the
	 * request
	 * 
	 * user.setLastName(request.getLastName()); // Setting last name from the
	 * request
	 * 
	 * user.setGender(request.getGender()); // Setting gender from the request
	 * 
	 * user.setEmailId(request.getEmailId()); // Setting email ID from the request
	 * 
	 * return userRepository.save(user); // Saving the user object via the
	 * repository and returning it }
	 */
	// To get User Details

	public User viewUserDetails(int userId) throws UserNotFoundException {
		// Method signature to view user details, taking user ID as input

		User user = userRepository.findByUserId(userId);
		// Finding user by ID using the repository and returning it
		if (user != null) {
			return user;

		} else {
			throw new UserNotFoundException("user not found with id:" + userId);
		}
	}
	// To get All User Details

	public List<User> viewAllUserDetails() {
		// Method signature to view all user details

		return userRepository.findAll();
		// Finding all users using the repository and returning the list
	}

	// To delete User Details
	public User deleteUserDetails(int userId) throws DeleteUserNotFoundException {
		// Method signature to delete user details, taking user ID as input

		User user = userRepository.findByUserId(userId);
		// Deleting user by ID using the repository

		if (user != null) {
			userRepository.deleteByUserId(userId);
			return user;

		} else {
			throw new DeleteUserNotFoundException("user not found with id:" + userId);
		}
		// Returning a success message
	}

	@Transactional
	public User updateUser(int userId, UserUpdateRequest UpdateRequest)
			throws DuplicateUserUpdateException, UserNotFoundException {
		// Method signature to update user details, taking updated user object as input

		User existingUser = userRepository.findByUserId(userId);
		// Finding existing user by ID
		log.info("checked userID");

		if (existingUser == null) {
			throw new UserNotFoundException("User not found with ID: " + userId);
		}
		   if (UpdateRequest.getFirstName() != null && userRepository.existsByFirstName(UpdateRequest.getFirstName())) {
		        throw new DuplicateUserUpdateException("Previous firstName also same: " + UpdateRequest.getFirstName());
		    }
		   if (UpdateRequest.getMiddleName() != null && userRepository.existsByMiddleName(UpdateRequest.getMiddleName())) {
		        throw new DuplicateUserUpdateException("Previous middleName also same: " + UpdateRequest.getMiddleName());
		    }
		   if (UpdateRequest.getLastName() != null && userRepository.existsByLastName(UpdateRequest.getLastName())) {
		        throw new DuplicateUserUpdateException("Previous lastName also same: " + UpdateRequest.getLastName());
		    }
		   if (UpdateRequest.getEmailId() != null && userRepository.existsByEmailId(UpdateRequest.getEmailId())) {
		        throw new DuplicateUserUpdateException("Previous emailId also same: " + UpdateRequest.getEmailId());
		    }
		   if (UpdateRequest.getGender() != null && userRepository.existsByGender(UpdateRequest.getGender())) {
		        throw new DuplicateUserUpdateException("Previous gender also same: " + UpdateRequest.getGender());
		    }
		   if (UpdateRequest.getMobileNumber() != null && userRepository.existsByMobileNumber(UpdateRequest.getMobileNumber())) {
		        throw new DuplicateUserUpdateException("Previous mobileNumber also same: " + UpdateRequest.getMobileNumber());
		    }
		   if (UpdateRequest.getPassword() != null && userRepository.existsByPassword(UpdateRequest.getPassword())) {
		        throw new DuplicateUserUpdateException("Previous Password also same: " + UpdateRequest.getPassword());
		    }
		
		if (UpdateRequest.getFirstName() != null) {
			// Checking if new first name is provided
			log.info("firstname updating");
			existingUser.setFirstName(UpdateRequest.getFirstName());
			// Updating first name
		}
		if (UpdateRequest.getMiddleName() != null) {
			// Checking if new middle name is provided

			existingUser.setMiddleName(UpdateRequest.getMiddleName());
			// Updating middle name
		}

		if (UpdateRequest.getLastName() != null) {
			// Checking if new last name is provided

			existingUser.setLastName(UpdateRequest.getLastName());
			// Updating last name
		}
		if (UpdateRequest.getGender() != null) {
			// Checking if new gender is provided

			existingUser.setGender(UpdateRequest.getGender());
			// Updating gender
		}

		if (UpdateRequest.getEmailId() != null) {
			// Checking if new email ID is provided

			existingUser.setEmailId(UpdateRequest.getEmailId());
			// Updating email ID

		}
		if (UpdateRequest.getMobileNumber() != null) {

			existingUser.setMobileNumber(UpdateRequest.getMobileNumber());

		}

		if (UpdateRequest.getPassword() != null) {

			existingUser.setPassword(UpdateRequest.getPassword());

		}
		return userRepository.save(existingUser);

	}
}






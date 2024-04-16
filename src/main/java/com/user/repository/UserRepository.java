package com.user.repository;
// Package declaration indicating the location of the interface



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// Importing JpaRepository interface
import org.springframework.stereotype.Repository;
// Importing annotation to denote this interface as a repository

import com.user.entity.User;


@Repository
// Annotation to denote that this interface is a repository component

public interface UserRepository extends JpaRepository<User, Integer> {

	// Declaration of the UserRepository interface, extending JpaRepository

	User findByUserId(int userId);

	void deleteByUserId(int userId);

	boolean existsByEmailId(String emailId);

	boolean existsByPassword(String emailId);

	boolean existsByMobileNumber(String mobileNumber);

	boolean existsByFirstName(String firstName);

	boolean existsByMiddleName(String middleName);

	boolean existsByLastName(String lastName);

	boolean existsByGender(String firstName);
Optional<User>  findByEmailId(String emailId);
	

	

	

	



} // End of the UserRepository interface

/*
 * // UserUpdateDetailsRequest save(UserUpdateDetailsRequest request); //To
 * update FirstName via ID
 * 
 * @Modifying
 * 
 * @Query(value =
 * "update users_table set first_name=:firstname where user_id=:userId"
 * ,nativeQuery = true) public void updateUserFirstName(@Param("firstname")
 * String firstname,@Param("userId") int userId);
 * 
 * //To update MiddleName via ID
 * 
 * @Modifying
 * 
 * @Query(value =
 * "update users_table set middle_name=:middlename where user_id=:userId"
 * ,nativeQuery = true) public void updateUserMiddleName(@Param("middlename")
 * String middlename,@Param("userId") int userId);
 * 
 * //To update LastName via ID
 * 
 * @Modifying
 * 
 * @Query(value =
 * "update users_table set last_name=:lastname where user_id=:userId"
 * ,nativeQuery = true) public void updateLastName(@Param("lastname") String
 * lastname,@Param("userId") int userId); //To update Gender via ID
 * 
 * @Modifying
 * 
 * @Query(value =
 * "update users_table set gender=:gender where user_id=:userId",nativeQuery =
 * true) public void updateGender(@Param("gender") String
 * gender,@Param("userId") int userId); //To update EmailId via ID
 * 
 * @Modifying
 * 
 * @Query(value =
 * "update users_table set email_id=:emailid where user_id=:userId",nativeQuery
 * = true) public void updateEmailId(@Param("emailid") String
 * emailid,@Param("userId") int userId); //To update MobileNumber via ID
 * 
 * @Modifying
 * 
 * @Query(value =
 * "update users_table set mobile_number=:mobilenumber where user_id=:userId"
 * ,nativeQuery = true) public void updateMobileNumber(@Param("mobilenumber")
 * long mobilenumber,@Param("userId") int userId);
 * 
 * 
 * //To update MiddleName via GENDER //@Modifying //@Query(value =
 * "update users_table set middle_name=:middlename where first_name=:firstname"
 * ,nativeQuery = true) //public void updateUserMName(@Param("middlename")
 * String middlename,@Param("firstname") String firstname);
 */

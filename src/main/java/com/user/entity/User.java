package com.user.entity;
// Package declaration indicating the location of the class

import java.util.Collection;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// Importing annotation to denote this class as a JPA entity
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// Importing annotation to denote the primary key field

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// Importing annotation to specify the table name in the database

@Entity
// Annotation to denote that this class is a JPA entity

@Table(name = "users_table" )
@DynamicUpdate
// Annotation to specify the name of the table in the database
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class User implements UserDetails {
	// Declaration of the User class

	@Id
	// Annotation to denote the primary key field
	@GeneratedValue
	private int userId;
	// Declaration of the userId field as the primary key

	private String firstName;
	// Declaration of the firstName field

	private String middleName;
	// Declaration of the middleName field

	private String lastName;
	// Declaration of the lastName field
@Column(unique = true)
	private String emailId;
	// Declaration of the emailId field

	private String gender;
	// Declaration of the gender field
	@Column(unique = true)
	private String mobileNumber;
	@Column(unique = true)
	private String password;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emailId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

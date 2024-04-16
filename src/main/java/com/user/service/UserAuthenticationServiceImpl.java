package com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.jwt.JWTTokenHelper;
import com.user.repository.UserRepository;
@Service
public class UserAuthenticationServiceImpl implements UserDetailsService{

	Logger  logger =LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);
	@Autowired
	UserRepository userRepository;
  
 
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
      
		logger.info("Fetching UserDetails");
		
		User user=userRepository.findByEmailId(emailId).orElseThrow(() -> new UsernameNotFoundException("Invalid UserName"));
	
		return user ;
	}

}

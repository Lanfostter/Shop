package com.example.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;

@Service
public class LoginService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("not found");
		}
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		for (String role : userEntity.getRoles()) {
			list.add(new SimpleGrantedAuthority(role));
		}
		org.springframework.security.core.userdetails.User currentUser = new org.springframework.security.core.userdetails.User(
				username, userEntity.getPassword(), list);

		return currentUser;
	}

}

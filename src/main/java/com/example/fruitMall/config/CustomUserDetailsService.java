package com.example.fruitMall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fruitMall.dao.IMemberDao;
import com.example.fruitMall.dto.Member;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private IMemberDao mdao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = mdao.memberByUsername(username);
		
		if(member != null) {
			return new CustomUserDetails(member);
		}
		
		return null;
	}

}

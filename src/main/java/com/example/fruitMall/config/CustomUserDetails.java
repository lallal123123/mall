package com.example.fruitMall.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fruitMall.dto.Member;

public class CustomUserDetails implements UserDetails{

	private Member member;
	
	public CustomUserDetails(Member member){
		this.member =member;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();	//Collection은 리스트,맵,셋 같은걸 포함 함
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return member.getRole();
			}
			
		});
		
		return collection;		//맨 처음 나오는 null이 있으면 안됨 바꿔줘야함!!
	}

	@Override
	public String getPassword() {
		return member.getPw();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}

	public String getName() {			//필요할때 메서드 만들어서 쓰면됨
		return member.getName();
	}
	
	public String getRole() {
		return member.getRole();
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

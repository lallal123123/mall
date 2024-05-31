package com.example.fruitMall.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.fruitMall.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class MySecurity {
	@Autowired
	HttpServletRequest request;
	
	public boolean checkMember() {
		HttpSession session= request.getSession();
		Member member= (Member)session.getAttribute("loginMember");
		if(member.getRole() ==null) {
			return false;
		}
		else if(member.getRole().equals("ROLE_MEMBER")) {
			return true;
		}else {
			return false;
		}
		
	}

	public boolean checkAdmin() {
		HttpSession session= request.getSession();
		Member member= (Member)session.getAttribute("loginMember");
		if(member.getRole() ==null) {
			return false;
		}
		else if(member.getRole().equals("ROLE_ADMIN")) {
			return true;
		}else {
			return false;
		}
		
	}

}

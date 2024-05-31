package com.example.fruitMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fruitMall.dao.ICartDao;
import com.example.fruitMall.dao.IMemberDao;
import com.example.fruitMall.dao.IOrderDao;
import com.example.fruitMall.dao.IProductDao;
import com.example.fruitMall.dao.IReviewDao;
import com.example.fruitMall.dto.Member;

import jakarta.servlet.http.HttpSession;


@Controller
public class MyController {

	@Autowired
	IMemberDao mdao; 
	@Autowired
	IProductDao pdao;
	@Autowired
	IOrderDao odao;
	@Autowired
	ICartDao cdao;
	@Autowired
	IReviewDao rdao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/")
	public String root(Model model) {
		model.addAttribute("list",pdao.list());
		return "main";
	}
	
	@RequestMapping("/regForm")
	public String regForm() {
		return "regForm";
	}
	@RequestMapping("/reg")
	public String reg(Member member) {
		
		String newPw = bCryptPasswordEncoder.encode(member.getPw());
		member.setPw(newPw);
		mdao.reg(member);
		return "main";
	}
	@RequestMapping("/login")
	public String loginPage() {
		return "loginForm";
	}
	
	
	
	
}

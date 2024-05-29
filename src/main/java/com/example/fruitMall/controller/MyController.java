package com.example.fruitMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
	private ResourceLoader resourceLoader;
	
	@RequestMapping("/")
	public String root(Model model) {
		model.addAttribute("list",pdao.list());
		return "main";
	}
	
	@RequestMapping("/regForm")
	public String regForm() {
		return "regForm";
	}
	@RequestMapping("reg")
	public String reg(Member member) {
		mdao.reg(member);
		return "main";
	}
	@RequestMapping("loginPage")
	public String loginPage() {
		return "login";
	}
	@RequestMapping("login")
	public String login(Member member,HttpSession session,Model model ) {
		Member m = mdao.login(member);
		
		if(m == null) {
			model.addAttribute("msg", "아이디 혹은 패스워드가 일치하지 않습니다.");
			return "login";
		}
		session.setAttribute("loginMember",m);
		return "redirect:/";
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
}

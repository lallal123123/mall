package com.example.fruitMall.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fruitMall.dao.ICartDao;
import com.example.fruitMall.dao.IMemberDao;
import com.example.fruitMall.dao.IOrderDao;
import com.example.fruitMall.dao.IProductDao;
import com.example.fruitMall.dao.IReviewDao;
import com.example.fruitMall.dto.Product;
import com.example.fruitMall.dto.ProductFile;
import com.example.fruitMall.security.MySecurity;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
	MySecurity ms;

	// 관리자모드 들어가는 컨트롤러
	@RequestMapping("/mypage")
	public String adminMypage() {
		
		return "admin/mypage";
	}

	@RequestMapping("/productRegForm")
	public String productRegForm(Model model ) {
		
		model.addAttribute("fruitList", pdao.fruitList());

		return "admin/productRegForm";
	}

	@RequestMapping("/productReg")
	public String productReg(ProductFile productFile ) throws IOException {
		
		Product p = new Product();
		p.setName(productFile.getName());
		p.setPrice(productFile.getPrice());
		p.setStock(productFile.getStock());
		p.setContent(productFile.getContent());
		p.setCategory(productFile.getCategory());
		if (!(productFile.getImg_url().isEmpty())) {
			String fileName = productFile.getImg_url().getOriginalFilename();

			String filePath = "src/main/resources/static/uploads/" + fileName;
			productFile.getImg_url().transferTo(new FileSystemResource(filePath).getFile());
			p.setImg_url(fileName);
		}
		pdao.reg(p);

		return "redirect:/";
	}

	@RequestMapping("/list")
	public String list(Model model ) {
		
		List<Product> list = pdao.list();
		model.addAttribute("list", list);
		return "admin/list";
	}

	@RequestMapping("/detail")
	public String detail(@RequestParam("no") String no, Model model ) {
		
		Product dto = pdao.detail(no);

		model.addAttribute("dto", dto);

		return "admin/detail";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("no") String no ) {
		
		pdao.delete(no);
		return "redirect:/admin/list";
	}

	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("id") String id, Model model ) {
		
		Product p = pdao.detail(id);
		model.addAttribute("dto", p);
		System.out.println(p);
		model.addAttribute("fruitList", pdao.fruitList());

		return "admin/update";
	}

	@RequestMapping("/update")
	public String update(ProductFile productFile ) throws IOException {
		
		Product p = new Product();
		p.setName(productFile.getName());
		p.setPrice(productFile.getPrice());
		p.setStock(productFile.getStock());
		p.setContent(productFile.getContent());
		p.setCategory(productFile.getCategory());
		p.setProduct_id(productFile.getProduct_id());
		p.setStatus(productFile.getStatus());
		if (!(productFile.getImg_url().isEmpty())) {
			String fileName = productFile.getImg_url().getOriginalFilename();
			String filePath = "src/main/resources/static/uploads/" + fileName;
			productFile.getImg_url().transferTo(new FileSystemResource(filePath).getFile());
			p.setImg_url(fileName);
			pdao.update(p);
		} else {
			pdao.updateNoImg(p);
		}
		return "redirect:/admin/detail?no=" + p.getProduct_id();
	}

}

package com.example.fruitMall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fruitMall.dao.ICartDao;
import com.example.fruitMall.dao.IMemberDao;
import com.example.fruitMall.dao.IOrderDao;
import com.example.fruitMall.dao.IProductDao;
import com.example.fruitMall.dao.IReviewDao;
import com.example.fruitMall.dto.Cart;
import com.example.fruitMall.dto.Member;
import com.example.fruitMall.dto.Order;
import com.example.fruitMall.dto.Product;
import com.example.fruitMall.dto.Review;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/members")
public class MemberController {

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
	
	
	@RequestMapping("/detail")
	public String membersDetail(@RequestParam("id") String id , Model model) {
		model.addAttribute("dto",pdao.detail(id));
		List<Review> reviewList =rdao.listByProductId(id);
		System.out.println("리뷰리스트"+reviewList);
		model.addAttribute("reviewList",reviewList);
		List<Order> orderList=odao.listByProductId(id);
		System.out.println("주문리스트"+orderList);
		model.addAttribute("orderList",orderList);
		
		return "members/detail";
		
	}
	
	@RequestMapping("/order")
	public String order(Order order,HttpSession session) {
		Member m =(Member)session.getAttribute("loginMember");
		order.setStatus("준비중");
		order.setUsername(m.getUsername());
		odao.reg(order);
		pdao.updateStockDown(order.getProduct_id(), order.getQuantity());
		Product p =pdao.detail(""+order.getProduct_id());
		if(p.getStock() <= 5) {
			pdao.updateStatus(order.getProduct_id(), 0);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/cart")
	public String cart(Cart cart,HttpSession session) {
		Member m =(Member)session.getAttribute("loginMember");
		cart.setUsername(m.getUsername());
		System.out.println(cart.getProduct_id());
		cdao.reg(cart);
		
		return "redirect:/";
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session,Model model) {
		Member m =(Member)session.getAttribute("loginMember");
		List<Order> orderList = odao.listByUsername(m.getUsername());
		model.addAttribute("orderList", orderList);
		
		
		
		List<Cart> cartList =cdao.listByUsername(m.getUsername());
		model.addAttribute("cartList", cartList);
		
		return "members/mypage";
	}
	
	@RequestMapping("/cartDelete")
	public String cartDelete(@RequestParam("cart_id") String id) {
		cdao.delete(id);
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/orderDelete")
	public String orderDelete(@RequestParam("order_id") String id) {
		Order order =odao.getOrder(id);
		pdao.updateStockUp(order.getProduct_id(),order.getQuantity() );
		odao.delete(id);
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/ordersDelete")
	public String orderDelete(@RequestParam("orders_id") List<Integer> orders_id) {
		for(Integer order_id : orders_id) {
			String id=order_id + "";
			Order order =odao.getOrder(id);
			pdao.updateStockUp(order.getProduct_id(),order.getQuantity() );
			odao.delete(id);
		}
		
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/review")
	public String review(Review review) {
		rdao.reg(review);
		return "redirect:/members/detail?id="+review.getProduct_id();
	}
	
	@RequestMapping("/cartOrder")
	public String cartOrder(@RequestParam("cart_id") String cart_id) {
		Cart cart = cdao.getCart(cart_id);
		Order order = new Order();
		order.setUsername(cart.getUsername());
		order.setProduct_id(cart.getProduct_id());
		order.setQuantity(cart.getQuantity());
		odao.reg(order);
		cdao.delete(cart_id);
		
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/cartsOrder")
	public String cartsOrder(@RequestParam("carts_id") List<Integer> carts_id) {
		
		for(Integer cart_id : carts_id) {
			String scart_id = cart_id+ "";
			Cart cart = cdao.getCart(scart_id);
			Order order = new Order();
			order.setUsername(cart.getUsername());
			order.setProduct_id(cart.getProduct_id());
			order.setQuantity(cart.getQuantity());
			odao.reg(order);
			cdao.delete(scart_id);
		}
		
		return "redirect:/members/mypage";
	}
	
	
	
	
}

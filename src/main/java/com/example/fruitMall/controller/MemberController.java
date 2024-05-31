package com.example.fruitMall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fruitMall.config.CustomUserDetails;
import com.example.fruitMall.dao.ICartDao;
import com.example.fruitMall.dao.IMemberDao;
import com.example.fruitMall.dao.IOrderDao;
import com.example.fruitMall.dao.IProductDao;
import com.example.fruitMall.dao.IReviewDao;
import com.example.fruitMall.dto.Cart;
import com.example.fruitMall.dto.Order;
import com.example.fruitMall.dto.Product;
import com.example.fruitMall.dto.Review;
import com.example.fruitMall.security.MySecurity;


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
	MySecurity ms;
	
	
	
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
	public String order(Order order,@AuthenticationPrincipal CustomUserDetails cud) {
		
		
		order.setStatus("준비중");
		order.setUsername(cud.getUsername());
		odao.reg(order);
		pdao.updateStockDown(order.getProduct_id(), order.getQuantity());
		Product p =pdao.detail(""+order.getProduct_id());
		if(p.getStock() <= 5) {
			pdao.updateStatus(order.getProduct_id(), 0);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/cart")
	public String cart(Cart cart,@AuthenticationPrincipal CustomUserDetails cud) {
		
		
		cart.setUsername(cud.getUsername());
		System.out.println(cart.getProduct_id());
		cdao.reg(cart);
		
		return "redirect:/";
	}
	
	@RequestMapping("/mypage")
	public String mypage(@AuthenticationPrincipal CustomUserDetails cud,Model model) {
		
		
		List<Order> orderList = odao.listByUsername(cud.getUsername());
		model.addAttribute("orderList", orderList);
		
		
		
		List<Cart> cartList =cdao.listByUsername(cud.getUsername());
		model.addAttribute("cartList", cartList);
		
		return "members/mypage";
	}
	
	@RequestMapping("/cartDelete")
	public String cartDelete(@RequestParam("cart_id") String id ) {
		
		cdao.delete(id);
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/orderDelete")
	public String orderDelete(@RequestParam("order_id") String id ) {
		
		Order order =odao.getOrder(id);
		pdao.updateStockUp(order.getProduct_id(),order.getQuantity() );
		odao.delete(id);
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/ordersDelete")
	public String orderDelete(@RequestParam("orders_id") List<Integer> orders_id ) {
		
		for(Integer order_id : orders_id) {
			String id=order_id + "";
			Order order =odao.getOrder(id);
			pdao.updateStockUp(order.getProduct_id(),order.getQuantity() );
			odao.delete(id);
		}
		
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/review")
	public String review(Review review ) {
		
		rdao.reg(review);
		return "redirect:/members/detail?id="+review.getProduct_id();
	}
	//@RequestMapping("/reviewDelete")
	
	@RequestMapping("/cartOrder")
	public String cartOrder(@RequestParam("cart_id") String cart_id ) {
		
		Cart cart = cdao.getCart(cart_id);
		Order order = new Order();
		Product product = pdao.detail(cart.getProduct_id()+"");
		order.setUsername(cart.getUsername());
		order.setProduct_id(cart.getProduct_id());
		order.setQuantity(cart.getQuantity());
		odao.reg(order);
		cdao.delete(cart_id);
		pdao.updateStockDown(order.getProduct_id(), order.getQuantity());
		
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/cartsOrder")
	public String cartsOrder(@RequestParam("carts_id") List<Integer> carts_id ) {
		
		
		for(Integer cart_id : carts_id) {
			String scart_id = cart_id+ "";
			Cart cart = cdao.getCart(scart_id);
			Order order = new Order();
			Product product = pdao.detail(cart.getProduct_id()+"");
			order.setUsername(cart.getUsername());
			order.setProduct_id(cart.getProduct_id());
			order.setQuantity(cart.getQuantity());
			odao.reg(order);
			cdao.delete(scart_id);
			pdao.updateStockDown(order.getProduct_id(), order.getQuantity());
			if(product.getStock() <= 5) {
				pdao.updateStatus(order.getProduct_id(), 0);
			}
		}
		
		return "redirect:/members/mypage";
	}
	
	
	
	
}

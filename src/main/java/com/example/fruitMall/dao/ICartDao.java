package com.example.fruitMall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fruitMall.dto.Cart;

@Mapper
public interface ICartDao {

	public void reg(@Param("cart") Cart cart);
	public List<Cart> listByUsername(@Param("username") String username);
	public void delete(@Param("id") String id);
	public Cart getCart(@Param("cart_id") String cart_id);
}

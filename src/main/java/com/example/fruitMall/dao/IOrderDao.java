package com.example.fruitMall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fruitMall.dto.Order;

@Mapper
public interface IOrderDao {

	public void reg(@Param("order") Order order);
	public Order getOrder(@Param("order_id") String id);
	public List<Order> listByUsername(@Param("username") String username);
	public void delete(@Param("id") String id);
	public List<Order> listByProductId(@Param("product_id")String product_id);
}

package com.example.fruitMall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fruitMall.dto.Fruit;
import com.example.fruitMall.dto.Product;

@Mapper
public interface IProductDao {
	public void reg(@Param("dto") Product p);
	public List<Fruit> fruitList();
	public List<Product> list();
	public Product detail(@Param("no") String no);
	public void delete(@Param("no") String no);

	public void updateNoImg(@Param("dto") Product p);
	public void update(@Param("dto") Product p);
	public void updateStockDown(@Param("product_id") int product_id,@Param("quantity") int quantity);
	public void updateStockUp(@Param("product_id") int product_id,@Param("quantity") int quantity);
	public void updateStatus(@Param("product_id") int product_id,@Param("status") int status);
}

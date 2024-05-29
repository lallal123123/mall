package com.example.fruitMall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	public int product_id;
	public String name;
	public int price;
	public int stock;
	public String content;
	public String img_url;
	public int status;
	public String category;
	
}

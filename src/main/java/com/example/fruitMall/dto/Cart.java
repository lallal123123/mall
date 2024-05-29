package com.example.fruitMall.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	public int cart_id;
	public String username;
	public int product_id;
	public String product_name;//products테이블
	public int quantity;
	public LocalDate reg_date;
	public String img_url; //products테이블
	
}

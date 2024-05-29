package com.example.fruitMall.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	public int order_id;
	public String username;//
	public int product_id;//
	public String product_name;
	public int quantity;//
	public LocalDate order_date;
	public String status;
	public String img_url;
}

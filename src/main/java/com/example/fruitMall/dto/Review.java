package com.example.fruitMall.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	public int review_id;
	public String username;//
	public int product_id;//
	public String content;//
	public double rating;
	public LocalDate reg_date;
	public String buying;
}

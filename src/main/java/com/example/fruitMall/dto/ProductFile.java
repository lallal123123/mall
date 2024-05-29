package com.example.fruitMall.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFile {
	public int product_id;
	public String name;
	public int price;
	public int stock;
	public String content;
	public MultipartFile img_url;
	public int status;
	public String category;
	
}

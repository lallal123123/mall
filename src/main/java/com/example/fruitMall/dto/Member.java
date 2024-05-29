package com.example.fruitMall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	public String username;
	public String pw;
	public String email;
	public String name;
	public String address;
	public String role;

}

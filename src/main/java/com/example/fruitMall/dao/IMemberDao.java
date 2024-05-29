package com.example.fruitMall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fruitMall.dto.Member;

@Mapper
public interface IMemberDao {

	public void reg(@Param("dto") Member member);
	public Member login(@Param("dto") Member member);
}

package com.example.fruitMall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fruitMall.dto.Review;

@Mapper
public interface IReviewDao {

	public void reg(@Param("review") Review review);
	public List<Review> listByProductId(@Param("id") String id);
}

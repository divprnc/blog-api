package com.app.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}

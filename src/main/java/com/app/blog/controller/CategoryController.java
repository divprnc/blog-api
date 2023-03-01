package com.app.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payload.CategoryDto;
import com.app.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	// Build Add Category REST API
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto savedCategory = categoryService.addCategory(categoryDto);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}

	// Get Category by id
	@GetMapping("{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String categoryId) {
		return ResponseEntity.ok(categoryService.getCategory(categoryId));
	}

	// Get All Categories REST API
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	// Update Category
	@PutMapping("{categoryId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {
		return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
	}
	
	// Delete Category by id
	@DeleteMapping("{categoryId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategoryById(@PathVariable String categoryId) {
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("Category deleted successfully!.");
	}
}

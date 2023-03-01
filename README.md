Blog Management REST API with Spring Boot

Blog Management REST API with Spring Boot
=========================================

Features
--------

*   CRUD operations on Posts and Comments
*   Pagination and Sorting of Posts
*   OneToMany and ManyToOne relationships between entities
*   User Input validation using Hibernate Validator
*   Exception Handling for different types of exceptions
*   Authentication and Authorization using Spring Security and JWT Tokens
*   API versioning
*   Deployment on AWS Cloud
*   Dockerizing the application with MySQL

API Endpoints
-------------

### Post Management

*   GET /api/posts - Get all posts
*   GET /api/posts/{id} - Get post by ID
*   POST /api/posts - Create a new post (admin role required)
*   PUT /api/posts/{id} - Update an existing post by ID (admin role required)
*   DELETE /api/posts/{id} - Delete post by ID (admin role required)

**Query Parameters:** The following query parameters can be used for pagination and sorting:

*   pageNo - Page number (default: 0)
*   pageSize - Number of items per page (default: 10)
*   sortBy - Sorting field (default: id)
*   sortDir - Sorting direction (default: ASC)

**Example:** /api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=ASC - Get the first 5 posts sorted by title in ascending order.

*   GET /api/posts/{id}/category/{id} - Get all posts by category ID

### Comment Management

*   GET /api/posts/{postId}/comments - Get all comments for a post
*   GET /api/posts/{postId}/comments/{id} - Get a comment by ID for a post
*   POST /api/posts/{postId}/comments - Create a new comment for a post
*   PUT /api/posts/{postId}/comments/{id} - Update an existing comment by ID for a post
*   DELETE /api/posts/{postId}/comments/{id} - Delete a comment by ID for a post

### Authentication and Authorization

*   POST /api/auth/login - Login with username or email
*   POST /api/auth/signup - Register a new user

### Category Management

*   GET /api/category/{id} - Get a category by ID
*   GET /api/category - Get all categories
*   POST /api/category - Create a new category (admin role required)
*   PUT /api/category/{id} - Update Category (admin role required)
*   DELETE /api/category/{id} - Delete Category (admin role required)

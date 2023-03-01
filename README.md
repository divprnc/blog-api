<!DOCTYPE html>
<html>
<head>
	<title>Blog Management REST API with Spring Boot</title>
</head>
<body>
	<h1>Blog Management REST API with Spring Boot</h1>

	<h2>Features</h2>
	<ul>
		<li>CRUD operations on Posts and Comments</li>
		<li>Pagination and Sorting of Posts</li>
		<li>OneToMany and ManyToOne relationships between entities</li>
		<li>User Input validation using Hibernate Validator</li>
		<li>Exception Handling for different types of exceptions</li>
		<li>Authentication and Authorization using Spring Security and JWT Tokens</li>
		<li>API versioning</li>
		<li>Deployment on AWS Cloud</li>
		<li>Dockerizing the application with MySQL</li>
	</ul>

	<h2>API Endpoints</h2>
	<h3>Post Management</h3>
	<ul>
		<li>GET /api/posts - Get all posts</li>
		<li>GET /api/posts/{id} - Get post by ID</li>
		<li>POST /api/posts - Create a new post (admin role required)</li>
		<li>PUT /api/posts/{id} - Update an existing post by ID (admin role required)</li>
		<li>DELETE /api/posts/{id} - Delete post by ID (admin role required)</li>
	</ul>
	<p><strong>Query Parameters:</strong> The following query parameters can be used for pagination and sorting:</p>
	<ul>
		<li>pageNo - Page number (default: 0)</li>
		<li>pageSize - Number of items per page (default: 10)</li>
		<li>sortBy - Sorting field (default: id)</li>
		<li>sortDir - Sorting direction (default: ASC)</li>
	</ul>
	<p><strong>Example:</strong> /api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=ASC - Get the first 5 posts sorted by title in ascending order.</p>
	<ul>
		<li>GET /api/posts/{id}/category/{id} - Get all posts by category ID</li>
	</ul>

	<h3>Comment Management</h3>
	<ul>
		<li>GET /api/posts/{postId}/comments - Get all comments for a post</li>
		<li>GET /api/posts/{postId}/comments/{id} - Get a comment by ID for a post</li>
		<li>POST /api/posts/{postId}/comments - Create a new comment for a post</li>
		<li>PUT /api/posts/{postId}/comments/{id} - Update an existing comment by ID for a post</li>
		<li>DELETE /api/posts/{postId}/comments/{id} - Delete a comment by ID for a post</li>
	</ul>

	<h3>Authentication and Authorization</h3>
	<ul>
		<li>POST /api/auth/login - Login with username or email</li>
		<li>POST /api/auth/signup - Register a new user</li>
	</ul>

	<h3>Category Management</h3>
	<ul>
		<li>GET /api/category/{id} - Get a category by ID</li>
		<li>GET /api/category - Get all categories</li>
		<li>POST /api/category - Create a new category (admin role required)</li>
		<li>PUT /api/category/{id} - Update Category (admin role required) </li>
		<li>DELETE /api/category/{id} - Delete Category (admin role required) </li>
    </ul>

</body>
</html>

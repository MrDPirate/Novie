# Novie - Movie Management & Review Platform

## 📖 Project Description

Novie is a comprehensive full-stack movie management and review platform that allows users to discover, track, and review their favourite movies. Built with Spring Boot and featuring robust security with JWT authentication, Novie enables users to maintain personalized wishlists, track completed movies, and share detailed reviews with the community. The application provides a RESTful API architecture designed for scalability and ease of integration with various frontend clients.

## 🛠️ Tools & Technologies

### Backend
- **Java 17** - Primary programming language
- **Spring Boot 4.0.1** - Core framework for rapid application development
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - ORM and database interaction
- **Hibernate** - JPA implementation
- **JWT (JSON Web Tokens)** - Stateless authentication
- **PostgreSQL** - Primary relational database
- **Lombok** - Reducing boilerplate code
- **Maven** - Dependency management and build automation

### Additional Features
- **Email Service** - Account verification and password reset functionality
- **File Upload/Storage** - Profile image management
- **Role-Based Access Control (RBAC)** - User and Admin roles

## 🎯 General Approach

The development of Novie followed a layered architecture approach, separating concerns into distinct layers: controllers, services, repositories, and models. This design promotes maintainability, testability, and scalability. The application starts with a solid foundation of entity relationships, establishing clear connections between users, movies, categories, reviews, wishlists, and completed lists.

Security was a primary consideration from the beginning. The application implements JWT-based authentication with role-based authorization, ensuring that sensitive operations are protected and only accessible to authenticated users with appropriate permissions. Email verification and password reset functionality were integrated to enhance user account security and improve the overall user experience.

The REST API was designed following RESTful principles, providing intuitive endpoints for all CRUD operations. Relationships between entities are carefully managed through JPA annotations, preventing circular references with `@JsonIgnore` and maintaining referential integrity. The API supports advanced features such as mass operations, filtering by user or movie, and comprehensive error handling to provide clear feedback to clients.

## 📋 REST API Endpoints

### 🔐 Authentication Endpoints

| Request Type | URL | Functionality | Access |
|-------------|-----|---------------|--------|
| POST | `/auth/users/register` | Register new user account | Public |
| POST | `/auth/users/login` | User login and JWT token generation | Public |
| GET | `/auth/users/register/verify` | Verify email address via token | Public |
| GET | `/auth/users/resetPassword` | Request password reset email | Public |
| POST | `/auth/users/resetPassword` | Confirm password reset with token | Public |
| PUT | `/auth/users/change-password` | Change user password | Private (Authenticated Users) |

### 🎬 Movie Endpoints

| Request Type | URL | Functionality | Access |
|-------------|-----|---------------|--------|
| POST | `/api/movies` | Create a new movie | Private (Admin Only) |
| GET | `/api/movies` | Get all movies | Private (Authenticated Users) |
| GET | `/api/movies/{movieId}` | Get movie by ID | Private (Authenticated Users) |
| PUT | `/api/movies/{movieId}` | Update movie details | Private (Admin Only) |
| DELETE | `/api/movies/{movieId}` | Delete a movie | Private (Admin Only) |

### 📂 Category Endpoints

| Request Type | URL | Functionality | Access |
|-------------|-----|---------------|--------|
| POST | `/api/categories` | Create a new category | Private (Admin Only) |
| GET | `/api/categories` | Get all categories | Private (Authenticated Users) |
| GET | `/api/categories/{categoryId}` | Get category by ID | Private (Authenticated Users) |
| PUT | `/api/categories/{categoryId}` | Update category | Private (Admin Only) |
| DELETE | `/api/categories/{categoryId}` | Delete a category | Private (Admin Only) |

### ✅ Completed Movies Endpoints

| Request Type | URL | Functionality | Access |
|-------------|-----|---------------|--------|
| POST | `/api/users/completed` | Create completed list for user | Private (Authenticated Users) |
| GET | `/api/users/completed` | Get user's completed movies | Private (Authenticated Users) |
| PUT | `/api/users/completed` | Update completed list | Private (Authenticated Users) |

### ⭐ Wishlist Endpoints

| Request Type | URL | Functionality | Access |
|-------------|-----|---------------|--------|
| POST | `/api/users/wishlist` | Create wishlist for user | Private (Authenticated Users) |
| GET | `/api/users/wishlist` | Get user's wishlist | Private (Authenticated Users) |
| POST | `/api/users/wishlist/add/{movieId}` | Add movie to wishlist | Private (Authenticated Users) |
| DELETE | `/api/users/wishlist/remove/{movieId}` | Remove movie from wishlist | Private (Authenticated Users) |

### 💬 Review Endpoints

| Request Type | URL | Functionality | Access |
|-------------|-----|---------------|--------|
| POST | `/api/{movieId}/reviews` | Create review for a movie | Private (Authenticated Users) |
| GET | `/api/{movieId}/reviews` | Get all reviews for a movie | Private (Authenticated Users) |
| GET | `/api/users/reviews` | Get all reviews by current user | Private (Authenticated Users) |
| GET | `/api/users/reviews/{movieId}` | Get user's reviews for specific movie | Private (Authenticated Users) |
| GET | `/api/reviews/{reviewId}` | Get single review by ID | Private (Authenticated Users) |
| PUT | `/api/reviews/{reviewId}` | Update a review | Private (Review Owner Only) |
| DELETE | `/api/reviews/{reviewId}` | Delete a review | Private (Review Owner Only) |
| DELETE | `/api/reviews` | Mass delete reviews (array of IDs) | Private (Review Owner Only) |

### 👤 User Profile Endpoints

| Request Type | URL | Functionality | Access |
|-------------|-----|---------------|--------|
| PUT | `/api/profile/{profileId}/image` | Update profile image | Private (Authenticated Users) |
| GET | `/api/profile/{profileId}/image` | Get profile image | Private (Authenticated Users) |

## 🗂️ Entity-Relationship Diagram (ERD)
<img width="720" height="437" alt="image" src="https://github.com/user-attachments/assets/f3bd15e8-c13a-4bd2-8de2-a0ea74a713ad" />


## 📌 Project Management
**Trello Board:** https://trello.com/b/SWz7nQkK/novie

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- PostgreSQL database
- Maven 3.6+

### Installation

1. Clone the repository
```bash
git clone https://github.com/MrDPirate/Novie.git
cd Novie
```

2. Configure database connection in `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/novie
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build and run the application
```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📧 Contact

For questions or feedback, please reach out through the GitHub repository.

---

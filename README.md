# 🏨 StayEase API

StayEase is a Spring Boot RESTful API for a Hotel Booking System with JWT Authentication and Role-Based Authorization.

---

## 🚀 Features

- User Registration & Login (JWT Based)
- Role Based Access (ADMIN, HOTEL_MANAGER, CUSTOMER)
- Hotel Management
- Booking Management
- Secure Endpoints using Spring Security
- Global Exception Handling
- Logging Enabled
- MySQL Database Integration

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL
- Maven

---

## 🔐 Authentication

This project uses JWT Token-based authentication.

After login/register, include the token in request header:

```
Authorization: Bearer <your_token_here>
```

---

## 📌 API Endpoints

### 🔑 Auth
- `POST /auth/register`
- `POST /auth/login`

### 🏨 Hotels
- `GET /hotels` (Public)
- `POST /hotels` (ADMIN only)
- `PUT /hotels/{id}` (HOTEL_MANAGER only)
- `DELETE /hotels/{id}` (ADMIN only)

### 📅 Bookings
- `POST /bookings`
- `GET /bookings`

---

## 📝 Sample Register Request

```json
POST /auth/register

{
  "email": "admin@gmail.com",
  "password": "1234",
  "firstName": "Admin",
  "lastName": "User",
  "role": "ADMIN"
}
```

---

## ▶️ Run Locally

### 1️⃣ Clone the repository

```bash
git clone https://github.com/udhyamsingh2122-byte/stayease-api.git
cd stayease-api
```

### 2️⃣ Configure MySQL

Create a database:

```sql
CREATE DATABASE stayease;
```

Update `application.yml` with your MySQL username & password.

### 3️⃣ Run the application

```bash
./mvnw spring-boot:run
```

Server runs at:

```
http://localhost:8082
```

---

## 📂 Project Structure

- controller
- service
- repository
- entity
- security
- exception
- dto

---

## 👨‍💻 Author

Udhyam Singh  
Java Backend Developer

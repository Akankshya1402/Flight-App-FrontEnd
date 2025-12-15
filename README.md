# ✈️ Flight App – Full Stack Architecture & Professional README

## 1. Project Overview

This is a **production-style Flight Booking System** built using **Angular (Frontend)** and **Spring Boot Microservices (Backend)** with **MySQL**, **JWT Authentication**, **Eureka Discovery**, **API Gateway**, and **Docker-ready setup**.

The goal of this project is **not a demo**, but a **real-world, interview‑ready, deployable system**.

---

## 2. Tech Stack (No Noise)

### Frontend

* Angular 17+
* TypeScript
* RxJS
* Angular Router
* HTTP Interceptors (JWT)
* Reactive Forms

### Backend

* Java 17
* Spring Boot 3.x
* Spring Security + JWT
* Spring Data JPA
* Spring Cloud (Eureka, Gateway)

### Database

* MySQL

  * `authdb` → users, roles
  * `flightdb` → flights, inventory
  * `bookingdb` → bookings

### DevOps

* Docker & Docker Compose
* Maven
* Git

---

## 3. High-Level Architecture

```
┌──────────────┐
│   Angular    │
│  Frontend   │
│ (Port 4200) │
└──────┬───────┘
       │ HTTP + JWT
       ▼
┌───────────────────┐
│   API Gateway     │
│  (Port 8080)      │
└──────┬────────────┘
       │
 ┌─────▼───────────────┐
 │     Eureka Server   │
 │     (Port 8761)     │
 └─────┬───────────────┘
       │ Service Discovery
 ┌─────▼──────┐  ┌─────▼──────┐  ┌─────▼────────┐
 │ Auth Svc   │  │ Flight Svc │  │ Booking Svc  │
 │ 8082       │  │ 8081       │  │ 8083         │
 └─────┬──────┘  └─────┬──────┘  └─────┬────────┘
       │               │               │
   MySQL(authdb)   MySQL(flightdb)  MySQL(bookingdb)
```

---

## 4. Functional Flow (Actual Working Logic)

### 🔐 Authentication Flow

```
User → Angular Login Form
     → Auth API (/api/auth/login)
     → Validate credentials (MySQL)
     → Generate JWT
     → Return JWT
     → Angular stores token
     → Token attached to all future requests
```

### ✈️ Flight Search Flow

```
User → Search Flights Form
     → Angular FlightService
     → API Gateway (/api/flights/search)
     → Flight Service
     → MySQL (flightdb)
     → Results returned
     → UI renders flights
```

### 📦 Booking Flow

```
User → Select Flight
     → Booking API
     → Check availability
     → Save booking
     → Reduce inventory
     → Confirm booking
```

---

## 5. Frontend Structure (Angular)

```
flight-app-frontend/
│
├── src/app/
│   ├── auth/
│   │   ├── login/
│   │   ├── register/
│   │   └── auth.service.ts
│   │
│   ├── flight/
│   │   ├── flight-search/
│   │   └── flight.service.ts
│   │
│   ├── booking/
│   │   └── booking.service.ts
│   │
│   ├── core/
│   │   ├── guards/
│   │   └── interceptors/
│   │
│   └── app-routing.module.ts
│
└── angular.json
```

### Key Frontend Concepts Used

* **Auth Guard** → protects routes
* **JWT Interceptor** → attaches token
* **Lazy loading** → scalable routing

---

## 6. Backend Services Structure

### Auth Service

```
auth-service/
├── controller/
├── service/
├── repository/
├── security/
│   ├── JwtUtils
│   ├── AuthFilter
│   └── SecurityConfig
└── AuthServiceApplication.java
```

### Flight Service

```
flight-service/
├── controller/
├── service/
├── repository/
├── dto/
└── FlightServiceApplication.java
```

### Booking Service

```
booking-service/
├── controller/
├── service/
├── repository/
└── BookingServiceApplication.java
```

---

## 7. Ports & URLs (Important)

| Component       | Port |
| --------------- | ---- |
| Angular         | 4200 |
| API Gateway     | 8080 |
| Eureka          | 8761 |
| Auth Service    | 8082 |
| Flight Service  | 8081 |
| Booking Service | 8083 |

---

## 8. Database Configuration (MySQL)

```sql
CREATE DATABASE authdb;
CREATE DATABASE flightdb;
CREATE DATABASE bookingdb;
```

Each service has **its own schema** — no sharing.

---

## 9. How to Run (Correct Order – No Guessing)

### Step 1: Start MySQL

```bash
mysql -u root -p
```

### Step 2: Start Eureka Server

```bash
cd eureka-server
mvn spring-boot:run
```

### Step 3: Start API Gateway

```bash
cd api-gateway
mvn spring-boot:run
```

### Step 4: Start Backend Services

```bash
cd auth-service
mvn spring-boot:run

cd flight-service
mvn spring-boot:run

cd booking-service
mvn spring-boot:run
```

### Step 5: Start Angular Frontend

```bash
cd flight-app-frontend
npm install
ng serve
```

---

## 10. Docker (Optional but Professional)

```bash
docker-compose up --build
```

All services + MySQL + Eureka will come up together.

---

## 11. Security Notes (Interview-Level)

* Stateless JWT authentication
* No session storage
* Token validation at Gateway
* Role-based authorization ready

---

## 12. Why This Project Is Strong

* Real microservices separation
* Clean frontend architecture
* Production‑grade security
* Docker ready
* Easy to explain in viva/interview

---

## 13. Final Note (Straight Talk)

This is **not** a toy project. If this fails, it’s due to **mis‑order of startup or wrong configs**, not design. Follow the steps exactly — it will run.

If you want, next step can be:

* Deployment on AWS
* Kubernetes
* CI/CD pipeline
* Swagger + Postman collections

---

## 14. Frontend README (To Keep **Along With** Angular Default README)

> ⚠️ **Important**: The Angular CLI–generated README you shared is **mandatory and must remain unchanged**.
> The section below is to be **added after it** for academic / project / interview submission.

---

## FlightAppFrontend – Application-Level Documentation

### Purpose of Frontend

The Angular frontend acts as the **presentation and orchestration layer** of the Flight Booking System. It is responsible for:

* User authentication (Login & Registration)
* Flight search and listing
* Booking initiation
* Secure communication with backend microservices via API Gateway

The frontend does **not** directly call individual microservices. All requests go through the **API Gateway**, ensuring security and loose coupling.

---

## Frontend Architecture

```
User Browser
   │
   ▼
Angular Components (UI)
   │
   ▼
Angular Services (HTTP)
   │  + JWT Interceptor
   ▼
API Gateway (8080)
   │
   ▼
Microservices (Auth / Flight / Booking)
```

---

## Module & Folder Structure

```
src/app/
│
├── auth/
│   ├── login/
│   ├── register/
│   └── auth.service.ts
│
├── flight/
│   ├── flight-search/
│   └── flight.service.ts
│
├── booking/
│   └── booking.service.ts
│
├── core/
│   ├── guards/
│   │   └── auth.guard.ts
│   ├── interceptors/
│   │   └── jwt.interceptor.ts
│   └── models/
│
├── app-routing.module.ts
├── app.component.ts
└── app.module.ts
```

---

## Authentication Flow (Frontend Perspective)

```
Login Form
   ↓
AuthService.login()
   ↓
POST /api/auth/login (via Gateway)
   ↓
JWT Token received
   ↓
Stored in LocalStorage
   ↓
JWT Interceptor attaches token
   ↓
Protected routes accessible
```

---

## Flight Search Flow

```
Flight Search Component
   ↓
FlightService.searchFlights()
   ↓
POST /api/flights/search
   ↓
Flight Service → Database
   ↓
Results returned
   ↓
Rendered in UI table/cards
```

---

## Security Implementation (Frontend)

* **JWT Interceptor**

  * Automatically appends Authorization header
  * Prevents manual token handling in components

* **Auth Guard**

  * Restricts access to protected routes
  * Redirects unauthenticated users to login

* **Stateless UI**

  * No session handling on frontend
  * Backend remains fully stateless

---

## Environment Configuration

```ts
export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080'
};
```

Changing backend server requires **no code changes**, only environment update.

---

## How Frontend Is Started (Exact Commands)

```bash
npm install
ng serve
```

Access URL:

```
http://localhost:4200
```

---

## Why This Frontend Design Is Correct

* Clear separation of concerns (UI vs Logic)
* Scalable folder structure
* Secure by default (JWT + Guards)
* Backend-agnostic (Gateway-based)
* Matches real-world enterprise Angular standards

---




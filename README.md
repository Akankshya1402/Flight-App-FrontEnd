✈️ Flight App – Full Stack Architecture & Professional README

====================================================================

1. Project Overview

This is a production-style Flight Booking System built using Angular (Frontend) and Spring Boot Microservices (Backend) with MySQL, JWT Authentication, Eureka Discovery, API Gateway, and a Docker-ready setup.

The goal of this project is not a demo, but a real-world, interview-ready, deployable system.

====================================================================

2. Tech Stack (No Noise)

Frontend
- Angular 17+
- TypeScript
- RxJS
- Angular Router
- HTTP Interceptors (JWT)
- Reactive Forms

Backend
- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- Spring Data JPA
- Spring Cloud (Eureka, API Gateway)

Database
- MySQL
  - authdb → users and roles
  - flightdb → flights and inventory
  - bookingdb → bookings

DevOps
- Docker & Docker Compose
- Maven
- Git

====================================================================

3. High-Level Architecture

Angular Frontend (4200)
        |
        | HTTP + JWT
        v
API Gateway (8080)
        |
        v
Eureka Server (8761)
        |
---------------------------------------------------
|              |                |                |
Auth Service   Flight Service   Booking Service
(8082)         (8081)           (8083)
   |               |                 |
authdb         flightdb          bookingdb

====================================================================

4. Functional Flow (Actual Working Logic)

Authentication Flow
User → Angular Login Form
     → Auth API (/api/auth/login)
     → Credentials validated using authdb
     → JWT generated
     → JWT returned to frontend
     → Token stored in browser
     → Token attached to all future requests

Flight Search Flow
User → Flight Search Form
     → Angular FlightService
     → API Gateway (/api/flights/search)
     → Flight Service
     → flightdb queried
     → Results returned
     → UI renders flights

Booking Flow
User → Select Flight
     → Booking Service
     → Availability checked
     → Booking saved in bookingdb
     → Inventory updated in flightdb
     → Booking confirmation returned

====================================================================

5. Frontend Structure (Angular)

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
│   │   │   └── auth.guard.ts
│   │   ├── interceptors/
│   │   │   └── jwt.interceptor.ts
│   │   └── models/
│   │
│   ├── app-routing.module.ts
│   ├── app.component.ts
│   └── app.module.ts
│
└── angular.json

Frontend Concepts Used
- Auth Guards for route protection
- JWT Interceptor for automatic token handling
- Lazy loading for scalability
- Service-based API communication

====================================================================

6. Backend Services Structure

Auth Service
- controller
- service
- repository
- security
  - JwtUtils
  - AuthFilter
  - SecurityConfig

Flight Service
- controller
- service
- repository
- dto

Booking Service
- controller
- service
- repository

====================================================================

7. Ports & URLs

Angular Frontend : 4200  
API Gateway      : 8080  
Eureka Server    : 8761  
Auth Service     : 8082  
Flight Service   : 8081  
Booking Service  : 8083  

====================================================================

8. Database Configuration

CREATE DATABASE authdb;
CREATE DATABASE flightdb;
CREATE DATABASE bookingdb;

Each microservice owns its own database.
No database sharing between services.

====================================================================

9. How to Run the Project (Correct Order – Mandatory)

Step 1: Start MySQL
mysql -u root -p

Step 2: Start Eureka Server
cd eureka-server
mvn spring-boot:run

Step 3: Start API Gateway
cd api-gateway
mvn spring-boot:run

Step 4: Start Backend Services
cd auth-service
mvn spring-boot:run

cd flight-service
mvn spring-boot:run

cd booking-service
mvn spring-boot:run

Step 5: Start Angular Frontend
cd flight-app-frontend
npm install
ng serve

Access application at:
http://localhost:4200

====================================================================

10. Docker (Optional but Professional)

docker-compose up --build

This starts:
- MySQL
- Eureka
- API Gateway
- All backend services

====================================================================

11. Security Notes (Interview Level)

- Stateless JWT authentication
- No HTTP sessions
- Token validation at API Gateway
- Role-based authorization supported
- Secure route access using Angular guards

====================================================================

12. Why This Project Is Strong

- Proper microservices separation
- Clean Angular frontend architecture
- Production-grade security
- Docker-ready infrastructure
- Easy to explain in viva and interviews
- Realistic enterprise design

====================================================================

13. Viva / Interview Explanation (1–2 Lines)

“The Angular frontend communicates securely with Spring Boot microservices through an API Gateway using JWT authentication, with Eureka for service discovery and separate databases per service.”

====================================================================

14. Angular CLI – Mandatory Section

This project was generated using Angular CLI version 21.0.3.

Development server:
ng serve

Build:
ng build

Unit tests:
ng test

End-to-end tests:
ng e2e




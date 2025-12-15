✈️ Flight Booking Application – Full Stack (Frontend-Focused) Project
PROJECT OVERVIEW

This project is a full-stack Flight Booking Application with a strong focus on the Angular frontend.
The frontend is built using Angular and communicates with a Spring Boot microservices backend through an API Gateway.

The application allows users to:

Register and login

Search for flights

View flight details

Book flights (authenticated users)

Securely interact using JWT-based authentication

The frontend is implemented as a Single Page Application (SPA) using Angular routing, services, guards, interceptors, and Bootstrap for UI styling.

TECHNOLOGY STACK
Frontend

Angular (TypeScript)

Angular Router

Angular Services

Angular Guards

Angular HTTP Interceptors

Bootstrap (UI framework)

HTML5, CSS3

Backend

Spring Boot Microservices

Spring Security (JWT)

Spring Cloud Gateway

Netflix Eureka

MySQL Database

Maven

DevOps

Docker

Docker Compose

Git

OVERALL SYSTEM ARCHITECTURE (ADVANCED & WORKABLE)
┌──────────────────────────────────────────────────────────────┐
│                        USER BROWSER                          │
│                (Chrome / Edge / Firefox)                     │
└──────────────────────────────────────────────────────────────┘
                               │
                               │ HTTP Requests (JSON)
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                  ANGULAR FRONTEND (SPA)                      │
│                         Port: 4200                           │
│                                                              │
│  Components                                                   │
│   - Login / Register                                          │
│   - Flight Search                                             │
│   - Booking UI                                                │
│                                                              │
│  Services + HttpClient                                        │
│   - API calls                                                 │
│   - Business logic                                            │
│                                                              │
│  Security Layer                                               │
│   - JWT Interceptor (Authorization Header)                    │
│   - Auth Guard (Route Protection)                             │
└──────────────────────────────────────────────────────────────┘
                               │
                               │ Authorization: Bearer <JWT>
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                 API GATEWAY (Spring Cloud)                   │
│                         Port: 8080                           │
│   - Single entry point                                       │
│   - JWT validation                                           │
│   - Route mapping                                            │
│   - Load balancing                                          │
└──────────────────────────────────────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                    EUREKA SERVER                             │
│                        Port: 8761                            │
│   - Service registration                                     │
│   - Service discovery                                        │
│   - Dynamic service lookup                                   │
└──────────────────────────────────────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                   BACKEND MICROSERVICES                     │
│                                                              │
│  AUTH SERVICE (8082)   → Login, Register, JWT                │
│  FLIGHT SERVICE (8081) → Search flights, Flight data         │
│  BOOKING SERVICE (8083)→ Book flights, Save booking          │
└──────────────────────────────────────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────────────────────────────────────┐
│                        DATABASE LAYER                        │
│   authdb | flightdb | bookingdb (MySQL)                     │
└──────────────────────────────────────────────────────────────┘

FRONTEND INTERNAL FLOW (ANGULAR)
User Action (Click / Submit)
        ↓
Angular Component (UI + Template)
        ↓
Angular Service (Business Logic)
        ↓
HttpClient
        ↓
JWT Interceptor
(Adds Authorization Header)
        ↓
API Gateway (Backend)

AUTHENTICATION FLOW (JWT)
User → Login Form
        ↓
POST /api/auth/login
        ↓
Auth Service validates credentials
        ↓
JWT Token Generated
        ↓
Returned to Angular
        ↓
Stored in localStorage
        ↓
Used automatically by JWT Interceptor

ROUTE PROTECTION FLOW (AUTH GUARD)
User tries to access protected route
        ↓
Auth Guard executes
        ↓
Token valid            → Allow access
Token missing/expired  → Redirect to Login

FRONTEND MODULE STRUCTURE
src/
├── app/
│   ├── auth/
│   │   ├── login/
│   │   ├── register/
│   │   └── auth.service.ts
│   ├── flight/
│   │   ├── flight-search/
│   │   └── flight.service.ts
│   ├── booking/
│   │   └── booking.service.ts
│   ├── core/
│   │   ├── guards/
│   │   │   └── auth.guard.ts
│   │   ├── interceptors/
│   │   │   └── jwt.interceptor.ts
│   │   └── models/
│   ├── app-routing.module.ts
│   ├── app.component.ts
│   └── app.module.ts
├── environments/
│   ├── environment.ts
│   └── environment.prod.ts
├── main.ts
├── styles.css
└── index.html

BOOTSTRAP & UI DESIGN

Bootstrap is used for:

Responsive grid layout

Login and registration forms

Navigation bar

Flight search results using cards

Mobile-friendly UI

Bootstrap is included via:

angular.json styles array OR

CDN in index.html

ENVIRONMENT CONFIGURATION
export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080'
};

PORTS USED

Angular Frontend : 4200

API Gateway : 8080

Eureka Server : 8761

Auth Service : 8082

Flight Service : 8081

Booking Service : 8083

MySQL : 3306

HOW TO RUN FRONTEND
npm install
ng serve


URL:
http://localhost:4200

ANGULAR DEVELOPMENT COMMANDS
ng serve
ng build
ng test
ng e2e


Generate component:

ng generate component component-name


Generate service:

ng generate service service-name


Generate guard:

ng generate guard guard-name


Generate interceptor:

ng generate interceptor interceptor-name

BACKEND – HOW TO RUN
PREREQUISITES

Java 17+

Maven

MySQL

Docker (optional)

DATABASE SETUP
CREATE DATABASE authdb;
CREATE DATABASE flightdb;
CREATE DATABASE bookingdb;

BACKEND STARTUP ORDER

Eureka Server

API Gateway

Auth Service

Flight Service

Booking Service

START SERVICES
cd eureka-server
mvn spring-boot:run

cd api-gateway
mvn spring-boot:run

cd auth-service
mvn spring-boot:run

cd flight-service
mvn spring-boot:run

cd booking-service
mvn spring-boot:run


Verify:
http://localhost:8761

HOW FRONTEND & BACKEND WORK TOGETHER

Frontend runs on port 4200

User performs actions in UI

Angular services send HTTP requests

JWT interceptor attaches token

API Gateway routes request

Microservice processes logic

Database accessed

Response returned to frontend

UI updates dynamically

WHY THIS DESIGN IS CORRECT

Clean separation of concerns

Secure JWT-based authentication

Centralized routing via API Gateway

Database-per-service architecture

Scalable frontend design

Easy to explain in viva & interviews

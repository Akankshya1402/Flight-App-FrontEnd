✈️ Flight Booking Application – Full Stack (Frontend-Focused)

=====================================================================

PROJECT OVERVIEW

This project is a full-stack Flight Booking Application with a strong focus on the Angular frontend.
The frontend is built as a Single Page Application (SPA) and communicates with a Spring Boot microservices backend through an API Gateway.

Features

User registration and login

Flight search and listing

Flight booking (authenticated users)

JWT-based secure communication

Clean separation of frontend and backend responsibilities

=====================================================================

TECHNOLOGY STACK
Frontend

Angular (TypeScript)

Angular Router

Angular Services

Angular Guards

Angular HTTP Interceptors

Bootstrap

HTML5, CSS3

Backend

Spring Boot Microservices

Spring Security (JWT)

Spring Cloud Gateway

Netflix Eureka

MySQL

Maven

DevOps

Docker

Docker Compose

Git

=====================================================================

OVERALL SYSTEM ARCHITECTURE (WORKFLOW DIAGRAM)

This is a REAL rendered diagram (SVG image), not ASCII or Mermaid

<svg width="100%" height="460" viewBox="0 0 1200 460" xmlns="http://www.w3.org/2000/svg"> <rect x="20" y="20" width="1160" height="60" rx="8" fill="#111827"/> <text x="600" y="55" fill="white" font-size="18" text-anchor="middle">User Browser (Chrome / Edge / Firefox)</text> <rect x="20" y="100" width="1160" height="90" rx="8" fill="#2563eb"/> <text x="600" y="140" fill="white" font-size="18" text-anchor="middle"> Angular Frontend (SPA) — Port 4200 </text> <text x="600" y="165" fill="white" font-size="13" text-anchor="middle"> Components • Services • HttpClient • JWT Interceptor • Auth Guard </text> <rect x="20" y="220" width="1160" height="80" rx="8" fill="#7c3aed"/> <text x="600" y="265" fill="white" font-size="18" text-anchor="middle"> API Gateway (Spring Cloud) — Port 8080 </text> <rect x="20" y="330" width="360" height="90" rx="8" fill="#059669"/> <rect x="420" y="330" width="360" height="90" rx="8" fill="#059669"/> <rect x="820" y="330" width="360" height="90" rx="8" fill="#059669"/>

<text x="200" y="370" fill="white" font-size="14" text-anchor="middle">Auth Service (8082)</text>
<text x="200" y="390" fill="white" font-size="12" text-anchor="middle">Login • Register • JWT</text>

<text x="600" y="370" fill="white" font-size="14" text-anchor="middle">Flight Service (8081)</text>
<text x="600" y="390" fill="white" font-size="12" text-anchor="middle">Search Flights • Flight Data</text>

<text x="1000" y="370" fill="white" font-size="14" text-anchor="middle">Booking Service (8083)</text>
<text x="1000" y="390" fill="white" font-size="12" text-anchor="middle">Book Flights • Save Booking</text>
</svg>

Description:
Shows the complete end-to-end flow from user → Angular → API Gateway → microservices → databases.

=====================================================================

FRONTEND INTERNAL FLOW (ANGULAR)
<svg width="100%" height="240" viewBox="0 0 1200 240" xmlns="http://www.w3.org/2000/svg"> <rect x="40" y="60" width="220" height="60" rx="8" fill="#2563eb"/> <rect x="300" y="60" width="220" height="60" rx="8" fill="#2563eb"/> <rect x="560" y="60" width="220" height="60" rx="8" fill="#2563eb"/> <rect x="820" y="60" width="300" height="60" rx="8" fill="#2563eb"/>

<text x="150" y="95" fill="white" text-anchor="middle">Component</text>
<text x="410" y="95" fill="white" text-anchor="middle">Service</text>
<text x="670" y="95" fill="white" text-anchor="middle">HttpClient</text>
<text x="970" y="95" fill="white" text-anchor="middle">JWT Interceptor + Guard</text>
</svg>

Flow Explanation

User interacts with Angular Component

Component calls Angular Service

Service uses HttpClient

JWT Interceptor adds Authorization header

Request sent securely to backend

=====================================================================

AUTHENTICATION FLOW (JWT)

User submits Login form

POST /api/auth/login via API Gateway

Auth Service validates credentials

JWT token generated

Token returned to frontend

Token stored in localStorage

JWT Interceptor automatically attaches token to requests

=====================================================================

ROUTE PROTECTION FLOW (AUTH GUARD)

User navigates to protected route

Auth Guard executes

Token exists & valid → Allow access

Token missing/expired → Redirect to Login

=====================================================================

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
├── main.ts
├── styles.css
└── index.html


=====================================================================

BOOTSTRAP & UI DESIGN

Bootstrap is used for:

Responsive grid layout

Login & registration forms

Navbar

Flight search cards

Mobile-friendly UI

Bootstrap included via:

angular.json styles array or

CDN in index.html

=====================================================================

ENVIRONMENT CONFIGURATION

src/environments/environment.ts

export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080'
};


=====================================================================

PORTS USED

Angular Frontend : 4200
API Gateway : 8080
Eureka Server : 8761
Auth Service : 8082
Flight Service : 8081
Booking Service : 8083
MySQL : 3306

=====================================================================

HOW TO RUN FRONTEND (COMMANDS)
npm install
ng serve


Application URL:
http://localhost:4200

=====================================================================

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


=====================================================================

BACKEND – HOW TO RUN (ALL COMMANDS)
PREREQUISITES

Java 17+

Maven

MySQL

Docker (optional)

DATABASE SETUP
CREATE DATABASE authdb;
CREATE DATABASE flightdb;
CREATE DATABASE bookingdb;

BACKEND STARTUP ORDER (IMPORTANT)

Eureka Server

API Gateway

Auth Service

Flight Service

Booking Service

START EUREKA SERVER
cd Flight-App-BackEnd/eureka-server
mvn spring-boot:run


Verify: http://localhost:8761

START API GATEWAY
cd Flight-App-BackEnd/api-gateway
mvn spring-boot:run

START AUTH SERVICE
cd Flight-App-BackEnd/auth-service
mvn spring-boot:run

START FLIGHT SERVICE
cd Flight-App-BackEnd/flight-service
mvn spring-boot:run

START BOOKING SERVICE
cd Flight-App-BackEnd/booking-service
mvn spring-boot:run

OPTIONAL: DOCKER
docker-compose up --build
docker-compose down
docker-compose logs -f


=====================================================================

HOW FRONTEND & BACKEND WORK TOGETHER

Frontend runs on port 4200

User interacts with UI

Angular Services send HTTP requests

JWT Interceptor attaches token

API Gateway receives request

Gateway routes to correct service

Service accesses its database

Response returned to frontend

UI updates dynamically

=====================================================================

WHY THIS DESIGN IS CORRECT

Clean separation of concerns

Secure JWT-based authentication

Centralized routing via API Gateway

Database-per-service architecture

Scalable frontend structure


=====================================================================

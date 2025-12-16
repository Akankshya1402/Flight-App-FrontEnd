✈️ Flight Booking Application – Frontend Focused Full Stack Project

=====================================================================

PROJECT OVERVIEW

This project is a full-stack Flight Booking Application with a strong focus on the Angular frontend.
The frontend is built using Angular and communicates with a Spring Boot microservices backend through an API Gateway.

The application allows users to:
- Register and login
- Search for flights
- View flight details
- Perform authenticated actions using JWT-based security

The frontend is designed as a Single Page Application (SPA) using Angular routing, services, guards, interceptors, and Bootstrap for UI styling.

Flow Diagram :
<img width="640" height="711" alt="image" src="https://github.com/user-attachments/assets/5a6b2790-c659-4220-9681-08908c202172" />
<img width="615" height="726" alt="image" src="https://github.com/user-attachments/assets/a0cc0065-3e56-43dd-aa97-1dc62ac48a03" />



=====================================================================

TECHNOLOGY STACK

Frontend
- Angular (TypeScript)
- Angular Router
- Angular Services
- Angular Guards
- Angular HTTP Interceptors
- Bootstrap (UI styling)
- HTML5, CSS3

Backend (Connected System)
- Spring Boot Microservices
- Spring Security (JWT)
- Spring Cloud Gateway
- Eureka Service Discovery
- MySQL Database

=====================================================================

<img width="668" height="670" alt="image" src="https://github.com/user-attachments/assets/7a008371-9458-471a-af6b-1521f0eedf99" />
<img width="649" height="617" alt="image" src="https://github.com/user-attachments/assets/84e6add4-f295-402f-9afa-553e8a9a97c6" />



FRONTEND ARCHITECTURE – HOW IT WORKS

High-Level Flow (Diagrammatic)

User Browser
   |
   |  UI Interaction (Forms, Buttons)
   v
Angular Components
   |
   |  Business Logic
   v
Angular Services
   |
   |  HTTP Requests (+ JWT via Interceptor)
   v
API Gateway (Backend)
   |
   v
Backend Microservices + Database

=====================================================================
<img width="665" height="372" alt="image" src="https://github.com/user-attachments/assets/4f22c2e3-dacb-47fd-a131-819752c7c553" />


FRONTEND FLOW – STEP BY STEP

1. User opens the application in browser
2. Angular app loads (SPA)
3. User navigates using Angular Router
4. Forms are handled using Angular Forms
5. API calls are made using Angular Services
6. JWT token is attached automatically using Interceptor
7. Guards protect secure routes
8. Response is rendered dynamically without page reload

=====================================================================
<img width="456" height="711" alt="image" src="https://github.com/user-attachments/assets/b6c92ef5-1e3b-4093-b735-ac56deadedd6" />

<img width="371" height="624" alt="image" src="https://github.com/user-attachments/assets/17197b95-1ebc-47ae-bb1f-48b87e213678" />



=====================================================================

ROUTING & NAVIGATION

Angular Router is used to navigate between pages without reloading.

Example Routes:
- /login        → Login page
- /register     → Registration page
- /flights      → Flight search page (Protected)
- /booking      → Booking page (Protected)

Protected routes are secured using Auth Guard.

=====================================================================

AUTHENTICATION FLOW (FRONTEND)

Diagrammatic Flow:

Login Page
   ↓
AuthService.login()
   ↓
POST request to backend (/api/auth/login)
   ↓
JWT token received
   ↓
Token stored in browser (localStorage)
   ↓
JWT Interceptor attaches token to requests
   ↓
Secure pages accessible

=====================================================================

JWT INTERCEPTOR – WHY & HOW

Purpose:
- Automatically attach JWT token to every API request
- Avoid manual token handling in components

Flow:
HTTP Request
   ↓
JWT Interceptor
   ↓
Authorization: Bearer <token>
   ↓
Backend API

=====================================================================

AUTH GUARD – WHY & HOW

Purpose:
- Prevent unauthorized access to protected routes

Flow:
User tries to access protected page
   ↓
AuthGuard checks token
   ↓
If valid → Allow navigation
If invalid → Redirect to login

=====================================================================

BOOTSTRAP IN FRONTEND (UI DESIGN)

Bootstrap is used to:
- Create responsive layouts
- Style forms, buttons, cards, and tables
- Maintain consistent UI across pages

Used Features:
- Grid system (row, col)
- Buttons (btn, btn-primary, btn-success)
- Forms (form-control, form-group)
- Cards for flight details
- Responsive navbar

Bootstrap is added via:
- angular.json styles section OR
- CDN in index.html

=====================================================================

ENVIRONMENT CONFIGURATION

src/environments/environment.ts

export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080'
};

All frontend API calls use this base URL.

=====================================================================

PORTS USED

Angular Frontend        : 4200
API Gateway (Backend)   : 8080
Eureka Server           : 8761
Auth Service            : 8082
Flight Service          : 8081
Booking Service         : 8083
MySQL                   : 3306

=====================================================================

HOW FRONTEND TALKS TO BACKEND

Angular Component
   ↓
Angular Service
   ↓
HTTPClient
   ↓
API Gateway
   ↓
Microservice
   ↓
Database

Frontend NEVER directly calls individual microservices.

=====================================================================

HOW TO RUN THE FRONTEND (COMMANDS)

Step 1: Install Node modules
npm install

Step 2: Start Angular server
ng serve

OR
npm start

Application URL:
http://localhost:4200

=====================================================================

ANGULAR DEVELOPMENT COMMANDS

Start development server:
ng serve

Build project:
ng build

Run unit tests:
ng test

Run end-to-end tests:
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

HOW THE FULL SYSTEM WORKS (FRONTEND + BACKEND)

1. Frontend runs on port 4200
2. Backend API Gateway runs on port 8080
3. User interacts with Angular UI
4. Angular sends API requests to Gateway
5. Gateway routes request to correct service
6. Service accesses database
7. Response flows back to frontend
8. UI updates dynamically

=====================================================================

WHY THIS FRONTEND DESIGN IS CORRECT

- Clean separation of UI and logic
- Secure route handling
- Centralized API communication
- Reusable services
- Production-style Angular architecture
- Easy to scale and maintain

=====================================================================

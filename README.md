✈️ Flight Booking Application – Full Stack (Frontend-Focused) Project

=====================================================================

PROJECT OVERVIEW

This project is a full-stack Flight Booking Application with a strong focus on the Angular frontend.
The frontend is built using Angular and communicates with a Spring Boot microservices backend through an API Gateway.

The application allows users to:
- Register and login
- Search for flights
- View flight details
- Book flights (authenticated users)
- Securely interact using JWT-based authentication

The frontend is implemented as a Single Page Application (SPA) using Angular routing, services, guards,
interceptors, and Bootstrap for UI styling.

=====================================================================

TECHNOLOGY STACK

Frontend
- Angular (TypeScript)
- Angular Router
- Angular Services
- Angular Guards
- Angular HTTP Interceptors
- Bootstrap
- HTML5, CSS3

Backend
- Spring Boot Microservices
- Spring Security (JWT)
- Spring Cloud Gateway
- Netflix Eureka
- MySQL
- Maven

DevOps
- Docker
- Docker Compose
- Git

=====================================================================

OVERALL SYSTEM ARCHITECTURE (WORKFLOW)

<img
  alt="Overall System Architecture Workflow"
  src="data:image/png;base64,REPLACE_WITH_ARCHITECTURE_WORKFLOW_BASE64"
/>

Description:
This diagram represents the complete end-to-end workflow of the application,
from user interaction in the browser to backend microservices and databases.

=====================================================================

FRONTEND INTERNAL FLOW (ANGULAR)

<img
  alt="Frontend Internal Flow"
  src="data:image/png;base64,REPLACE_WITH_FRONTEND_FLOW_BASE64"
/>

Description:
Shows how Angular components, services, HttpClient, JWT interceptor, and guards
work together inside the frontend.

=====================================================================

AUTHENTICATION FLOW (JWT)

<img
  alt="Authentication Flow"
  src="data:image/png;base64,REPLACE_WITH_AUTH_FLOW_BASE64"
/>

Description:
Illustrates login, JWT generation, token storage, and secure API access.

=====================================================================

ROUTE PROTECTION FLOW (AUTH GUARD)

<img
  alt="Route Protection Flow"
  src="data:image/png;base64,REPLACE_WITH_ROUTE_GUARD_FLOW_BASE64"
/>

Description:
Explains how Angular route guards restrict access to protected routes.

=====================================================================

FRONTEND MODULE & FOLDER STRUCTURE

<img
  alt="Frontend Module Structure"
  src="data:image/png;base64,REPLACE_WITH_FRONTEND_STRUCTURE_BASE64"
/>

Description:
Represents the Angular folder and module organization used in this project.

=====================================================================

BOOTSTRAP & UI DESIGN FLOW

<img
  alt="Bootstrap UI Design"
  src="data:image/png;base64,REPLACE_WITH_UI_DESIGN_BASE64"
/>

Description:
Shows how Bootstrap is used for layout, forms, navigation, and responsiveness.

=====================================================================

ENVIRONMENT CONFIGURATION FLOW

<img
  alt="Environment Configuration"
  src="data:image/png;base64,REPLACE_WITH_ENV_CONFIG_BASE64"
/>

Description:
Explains how environment.ts controls backend API URLs and configuration.

=====================================================================

PORTS USED

Angular Frontend        : 4200  
API Gateway             : 8080  
Eureka Server           : 8761  
Auth Service            : 8082  
Flight Service          : 8081  
Booking Service         : 8083  
MySQL                   : 3306  

=====================================================================

HOW TO RUN FRONTEND

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

BACKEND – HOW TO RUN

PREREQUISITES
- Java 17+
- Maven
- MySQL
- Docker (optional)

DATABASE SETUP

mysql -u root -p

CREATE DATABASE authdb;
CREATE DATABASE flightdb;
CREATE DATABASE bookingdb;

---------------------------------------------------------------------

BACKEND STARTUP ORDER

1. Eureka Server
2. API Gateway
3. Auth Service
4. Flight Service
5. Booking Service

---------------------------------------------------------------------

START EUREKA SERVER

cd Flight-App-BackEnd/eureka-server  
mvn spring-boot:run  

Verify:
http://localhost:8761

---------------------------------------------------------------------

START API GATEWAY

cd Flight-App-BackEnd/api-gateway  
mvn spring-boot:run  

---------------------------------------------------------------------

START AUTH SERVICE

cd Flight-App-BackEnd/auth-service  
mvn spring-boot:run  

---------------------------------------------------------------------

START FLIGHT SERVICE

cd Flight-App-BackEnd/flight-service  
mvn spring-boot:run  

---------------------------------------------------------------------

START BOOKING SERVICE

cd Flight-App-BackEnd/booking-service  
mvn spring-boot:run  

---------------------------------------------------------------------

OPTIONAL: RUN USING DOCKER

docker-compose up --build  
docker-compose down  
docker-compose logs -f  

=====================================================================

HOW FRONTEND & BACKEND WORK TOGETHER

<img
  alt="Frontend Backend Interaction Flow"
  src="data:image/png;base64,REPLACE_WITH_FRONTEND_BACKEND_FLOW_BASE64"
/>

Description:
Shows the complete request-response lifecycle from Angular frontend to backend services.

=====================================================================

WHY THIS DESIGN IS CORRECT

- Clean separation of concerns
- Secure JWT-based authentication
- Centralized routing via API Gateway
- Database-per-service architecture
- Scalable frontend design
- Easy to explain in viva and interviews

=====================================================================

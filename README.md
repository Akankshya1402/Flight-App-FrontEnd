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

The frontend is implemented as a Single Page Application (SPA) using Angular routing, services, guards, interceptors, and Bootstrap for UI styling.

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

OVERALL SYSTEM ARCHITECTURE (WORKFLOW DIAGRAM)

The complete end-to-end architecture and request flow is represented in the diagram below.

📌 Architecture Workflow Diagram (PNG):
docs/architecture-workflow.png

=====================================================================

FRONTEND INTERNAL FLOW (ANGULAR)

The internal working of the Angular frontend, including components, services,
HttpClient, JWT interceptor, and guards, is shown in the diagram below.

📌 Frontend Internal Flow Diagram (PNG):
docs/frontend-internal-flow.png

=====================================================================

AUTHENTICATION FLOW (JWT)

The JWT-based authentication flow from login to secured API access is shown below.

📌 Authentication Flow Diagram (PNG):
docs/authentication-flow.png

=====================================================================

ROUTE PROTECTION FLOW (AUTH GUARD)

The route protection logic using Angular Auth Guards is illustrated below.

📌 Route Protection Flow Diagram (PNG):
docs/route-protection-flow.png

=====================================================================

FRONTEND MODULE STRUCTURE

The Angular project folder and module structure is shown in the diagram below.

📌 Frontend Folder Structure Diagram (PNG):
docs/frontend-module-structure.png

=====================================================================

BOOTSTRAP & UI DESIGN

The UI layout, responsiveness, and Bootstrap-based component structure is represented below.

📌 UI / Bootstrap Layout Diagram (PNG):
docs/ui-bootstrap-layout.png

=====================================================================

ENVIRONMENT CONFIGURATION

Environment configuration and API base URL handling is shown below.

📌 Environment Configuration Diagram (PNG):
docs/environment-configuration.png

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

The complete frontend–backend interaction flow is illustrated below.

📌 Frontend–Backend Interaction Diagram (PNG):
docs/frontend-backend-interaction.png

=====================================================================

WHY THIS DESIGN IS CORRECT

- Clean separation of concerns
- Secure JWT-based authentication
- Centralized routing via API Gateway
- Database-per-service architecture
- Scalable frontend design
- Easy to explain in viva and interviews

=====================================================================

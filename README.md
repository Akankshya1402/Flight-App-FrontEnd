✈️ Flight Booking Application – Full Stack (Frontend-Focused)
📌 Project Overview

This project is a full-stack Flight Booking Application with a strong focus on the Angular frontend.
The frontend is implemented as a Single Page Application (SPA) and communicates with a Spring Boot microservices backend via an API Gateway.

Functional Capabilities

User registration and login

Flight search and listing

Secure flight booking

JWT-based authentication

Route protection using Angular Guards

Responsive UI using Bootstrap

The frontend follows real-world Angular architecture using components, services, guards, interceptors, and environment-based configuration.

🧰 Technology Stack
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

🧭 Overall System Architecture (Workflow)

This diagram shows the complete end-to-end flow
from user browser → Angular frontend → API Gateway → backend microservices → databases.

<p align="center"> <img src="architecture/overall-system-architecture.png" alt="Overall System Architecture Workflow" width="900"> </p>

📁 Required folder structure

Flight-App-FrontEnd/
├── README.md
└── architecture/
    └── overall-system-architecture.png


Once committed, GitHub will render this image automatically.

🔁 Frontend Internal Flow (Angular)
How a request flows inside the frontend

User performs an action (click / submit)

Angular Component handles UI logic

Angular Service executes business logic

HttpClient sends HTTP request

JWT Interceptor attaches Authorization header

Request is forwarded to API Gateway

🔐 Authentication Flow (JWT)

User submits Login form

Frontend sends POST /api/auth/login

Auth Service validates credentials

JWT token is generated

Token is returned to frontend

Token is stored in localStorage

JWT Interceptor automatically adds token to secured requests

🛡 Route Protection Flow (Auth Guard)

User tries to access a protected route

Auth Guard executes

Token valid → Access allowed

Token missing / expired → Redirect to Login page

📂 Frontend Module Structure
src/
├── app/
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
├── environments/
│   ├── environment.ts
│   └── environment.prod.ts
│
├── main.ts
├── styles.css
└── index.html

🎨 Bootstrap & UI Design

Bootstrap is used for:

Responsive grid layout

Login and registration forms

Navigation bar

Flight search results using cards

Mobile-friendly UI

Bootstrap is included via:

angular.json styles array OR

CDN link in index.html

⚙️ Environment Configuration

src/environments/environment.ts

export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080'
};

🔌 Ports Used
Component	Port
Angular Frontend	4200
API Gateway	8080
Eureka Server	8761
Auth Service	8082
Flight Service	8081
Booking Service	8083
MySQL	3306
▶️ How to Run Frontend
npm install
ng serve


Application URL:

http://localhost:4200

🛠 Angular Development Commands
ng serve
ng build
ng test
ng e2e


Generate Angular artifacts:

ng generate component component-name
ng generate service service-name
ng generate guard guard-name
ng generate interceptor interceptor-name

🧩 Backend – How to Run
Prerequisites

Java 17+

Maven

MySQL

Docker (optional)

Database Setup
CREATE DATABASE authdb;
CREATE DATABASE flightdb;
CREATE DATABASE bookingdb;

Backend Startup Order (IMPORTANT)

Eureka Server

API Gateway

Auth Service

Flight Service

Booking Service

Start Eureka Server
cd Flight-App-BackEnd/eureka-server
mvn spring-boot:run


Verify:

http://localhost:8761

Start API Gateway
cd Flight-App-BackEnd/api-gateway
mvn spring-boot:run


Runs on:

http://localhost:8080

Start Auth Service
cd Flight-App-BackEnd/auth-service
mvn spring-boot:run


Port: 8082
Database: authdb

Start Flight Service
cd Flight-App-BackEnd/flight-service
mvn spring-boot:run


Port: 8081
Database: flightdb

Start Booking Service
cd Flight-App-BackEnd/booking-service
mvn spring-boot:run


Port: 8083
Database: bookingdb

Verify Backend Services

Open:

http://localhost:8761


Ensure all services are registered.

🐳 Optional: Run Backend Using Docker
docker-compose up --build
docker-compose down
docker-compose logs -f

🔗 How Frontend & Backend Work Together

Frontend runs on port 4200

User interacts with UI

Angular services send HTTP requests

JWT interceptor attaches token

API Gateway receives request

Gateway routes to appropriate microservice

Microservice accesses database

Response is returned to frontend

UI updates dynamically

✅ Why This Design Is Correct

Clean separation of concerns

Stateless JWT-based authentication

Centralized routing via API Gateway

Database-per-service architecture

Scalable Angular frontend

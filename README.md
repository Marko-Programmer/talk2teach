# 🎓 Talk2Teach – Teacher-Parent Consultation Platform

**Talk2Teach** is a web-based coordination system designed to streamline communication between teachers and parents. 
The platform automates the process of booking consultation slots, managing student information, and organizing school meetings.

 <br>

## 🚀 Core Functionality

### 🔐 Multi-Role Access Control
* **Admin:** Full user management. Can generate credentials for new users, assign roles, and moderate all platform activities.
* **Teachers:** Manage their availability by creating time slots, specifying classrooms, and viewing their consultation schedule.
* **Parents:** Search for teachers by name or subject, book available time slots for consultations.

### 📅 Consultation Management
* **Dynamic Time Slots:** Teachers set their own time frames and locations.
* **Instant Booking:** Parents select available slots and provide a message/reason for the meeting. 

### 👤 User Dashboard (Private Cabinet)
* Personal data management for all users.
* Meeting history and upcoming schedule overview. 

 <br>

## 💻 Tech Stack

### Backend
* **Language:** Java 21
* **Framework:** Spring Boot (MVC, Data JPA, Security)
* **Security:** JWT (JSON Web Tokens)
* **Documentation:** Swagger / OpenAPI 3 
* **Utilities:** Lombok, Jakarta Validation

### Frontend
* **Framework:** Angular
* **Styling:** HTML5, CSS3, SCSS, Bootstrap
* **State Management:** RxJS

### Infrastructure & Database
* **Database:** PostgreSQL 
 
 <br>

## 🏗 System Architecture

The project follows a clean **Layered Architecture**:

1. **Controller Layer:** Handles HTTP requests and REST API endpoints.
2. **Service Layer:** Contains core business logic and coordination.
3. **Repository Layer:** Manages database interaction via Spring Data JPA.
4. **Model Layer:** Persistent JPA Entities.
5. **DTO (Data Transfer Object) Layer:** Decouples API contract from internal data models.
6. **Mapper Layer:** Transformation logic between Entities and DTOs.
 
 

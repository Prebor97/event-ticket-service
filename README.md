# 🎟️ Event/Ticket Service

The **Event/Ticket Service** is the **core application** of the Event-Driven Microservices System.  
It manages the **creation of events, retrieval of event details, and booking of event tickets**, while enforcing role-based access.  
This service integrates seamlessly with other microservices (User, Payment, Notification, and Reconciliation) via **Apache Kafka**.

---

## 📌 Features

- **Event Management**
    - Create, update, and delete events (Admin access).
    - View all events or a specific event (Public access).

- **Ticket Management**
    - Book tickets for an event (Authenticated users).
    - View booking history.
    - Track available tickets.

- **Role-Based Access Control**
    - Roles are managed in the **User Service**.
    - Enforces access permissions for event operations.

- **Event Publishing**
    - Publishes domain events (ticket booked, event created, booking failed, etc.) to Kafka.
    - Consumes user-related events from the User Service for authorization and profile linkage.

---

## 🏗️ System Architecture

This service is part of a **microservices ecosystem**:

- **User Service** → Manages authentication, authorization, and profiles.
- **Event/Ticket Service** (this repo) → Core event + ticket management.
- **Payment Service** → Processes ticket payments.
- **Notification Service** → Sends email/SMS notifications via Java Mail Sender.
- **Reconciliation/Scheduler Service** → Periodically checks payment status and updates ticket bookings.

**Communication**:  
All inter-service communication is **asynchronous** via **Apache Kafka**.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security (JWT, Role-based access)**
- **PostgreSQL** (event/ticket data persistence)
- **Apache Kafka** (event streaming between services)
- **JUnit / Mockito** (testing)

---

## ⚙️ Running Locally

### Prerequisites
- Java 17+
- Maven 3+
- PostgreSQL
- Kafka & Zookeeper (running locally or via Docker)

### Steps
```bash
# Clone the repository
git clone https://github.com/Prebor97/event-ticket-service.git
cd event-ticket-service

# Configure database + Kafka in application.yml

# Run with Maven
mvn spring-boot:run
```
## 🔒 Security

- Uses **JWT authentication** provided by the **User Service**.  
- Only authenticated users can book tickets.  
- Only admins can create/update/delete events.  

---

## 🧪 Testing

Run unit and integration tests:
```bash
mvn test
```
## 👥 Contributors

- **Backend Team** — Spring Boot, Kafka, PostgreSQL
- **DevOps Team** — Docker, AWS  


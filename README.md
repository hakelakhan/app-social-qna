# 📚 Social Q\&A Backend

A **backend API** for a modern, community-driven **Q\&A platform** where users can ask questions, post answers, and engage around topics they care about. Built with **Spring Boot, Java 17, Spring Security (OAuth2 + JWT)**, and **Spring Data JPA**.

---

## ✨ Features

* **User Management** – Sign up, login, and manage profiles
* **Ask Questions** – Create and manage questions
* **Answer & Vote** – Post answers, upvote/downvote
* **Topic Management** – Organize questions by topics/interests
* **Social Sharing Ready** – APIs designed to integrate with WhatsApp, Stories, and more
* **Secure Authentication** – Google OAuth + JWT support
* **Admin & Moderation APIs** – Report and manage inappropriate content

---

## 🛠️ Tech Stack

| Layer          | Technology                             |
| -------------- | -------------------------------------- |
| **Backend**    | Spring Boot, Java 17                   |
| **Security**   | Spring Security (OAuth2 + JWT)         |
| **Database**   | PostgreSQL/MySQL (via Spring Data JPA) |
| **Build Tool** | Maven/Gradle                           |

---

## 🚀 Getting Started

### Prerequisites

* Java 17
* Maven or Gradle
* PostgreSQL/MySQL database

### Setup

```bash
# Clone the repo
git clone https://github.com/your-username/social-qna-backend.git
cd social-qna-backend

# Build the project
./mvnw clean install

# Run the Spring Boot app
./mvnw spring-boot:run
```

### Configuration

Update your `application.properties` or `application.yml` with:

* Database credentials
* OAuth credentials (Google, etc.)
* JWT secret

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/socialqna
spring.datasource.username=your_user
spring.datasource.password=your_password

spring.security.oauth2.client.registration.google.client-id=xxxx
spring.security.oauth2.client.registration.google.client-secret=yyyy
jwt.secret=your_jwt_secret
```

---

## 📖 API Highlights

* **Auth APIs**: Register, login, OAuth2 callback
* **Question APIs**: Create, read, update, delete questions
* **Answer APIs**: Post, upvote/downvote, comment
* **Topic APIs**: CRUD for topics/interests
* **Admin APIs**: Moderate content and manage reports

---

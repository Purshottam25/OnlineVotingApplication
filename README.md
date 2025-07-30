# 🗳️ Votezy - Online Voting Application

Votezy is a Spring Boot-based RESTful web application that facilitates a digital voting process. It supports voter and candidate registration, secure vote casting, and result declaration for a **single election at a time**.

## 🚀 Features

- ✅ Voter and candidate registration with validation  
- 🗳️ Vote casting with eligibility checks (one vote per voter)  
- 🧾 Vote count tracking per candidate  
- 🏆 Election result declaration (automatically determines the winner)  
- 📃 REST API endpoints for full control and integration  

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot, Spring Data JPA, Hibernate  
- **Validation:** Jakarta Bean Validation  
- **Database:** MySQL 8.x  
- **Build Tool:** Maven  
- **Lombok** for boilerplate reduction  

---

## 📁 Project Structure

```
com.votezy
├── controller         # REST controllers
├── dto               # Request/Response DTOs
├── entity            # JPA entities (Voter, Candidate, Vote, ElectionResult)
├── exception         # Custom exceptions
├── repository        # Spring Data JPA repositories
└── service           # Business logic
```

---

## 📦 API Endpoints

### 🔹 Voter Endpoints
- `POST /api/voter/register` - Register a new voter  
- `GET /api/voter/{id}` - Get voter by ID  
- `GET /api/voter` - Get all voters  
- `PUT /api/voter/update/{id}` - Update voter  
- `DELETE /api/voter/delete/{id}` - Delete voter  

### 🔹 Candidate Endpoints
- `POST /api/candidate/register` - Register a new candidate  
- `GET /api/candidate/{id}` - Get candidate by ID  
- `GET /api/candidate` - Get all candidates  
- `PUT /api/candidate/update/{id}` - Update candidate  
- `DELETE /api/candidate/delete/{id}` - Delete candidate  

### 🔹 Vote Endpoints
- `POST /api/vote/cast` - Cast a vote (one vote per voter)  
- `GET /api/vote` - Get all casted votes  

### 🔹 Election Result Endpoints
- `POST /api/result/declare` - Declare election result (requires election name)  
- `GET /api/result` - View all declared results  

---

## 🗃️ Database Schema

**Tables:**
- `voter`
- `candidate`
- `vote`
- `election_result`

**Relationships:**
- One-to-One: `Voter ↔ Vote`  
- Many-to-One: `Vote → Candidate`  
- One-to-One: `ElectionResult → Candidate (winner)`  

---

## ✅ Validation & Rules

- A voter can vote **only once**  
- Emails for voters and candidates must be **unique**  
- Election result is declared only if votes are cast  

---

## 🔧 Setup & Run Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Purshottam25/Votezy.git
cd Votezy
```

### 2. Configure MySQL Database

Ensure MySQL is running and create a database named `votesy_db`:
```sql
CREATE DATABASE votesy_db;
```

Update `src/main/resources/application.properties`:

```properties
spring.application.name=votezy

spring.datasource.url=jdbc:mysql://localhost:3306/votesy_db
spring.datasource.username=root
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 3. Build & Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

### 4. Test the API

Use **Postman**, **curl**, or Swagger UI (if integrated) to test the endpoints.

---

## ⚠️ Limitations

- Supports **only one election at a time**  
- No authentication or role-based access implemented  
- Election name is required during result declaration but not enforced across the system  

---

## 🙌 Author

**Purshottam Patel**

If you find this project helpful, feel free to ⭐ star the repo!

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

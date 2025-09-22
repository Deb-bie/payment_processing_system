# Payment Processing System

This is a scalable, fault-tolerant payment processing platform that enables businesses to accept payments securely while providing customerss with a seamless payment experience.



## 🧑‍💻 Project Structure

```
payment_processing_system/
├── services/
│   ├── api-gateway/          # API Gateway service
│   ├── user-service/          
│   ├── notifications-service/
│   ├── payment-gateway/
│   └── payment-processor/
│ 
├── .github/                  # Github actions
│       └── workflows
├── .env
└── docker-compose.yml        # Docker compose
```
## 🛠️ Tech Stack

| Area               | Technology                          |
|--------------------|--------------------------------------|
| Language/Framework | **Java 21**, Spring Boot, Gradle     |
| Databases          | **PostgreSQL**  |
| Messaging          | **Apache Kafka**|
| Caching            | **Redis**    |
| Containerization   | **Docker & Docker Compose**          |
| Testing            | JUnit, Mockito      




## Installation & Setup

**Prerequisites**
- Java 17+
- PostgreSQL
- Redis
- Kafka & Zookeeper
- Elasticsearch 
- Docker & Docker Compose
## Run Locally

Clone the repository

```bash
  git clone https://github.com/Deb-bie/payment_processing_system.git
```

Go to the project directory

```bash
  cd payment_processing_system
```

Setup Environment Variables

```bash
Duplicate application-example files in each service and fill in the required values.
```

Start services with Docker Compose

```bash
docker-compose up --build
```


## 🔗 Connect With Me

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/deborah-asamoah/)


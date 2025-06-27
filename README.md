# Tracking Number Generator API

A Spring Boot-based REST API for generating unique tracking numbers and storing them in MongoDB.  
Containerized with Docker and orchestrated via Docker Compose for easy deployment.

## Live Application
This API is live on ``https://tracking-number-app-3g67.onrender.com`` using render as cloud hosting and mongodb atlas as mongodb hosting

## Prerequisites
- Java 17+
- Maven 3.8+
- Docker 20+
- Docker Compose 2.30+

## Tech Stack
- **Backend**: Spring Boot
- **Database**: MongoDB
- **Containerization**: Docker + Docker Compose

---

## Quick Start (Local Development)

### 1. Clone the Repository
```bash
git clone https://github.com/mabdulloh/tracking_number.git
cd tracking_number
```

### 2. Run Docker Compose
```bash
docker compose up -build
```
- API will be available at: http://localhost:8080
- mongoDB can be accessed at: mongodb://localhost:27017

## API Endpoint
| Endpoint                  | Description                  | Params                                                                                                                                 |
|---------------------------|------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| /api/next-tracking-number | Generate new tracking number | origin_country_id <br/> destination_country_id <br/> weight <br/> created_at <br/> customer_id <br/> customer_name <br/> customer_slug |

Example
```bash
curl --request GET \
  --url 'http://localhost:8080/api/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2018-11-20T19:29:32+08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=Redbox Logistic&customer_slug=redbox_logistics'
```
Response
```json
{
  "tracking_number": "1120MYIDAZGTW9A3",
  "created_at": "2025-06-27T09:46:59+0000"
}
```


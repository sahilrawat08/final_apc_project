# Learning Management System (LMS) - Microservices Architecture

A comprehensive Learning Management System built with Spring Boot microservices architecture, featuring separate services for administrators and students, JWT authentication, and MongoDB as the database.

## 🏗️ Architecture Overview

This LMS follows a microservices architecture with the following components:

- **Admin Service** (Port 8081): Full CRUD control over courses, students, assignments, and reports
- **Student Service** (Port 8082): Course viewing, enrollment, assignment submission, and profile management
- **Common Module**: Shared models, DTOs, and utilities
- **MongoDB**: Document database for data persistence
- **JWT Authentication**: Token-based authentication with role-based access control

## 📋 Features

### Admin Functionalities
- ✅ Create, edit, and delete courses
- ✅ Manage student accounts and profiles
- ✅ Create and manage assignments
- ✅ Grade student submissions
- ✅ View all submissions and generate reports
- ✅ Enroll/unenroll students in courses
- ✅ Dashboard with statistics

### Student Functionalities
- ✅ View available courses
- ✅ Enroll in courses
- ✅ View enrolled courses and assignments
- ✅ Submit assignments
- ✅ View grades and feedback
- ✅ Update personal profile
- ✅ Dashboard with personal statistics

### Technical Features
- ✅ JWT-based authentication with role separation
- ✅ MongoDB document database
- ✅ RESTful API design
- ✅ Microservices architecture
- ✅ Thymeleaf web interface
- ✅ Docker deployment support
- ✅ Sample data initialization

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MongoDB 7.0+
- Docker (optional)

### 1. Clone and Setup

```bash
git clone <repository-url>
cd lms-microservices
```

### 2. Start MongoDB

**Option A: Using Docker**
```bash
cd docker
docker-compose up mongodb -d
```

**Option B: Local Installation**
```bash
# Install MongoDB (Ubuntu/Debian)
sudo apt-get update
sudo apt-get install -y mongodb-org

# Start MongoDB
sudo mkdir -p /data/db
sudo chown mongodb:mongodb /data/db
sudo -u mongodb mongod --dbpath /data/db --port 27017 --fork --logpath /var/log/mongodb.log
```

### 3. Build the Project

```bash
# Build common module first
cd common
mvn clean install -DskipTests

# Build admin service
cd ../admin-service
mvn clean compile

# Build student service
cd ../student-service
mvn clean compile
```

### 4. Run the Services

**Terminal 1 - Admin Service:**
```bash
cd admin-service
mvn spring-boot:run
```

**Terminal 2 - Student Service:**
```bash
cd student-service
mvn spring-boot:run
```

### 5. Access the Applications

- **Admin Portal**: http://localhost:8081/admin/login
- **Student Portal**: http://localhost:8082/student/login
- **Admin API**: http://localhost:8081/api/admin/*
- **Student API**: http://localhost:8082/api/student/*

## 👥 Default Accounts

The system comes with pre-configured accounts:

### Admin Account
- **Username**: admin
- **Password**: admin123
- **Access**: http://localhost:8081/admin/login

### Student Accounts
- **Student 1**:
  - Username: STU001
  - Password: password123
  - Name: John Doe
  - Department: Computer Science

- **Student 2**:
  - Username: STU002
  - Password: password123
  - Name: Jane Smith
  - Department: Mathematics

## 🔌 API Documentation

### Authentication Endpoints

#### Login (Both Services)
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "username": "admin",
    "role": "ADMIN",
    "userId": "64a1b2c3d4e5f6789abc0001"
  }
}
```

### Admin Service APIs (Port 8081)

#### Courses Management
```http
GET    /api/admin/courses              # Get all courses
POST   /api/admin/courses              # Create new course
GET    /api/admin/courses/{id}         # Get course by ID
PUT    /api/admin/courses/{id}         # Update course
DELETE /api/admin/courses/{id}         # Delete course
POST   /api/admin/courses/{courseId}/enroll/{studentId}    # Enroll student
DELETE /api/admin/courses/{courseId}/enroll/{studentId}    # Unenroll student
```

#### Students Management
```http
GET    /api/admin/students             # Get all students
POST   /api/admin/students             # Create new student
GET    /api/admin/students/{id}        # Get student by ID
PUT    /api/admin/students/{id}        # Update student
DELETE /api/admin/students/{id}        # Delete student
GET    /api/admin/students/department/{dept}  # Get students by department
```

#### Assignments Management
```http
GET    /api/admin/assignments          # Get all assignments
POST   /api/admin/assignments          # Create new assignment
GET    /api/admin/assignments/{id}     # Get assignment by ID
PUT    /api/admin/assignments/{id}     # Update assignment
DELETE /api/admin/assignments/{id}     # Delete assignment
GET    /api/admin/assignments/course/{courseId}  # Get assignments by course
```

#### Submissions Management
```http
GET    /api/admin/submissions          # Get all submissions
GET    /api/admin/submissions/{id}     # Get submission by ID
GET    /api/admin/submissions/assignment/{assignmentId}  # Get submissions by assignment
GET    /api/admin/submissions/student/{studentId}       # Get submissions by student
PUT    /api/admin/submissions/{id}/grade                 # Grade submission
DELETE /api/admin/submissions/{id}     # Delete submission
```

### Student Service APIs (Port 8082)

#### Courses
```http
GET    /api/student/courses            # Get all available courses
GET    /api/student/courses/{id}       # Get course details
GET    /api/student/courses/enrolled   # Get enrolled courses
POST   /api/student/courses/{courseId}/enroll      # Enroll in course
DELETE /api/student/courses/{courseId}/unenroll    # Unenroll from course
```

#### Assignments
```http
GET    /api/student/assignments        # Get all active assignments
GET    /api/student/assignments/{id}   # Get assignment details
GET    /api/student/assignments/course/{courseId}  # Get assignments by course
```

#### Submissions
```http
GET    /api/student/submissions                    # Get my submissions
GET    /api/student/submissions/assignment/{assignmentId}  # Get my submission for assignment
POST   /api/student/submissions/assignment/{assignmentId}  # Submit assignment
PUT    /api/student/submissions/{id}               # Update submission
```

#### Profile
```http
GET    /api/student/profile            # Get my profile
PUT    /api/student/profile            # Update my profile
```

## 🗄️ Database Schema

The system uses MongoDB with the following collections:

### Users Collection
```json
{
  "_id": "ObjectId",
  "username": "String",
  "email": "String",
  "password": "String (hashed)",
  "firstName": "String",
  "lastName": "String",
  "role": "ADMIN|STUDENT",
  "createdAt": "Date",
  "updatedAt": "Date",
  "enabled": "Boolean"
}
```

### Students Collection
```json
{
  "_id": "ObjectId",
  "userId": "String (reference to users)",
  "studentId": "String (unique)",
  "firstName": "String",
  "lastName": "String",
  "email": "String",
  "department": "String",
  "phoneNumber": "String",
  "enrolledCourses": ["String (course IDs)"],
  "createdAt": "Date",
  "updatedAt": "Date"
}
```

### Courses Collection
```json
{
  "_id": "ObjectId",
  "title": "String",
  "description": "String",
  "instructorId": "String",
  "instructorName": "String",
  "enrolledStudents": ["String (student IDs)"],
  "maxStudents": "Number",
  "status": "ACTIVE|INACTIVE|COMPLETED",
  "createdAt": "Date",
  "updatedAt": "Date",
  "startDate": "Date",
  "endDate": "Date"
}
```

### Assignments Collection
```json
{
  "_id": "ObjectId",
  "courseId": "String",
  "title": "String",
  "description": "String",
  "dueDate": "Date",
  "maxScore": "Number",
  "status": "ACTIVE|INACTIVE|COMPLETED",
  "createdAt": "Date",
  "updatedAt": "Date"
}
```

### Submissions Collection
```json
{
  "_id": "ObjectId",
  "assignmentId": "String",
  "studentId": "String",
  "studentName": "String",
  "content": "String",
  "attachmentUrl": "String",
  "grade": "Number",
  "feedback": "String",
  "status": "SUBMITTED|GRADED|LATE_SUBMISSION",
  "submittedAt": "Date",
  "gradedAt": "Date"
}
```

## 🐳 Docker Deployment

### Using Docker Compose

```bash
cd docker
docker-compose up -d
```

This will start:
- MongoDB on port 27017
- Admin Service on port 8081
- Student Service on port 8082

### Manual Docker Build

**Build Images:**
```bash
# Build admin service
cd admin-service
docker build -t lms-admin-service .

# Build student service
cd ../student-service
docker build -t lms-student-service .
```

**Run Containers:**
```bash
# Start MongoDB
docker run -d --name lms-mongodb -p 27017:27017 mongo:7.0

# Start Admin Service
docker run -d --name lms-admin-service -p 8081:8081 \
  --link lms-mongodb:mongodb \
  -e SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/lms_db \
  lms-admin-service

# Start Student Service
docker run -d --name lms-student-service -p 8082:8082 \
  --link lms-mongodb:mongodb \
  -e SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/lms_db \
  lms-student-service
```

## 🔧 Configuration

### Application Properties

**Admin Service (application.yml):**
```yaml
server:
  port: 8081

spring:
  application:
    name: admin-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/lms_db
  thymeleaf:
    cache: false
```

**Student Service (application.yml):**
```yaml
server:
  port: 8082

spring:
  application:
    name: student-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/lms_db
  thymeleaf:
    cache: false
```

### JWT Configuration

JWT tokens are configured with:
- **Secret Key**: "mySecretKeyForJWTTokenGenerationThatIsLongEnoughForHS256"
- **Expiration**: 5 hours
- **Algorithm**: HS256

To change JWT settings, modify the `JwtUtil` class in the common module.

## 🧪 Testing

### Running Unit Tests

```bash
# Test common module
cd common
mvn test

# Test admin service
cd ../admin-service
mvn test

# Test student service
cd ../student-service
mvn test
```

### API Testing with cURL

**Login as Admin:**
```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

**Create a Course (Admin):**
```bash
curl -X POST http://localhost:8081/api/admin/courses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "title": "New Course",
    "description": "Course description",
    "instructorName": "Dr. Smith",
    "maxStudents": 30,
    "status": "ACTIVE"
  }'
```

**Login as Student:**
```bash
curl -X POST http://localhost:8082/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"STU001","password":"password123"}'
```

**Enroll in Course (Student):**
```bash
curl -X POST http://localhost:8082/api/student/courses/COURSE_ID/enroll \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🐛 Troubleshooting

### Common Issues

1. **MongoDB Connection Error**
   - Ensure MongoDB is running on port 27017
   - Check MongoDB logs: `tail -f /var/log/mongodb.log`

2. **Port Already in Use**
   - Check if ports 8081/8082 are available
   - Kill existing processes: `lsof -ti:8081 | xargs kill -9`

3. **JWT Token Issues**
   - Tokens expire after 5 hours
   - Ensure correct Authorization header format: `Bearer <token>`

4. **Build Failures**
   - Ensure Java 17+ is installed
   - Build common module first: `cd common && mvn clean install`

### Logs Location

- **Admin Service**: Console output or `./logs/admin-service.log`
- **Student Service**: Console output or `./logs/student-service.log`
- **MongoDB**: `/var/log/mongodb.log`

## 📊 Sample Data

The system initializes with sample data including:

- **1 Admin User**: admin/admin123
- **2 Student Users**: STU001/password123, STU002/password123
- **3 Sample Courses**: Introduction to Programming, Data Structures, Calculus I
- **2 Sample Assignments**: Hello World Program, Binary Search Implementation

## 🔐 Security

- Passwords are hashed using BCrypt
- JWT tokens for stateless authentication
- Role-based access control (ADMIN/STUDENT)
- CORS enabled for development
- Input validation on all endpoints

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue in the repository
- Check the troubleshooting section
- Review the API documentation

---

**Happy Learning! 🎓**
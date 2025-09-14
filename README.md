# Learning Management System (LMS)

A comprehensive Learning Management System built with Spring Boot 3.5.5, featuring course management, student enrollment, assignments, tests, grades, and discussion forums.

## 🚀 Features

### Core Modules
- **Dashboard** - Central hub with navigation to all features
- **Students** - Manage student records and enrollments
- **Faculty** - Manage faculty information
- **Courses** - Create and manage course offerings
- **Assignments** - Create assignments with deadlines
- **Tests/Quizzes** - Create tests with multiple-choice questions
- **Grades** - Record and view student grades
- **Notices** - Post announcements visible on dashboard
- **Timetable** - Weekly schedule for courses
- **Discussion Forum** - Student discussions per course

### Technical Features
- **Spring Boot 3.5.5** with Java 24
- **Spring Data JPA** for database operations
- **Thymeleaf** for server-side rendering
- **MySQL** database with auto-generated schema
- **Bootstrap 5** for responsive UI
- **Maven** build tool
- **Preloaded sample data** for immediate testing

## 🛠️ Prerequisites

- **Java 24** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

## 📋 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd lms
```

### 2. Database Setup

#### Install MySQL
- Download and install MySQL from [mysql.com](https://dev.mysql.com/downloads/mysql/)
- Start MySQL service
- Create a database user (optional, can use root)

#### Configure Database Connection
Update `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/lms_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

**Important:** Replace `your_mysql_password` with your actual MySQL root password.

### 3. Build and Run

#### Using Maven
```bash
# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

#### Using IDE
1. Import the project into your IDE
2. Ensure Maven dependencies are resolved
3. Run `LmsApplication.java` as a Java application

### 4. Access the Application

Open your browser and navigate to:
```
http://localhost:8080
```

The application will automatically:
- Create the database `lms_db` if it doesn't exist
- Generate all required tables
- Load sample data (students, faculty, courses, etc.)

## 🎯 Quick Start Guide

### First Login
The application opens directly to the dashboard (no authentication required for now).

### Sample Data Included
The system comes pre-loaded with:
- **5 Faculty members** across different departments
- **10 Students** with various roll numbers
- **10 Courses** covering different subjects
- **10 Assignments** with deadlines
- **10 Tests** with sample questions
- **Sample grades** for students
- **8 Notices** for announcements
- **Weekly timetable** for all courses
- **Forum posts** for course discussions

### Navigation
Use the top navigation bar to access different modules:
- **Dashboard** - Overview and recent notices
- **Students** - Add, edit, delete student records
- **Faculty** - Manage faculty information
- **Courses** - Create courses and assign faculty
- **Assignments** - Create assignments with deadlines
- **Tests** - Create tests and add questions
- **Grades** - Record student grades
- **Notices** - Post announcements
- **Timetable** - Schedule course timings
- **Forum** - Course-specific discussions

## 🏗️ Project Structure

```
src/main/java/com/university/lms/
├── controller/          # Web controllers for all modules
├── service/            # Business logic layer
├── repository/         # JPA repositories
├── model/              # JPA entities
└── LmsApplication.java # Main application class

src/main/resources/
├── templates/          # Thymeleaf HTML templates
│   ├── dashboard.html
│   ├── students/
│   ├── faculty/
│   ├── courses/
│   ├── assignments/
│   ├── tests/
│   ├── questions/
│   ├── grades/
│   ├── notices/
│   ├── timetable/
│   └── forum/
├── static/             # CSS and JavaScript files
│   ├── css/style.css
│   └── js/script.js
├── application.properties
└── data.sql           # Sample data
```

## 🗄️ Database Schema

### Core Entities
- **Student** (id, name, rollNumber, department)
- **Faculty** (id, name, department, contact)
- **Course** (id, title, description, faculty)
- **Assignment** (id, course, title, deadline, description)
- **Test** (id, course, title)
- **Question** (id, test, questionText, options, correctAnswer)
- **Grade** (id, student, course, grade)
- **Notice** (id, title, message, createdAt)
- **Timetable** (id, course, day, startTime, endTime)
- **ForumPost** (id, course, student, message, createdAt)

### Relationships
- Many-to-Many: Students ↔ Courses
- One-to-Many: Faculty → Courses
- One-to-Many: Course → Assignments, Tests, Grades, Timetables, ForumPosts
- One-to-Many: Test → Questions

## 🎨 UI Features

- **Responsive Design** - Works on desktop, tablet, and mobile
- **Bootstrap 5** - Modern, clean interface
- **Navigation Bar** - Easy access to all modules
- **Dashboard Cards** - Quick navigation to features
- **Data Tables** - Sortable and searchable lists
- **Forms** - User-friendly input forms
- **Alerts** - Success/error messages
- **Modals** - Confirmation dialogs

## 🔧 Configuration Options

### Application Properties
```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/lms_db
spring.datasource.username=root
spring.datasource.password=password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Thymeleaf
spring.thymeleaf.cache=false

# Data Initialization
spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:data.sql
```

## 🚨 Troubleshooting

### Common Issues

#### 1. Database Connection Error
```
Error: Access denied for user 'root'@'localhost'
```
**Solution:** Update MySQL credentials in `application.properties`

#### 2. Port Already in Use
```
Error: Port 8080 was already in use
```
**Solution:** Change port in `application.properties` or stop the conflicting service

#### 3. Maven Build Issues
```
Error: Could not resolve dependencies
```
**Solution:** 
```bash
mvn clean install
mvn dependency:resolve
```

#### 4. MySQL Not Running
```
Error: Communications link failure
```
**Solution:** Start MySQL service:
- Windows: Services → MySQL80 → Start
- macOS: `brew services start mysql`
- Linux: `sudo systemctl start mysql`

### Logs
Check application logs for detailed error information:
```bash
tail -f logs/spring.log
```

## 🔮 Future Enhancements

- **Authentication & Authorization** - User login and role-based access
- **File Upload** - Assignment submissions and course materials
- **Email Notifications** - Assignment reminders and grade notifications
- **Calendar Integration** - Export timetable to calendar apps
- **Mobile App** - React Native or Flutter mobile application
- **API Endpoints** - REST API for mobile app integration
- **Advanced Analytics** - Student performance analytics
- **Video Integration** - Embedded video lectures
- **Real-time Chat** - Live discussion features

## 📝 Development Notes

### Adding New Features
1. Create entity in `model/` package
2. Create repository in `repository/` package
3. Create service in `service/` package
4. Create controller in `controller/` package
5. Create templates in `templates/` directory
6. Update navigation in templates

### Database Changes
- Modify entities in `model/` package
- Update `data.sql` for sample data
- Restart application to apply schema changes

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📞 Support

For issues and questions:
- Create an issue in the repository
- Check the troubleshooting section
- Review application logs for errors

---

**Happy Learning! 🎓**

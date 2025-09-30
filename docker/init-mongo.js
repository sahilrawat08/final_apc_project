// Initialize LMS database with sample data

// Create the database and switch to it
db = db.getSiblingDB('lms_db');

// Create users collection with admin and sample students
db.users.insertMany([
  {
    _id: ObjectId(),
    username: "admin",
    email: "admin@university.edu",
    password: "$2a$10$YourHashedPasswordHere", // Password: admin123
    firstName: "System",
    lastName: "Administrator",
    role: "ADMIN",
    createdAt: new Date(),
    updatedAt: new Date(),
    enabled: true
  },
  {
    _id: ObjectId("64a1b2c3d4e5f6789abcdef1"),
    username: "STU001",
    email: "john.doe@student.edu",
    password: "$2a$10$YourHashedPasswordHere", // Password: password123
    firstName: "John",
    lastName: "Doe",
    role: "STUDENT",
    createdAt: new Date(),
    updatedAt: new Date(),
    enabled: true
  },
  {
    _id: ObjectId("64a1b2c3d4e5f6789abcdef2"),
    username: "STU002", 
    email: "jane.smith@student.edu",
    password: "$2a$10$YourHashedPasswordHere", // Password: password123
    firstName: "Jane",
    lastName: "Smith",
    role: "STUDENT",
    createdAt: new Date(),
    updatedAt: new Date(),
    enabled: true
  }
]);

// Create students collection
db.students.insertMany([
  {
    _id: ObjectId(),
    userId: "64a1b2c3d4e5f6789abcdef1",
    studentId: "STU001",
    firstName: "John",
    lastName: "Doe",
    email: "john.doe@student.edu",
    department: "Computer Science",
    phoneNumber: "+1-555-0101",
    enrolledCourses: [],
    createdAt: new Date(),
    updatedAt: new Date()
  },
  {
    _id: ObjectId(),
    userId: "64a1b2c3d4e5f6789abcdef2", 
    studentId: "STU002",
    firstName: "Jane",
    lastName: "Smith",
    email: "jane.smith@student.edu",
    department: "Mathematics",
    phoneNumber: "+1-555-0102",
    enrolledCourses: [],
    createdAt: new Date(),
    updatedAt: new Date()
  }
]);

// Create courses collection
db.courses.insertMany([
  {
    _id: ObjectId("64a1b2c3d4e5f6789abc0001"),
    title: "Introduction to Programming",
    description: "Learn the fundamentals of programming using Java",
    instructorId: "admin",
    instructorName: "Dr. Johnson",
    enrolledStudents: [],
    maxStudents: 30,
    status: "ACTIVE",
    createdAt: new Date(),
    updatedAt: new Date(),
    startDate: new Date("2024-01-15"),
    endDate: new Date("2024-05-15")
  },
  {
    _id: ObjectId("64a1b2c3d4e5f6789abc0002"),
    title: "Data Structures and Algorithms",
    description: "Advanced programming concepts and algorithm design",
    instructorId: "admin",
    instructorName: "Dr. Wilson",
    enrolledStudents: [],
    maxStudents: 25,
    status: "ACTIVE",
    createdAt: new Date(),
    updatedAt: new Date(),
    startDate: new Date("2024-01-15"),
    endDate: new Date("2024-05-15")
  },
  {
    _id: ObjectId("64a1b2c3d4e5f6789abc0003"),
    title: "Calculus I",
    description: "Introduction to differential and integral calculus",
    instructorId: "admin",
    instructorName: "Dr. Brown",
    enrolledStudents: [],
    maxStudents: 40,
    status: "ACTIVE",
    createdAt: new Date(),
    updatedAt: new Date(),
    startDate: new Date("2024-01-15"),
    endDate: new Date("2024-05-15")
  }
]);

// Create assignments collection
db.assignments.insertMany([
  {
    _id: ObjectId(),
    courseId: "64a1b2c3d4e5f6789abc0001",
    title: "Hello World Program",
    description: "Write your first Java program that prints 'Hello, World!' to the console",
    dueDate: new Date("2024-02-01T23:59:59Z"),
    maxScore: 100,
    status: "ACTIVE",
    createdAt: new Date(),
    updatedAt: new Date()
  },
  {
    _id: ObjectId(),
    courseId: "64a1b2c3d4e5f6789abc0002",
    title: "Binary Search Implementation",
    description: "Implement binary search algorithm in Java",
    dueDate: new Date("2024-02-15T23:59:59Z"),
    maxScore: 100,
    status: "ACTIVE",
    createdAt: new Date(),
    updatedAt: new Date()
  }
]);

print("Sample data inserted successfully!");
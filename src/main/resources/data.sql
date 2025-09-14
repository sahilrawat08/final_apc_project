-- Sample data for LMS system

-- Insert Faculty
INSERT INTO faculty (name, department, contact) VALUES
('Dr. Sarah Johnson', 'Computer Science', 'sarah.johnson@university.edu'),
('Prof. Michael Chen', 'Mathematics', 'michael.chen@university.edu'),
('Dr. Emily Rodriguez', 'Physics', 'emily.rodriguez@university.edu'),
('Prof. David Wilson', 'Chemistry', 'david.wilson@university.edu'),
('Dr. Lisa Thompson', 'Biology', 'lisa.thompson@university.edu');

-- Insert Students
INSERT INTO students (name, roll_number, department) VALUES
('John Smith', '2024001', 'Computer Science'),
('Emma Davis', '2024002', 'Computer Science'),
('James Wilson', '2024003', 'Mathematics'),
('Sophia Brown', '2024004', 'Physics'),
('William Taylor', '2024005', 'Chemistry'),
('Olivia Anderson', '2024006', 'Biology'),
('Benjamin Martinez', '2024007', 'Computer Science'),
('Charlotte Garcia', '2024008', 'Mathematics'),
('Lucas Miller', '2024009', 'Physics'),
('Amelia Jones', '2024010', 'Chemistry');

-- Insert Courses
INSERT INTO courses (title, description, faculty_id) VALUES
('Introduction to Programming', 'Learn the fundamentals of programming using Java', 1),
('Data Structures and Algorithms', 'Advanced programming concepts and algorithm design', 1),
('Calculus I', 'Differential and integral calculus', 2),
('Linear Algebra', 'Vector spaces, matrices, and linear transformations', 2),
('Classical Mechanics', 'Newtonian mechanics and dynamics', 3),
('Quantum Physics', 'Introduction to quantum mechanics', 3),
('Organic Chemistry', 'Structure and reactions of organic compounds', 4),
('Biochemistry', 'Chemical processes in living organisms', 5),
('Cell Biology', 'Structure and function of cells', 5),
('Advanced Mathematics', 'Complex analysis and topology', 2);

-- Insert Assignments
INSERT INTO assignments (course_id, title, description, deadline, created_at) VALUES
(1, 'Hello World Program', 'Create your first Java program that prints Hello World', '2024-02-15 23:59:00', '2024-01-15 10:00:00'),
(1, 'Calculator Application', 'Build a simple calculator with basic operations', '2024-02-28 23:59:00', '2024-01-20 10:00:00'),
(2, 'Sorting Algorithms', 'Implement various sorting algorithms and compare their performance', '2024-03-10 23:59:00', '2024-02-01 10:00:00'),
(3, 'Derivative Problems', 'Solve calculus problems involving derivatives', '2024-02-20 23:59:00', '2024-01-25 10:00:00'),
(4, 'Matrix Operations', 'Perform matrix calculations and transformations', '2024-03-05 23:59:00', '2024-02-10 10:00:00'),
(5, 'Projectile Motion', 'Analyze projectile motion problems', '2024-02-25 23:59:00', '2024-01-30 10:00:00'),
(6, 'Wave Function Analysis', 'Study quantum wave functions and probability', '2024-03-15 23:59:00', '2024-02-15 10:00:00'),
(7, 'Organic Synthesis', 'Design synthesis pathways for organic compounds', '2024-03-20 23:59:00', '2024-02-20 10:00:00'),
(8, 'Enzyme Kinetics', 'Study enzyme reactions and kinetics', '2024-03-25 23:59:00', '2024-02-25 10:00:00'),
(9, 'Cell Structure Analysis', 'Examine cellular components and their functions', '2024-03-30 23:59:00', '2024-03-01 10:00:00');

-- Insert Tests
INSERT INTO tests (course_id, title) VALUES
(1, 'Programming Fundamentals Quiz'),
(1, 'Java Syntax Test'),
(2, 'Algorithm Analysis Exam'),
(3, 'Calculus Midterm'),
(4, 'Linear Algebra Quiz'),
(5, 'Mechanics Test'),
(6, 'Quantum Physics Exam'),
(7, 'Organic Chemistry Quiz'),
(8, 'Biochemistry Test'),
(9, 'Cell Biology Exam');

-- Insert Questions
INSERT INTO questions (test_id, question_text, options, correct_answer) VALUES
(1, 'What is the correct syntax to declare a variable in Java?', 'A) int x = 5; B) var x = 5; C) x = 5; D) declare x = 5;', 'A'),
(1, 'Which keyword is used to create a new object in Java?', 'A) new B) create C) make D) object', 'A'),
(2, 'What is the output of System.out.println("Hello" + "World");', 'A) HelloWorld B) Hello World C) Hello+World D) Error', 'A'),
(2, 'Which data type is used for storing whole numbers in Java?', 'A) float B) double C) int D) string', 'C'),
(3, 'What is the time complexity of bubble sort?', 'A) O(n) B) O(n log n) C) O(n²) D) O(log n)', 'C'),
(3, 'Which sorting algorithm has the best average-case time complexity?', 'A) Bubble Sort B) Selection Sort C) Quick Sort D) Insertion Sort', 'C'),
(4, 'What is the derivative of x²?', 'A) x B) 2x C) x² D) 2x²', 'B'),
(4, 'What is the integral of 2x?', 'A) x² B) 2x² C) x² + C D) 2x² + C', 'C'),
(5, 'What is the determinant of a 2x2 matrix [[a,b],[c,d]]?', 'A) ad + bc B) ad - bc C) ab - cd D) ac - bd', 'B'),
(5, 'What is the inverse of a matrix?', 'A) Transpose B) Negative C) Reciprocal D) Adjoint divided by determinant', 'D');

-- Insert Grades
INSERT INTO grades (student_id, course_id, grade, created_at) VALUES
(1, 1, 'A', '2024-02-15 10:00:00'),
(1, 2, 'B+', '2024-03-10 10:00:00'),
(2, 1, 'A-', '2024-02-15 10:00:00'),
(2, 2, 'A', '2024-03-10 10:00:00'),
(3, 3, 'B', '2024-02-20 10:00:00'),
(3, 4, 'A-', '2024-03-05 10:00:00'),
(4, 5, 'B+', '2024-02-25 10:00:00'),
(4, 6, 'A', '2024-03-15 10:00:00'),
(5, 7, 'B', '2024-03-20 10:00:00'),
(5, 8, 'B+', '2024-03-25 10:00:00'),
(6, 9, 'A-', '2024-03-30 10:00:00'),
(7, 1, 'B+', '2024-02-15 10:00:00'),
(7, 2, 'A-', '2024-03-10 10:00:00'),
(8, 3, 'A', '2024-02-20 10:00:00'),
(8, 4, 'B+', '2024-03-05 10:00:00'),
(9, 5, 'A-', '2024-02-25 10:00:00'),
(9, 6, 'B', '2024-03-15 10:00:00'),
(10, 7, 'A', '2024-03-20 10:00:00'),
(10, 8, 'B+', '2024-03-25 10:00:00');

-- Insert Notices
INSERT INTO notices (title, message, created_at) VALUES
('Welcome to Spring Semester 2024', 'Welcome all students to the Spring 2024 semester. Please check your course schedules and assignment deadlines.', '2024-01-15 09:00:00'),
('Midterm Exam Schedule', 'Midterm exams will be conducted from March 1-15, 2024. Please check your individual exam schedules.', '2024-02-20 14:30:00'),
('Library Hours Extended', 'The university library will now be open until 11 PM on weekdays to accommodate students studying for exams.', '2024-02-25 16:00:00'),
('Course Registration Open', 'Registration for Summer 2024 courses is now open. Please visit the registrar office or online portal.', '2024-03-01 10:00:00'),
('Graduation Ceremony', 'The Spring 2024 graduation ceremony will be held on May 15, 2024. More details to follow.', '2024-03-10 11:00:00'),
('Research Symposium', 'Annual research symposium will be held on April 20, 2024. Students are encouraged to present their projects.', '2024-03-15 13:00:00'),
('Holiday Notice', 'University will be closed on Good Friday, March 29, 2024. Classes will resume on Monday.', '2024-03-20 15:00:00'),
('Scholarship Applications', 'Applications for merit-based scholarships are now open. Deadline is April 30, 2024.', '2024-03-25 12:00:00');

-- Insert Timetable
INSERT INTO timetables (course_id, day, start_time, end_time) VALUES
(1, 'Monday', '09:00:00', '10:30:00'),
(1, 'Wednesday', '09:00:00', '10:30:00'),
(1, 'Friday', '09:00:00', '10:30:00'),
(2, 'Tuesday', '11:00:00', '12:30:00'),
(2, 'Thursday', '11:00:00', '12:30:00'),
(3, 'Monday', '14:00:00', '15:30:00'),
(3, 'Wednesday', '14:00:00', '15:30:00'),
(4, 'Tuesday', '16:00:00', '17:30:00'),
(4, 'Thursday', '16:00:00', '17:30:00'),
(5, 'Monday', '10:00:00', '11:30:00'),
(5, 'Wednesday', '10:00:00', '11:30:00'),
(6, 'Tuesday', '13:00:00', '14:30:00'),
(6, 'Thursday', '13:00:00', '14:30:00'),
(7, 'Friday', '15:00:00', '16:30:00'),
(8, 'Monday', '16:00:00', '17:30:00'),
(8, 'Wednesday', '16:00:00', '17:30:00'),
(9, 'Tuesday', '09:00:00', '10:30:00'),
(9, 'Thursday', '09:00:00', '10:30:00'),
(10, 'Friday', '11:00:00', '12:30:00');

-- Insert Forum Posts
INSERT INTO forum_posts (course_id, student_id, message, created_at) VALUES
(1, 1, 'Can someone explain the difference between ArrayList and LinkedList?', '2024-01-20 14:30:00'),
(1, 2, 'ArrayList is better for random access, LinkedList is better for frequent insertions/deletions.', '2024-01-20 15:45:00'),
(1, 7, 'Thanks for the explanation! That makes sense now.', '2024-01-20 16:20:00'),
(2, 1, 'What is the best way to approach dynamic programming problems?', '2024-02-05 10:15:00'),
(2, 2, 'Start with understanding the problem, identify overlapping subproblems, then build the solution bottom-up.', '2024-02-05 11:30:00'),
(3, 3, 'Can someone help me with this calculus problem involving limits?', '2024-02-10 13:20:00'),
(3, 8, 'Sure! What specific limit are you working on?', '2024-02-10 14:00:00'),
(4, 3, 'Linear algebra is getting complex. Any study tips?', '2024-02-15 09:30:00'),
(4, 8, 'Practice with matrix operations daily. Visualizing transformations helps a lot.', '2024-02-15 10:15:00'),
(5, 4, 'Physics lab report due next week. Anyone want to form a study group?', '2024-02-20 16:45:00'),
(5, 9, 'I am interested! When and where should we meet?', '2024-02-20 17:30:00'),
(6, 4, 'Quantum mechanics is fascinating but challenging. Any recommended resources?', '2024-03-01 12:00:00'),
(6, 9, 'Check out MIT OpenCourseWare quantum mechanics lectures. Very helpful!', '2024-03-01 13:15:00'),
(7, 5, 'Organic chemistry reactions are overwhelming. How do you memorize them?', '2024-03-05 11:20:00'),
(7, 10, 'Focus on understanding mechanisms rather than memorizing. It helps in the long run.', '2024-03-05 12:30:00'),
(8, 5, 'Biochemistry lab results are confusing. Can someone explain enzyme kinetics?', '2024-03-10 14:45:00'),
(8, 10, 'Enzyme kinetics follows Michaelis-Menten equation. The key is understanding Km and Vmax.', '2024-03-10 15:30:00'),
(9, 6, 'Cell biology exam next week. What topics should I focus on?', '2024-03-15 10:00:00'),
(9, 6, 'Focus on cell organelles, membrane transport, and cell division processes.', '2024-03-15 10:45:00');

-- Insert Course-Student Enrollments
INSERT INTO course_students (course_id, student_id) VALUES
(1, 1), (1, 2), (1, 7),
(2, 1), (2, 2), (2, 7),
(3, 3), (3, 8),
(4, 3), (4, 8),
(5, 4), (5, 9),
(6, 4), (6, 9),
(7, 5), (7, 10),
(8, 5), (8, 10),
(9, 6),
(10, 3), (10, 8);

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the common library first
COPY ../common/target/lms-common-1.0.0.jar /app/lib/lms-common-1.0.0.jar

# Copy Maven files
COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "target/admin-service-1.0.0.jar"]
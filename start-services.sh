#!/bin/bash

echo "🚀 Starting LMS Microservices..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to check if a port is available
check_port() {
    if lsof -ti:$1 >/dev/null 2>&1; then
        echo -e "${RED}Port $1 is already in use!${NC}"
        echo "Please stop the service using port $1 or run: lsof -ti:$1 | xargs kill -9"
        return 1
    fi
    return 0
}

# Function to check if MongoDB is running
check_mongodb() {
    if ! pgrep mongod >/dev/null 2>&1; then
        echo -e "${YELLOW}MongoDB is not running. Starting MongoDB...${NC}"
        
        # Create data directory if it doesn't exist
        sudo mkdir -p /data/db
        sudo chown mongodb:mongodb /data/db
        
        # Start MongoDB
        sudo -u mongodb mongod --dbpath /data/db --port 27017 --fork --logpath /var/log/mongodb.log
        
        # Wait for MongoDB to start
        sleep 3
        
        if pgrep mongod >/dev/null 2>&1; then
            echo -e "${GREEN}✅ MongoDB started successfully${NC}"
        else
            echo -e "${RED}❌ Failed to start MongoDB${NC}"
            echo "Please check the MongoDB logs: sudo tail -f /var/log/mongodb.log"
            exit 1
        fi
    else
        echo -e "${GREEN}✅ MongoDB is already running${NC}"
    fi
}

# Function to build common module
build_common() {
    echo -e "${BLUE}📦 Building common module...${NC}"
    cd common
    if mvn clean install -DskipTests -q; then
        echo -e "${GREEN}✅ Common module built successfully${NC}"
    else
        echo -e "${RED}❌ Failed to build common module${NC}"
        exit 1
    fi
    cd ..
}

# Check prerequisites
echo -e "${BLUE}🔍 Checking prerequisites...${NC}"

# Check Java
if ! command -v java &> /dev/null; then
    echo -e "${RED}❌ Java is not installed${NC}"
    exit 1
fi

# Check Maven
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}❌ Maven is not installed${NC}"
    exit 1
fi

# Check MongoDB
if ! command -v mongod &> /dev/null; then
    echo -e "${RED}❌ MongoDB is not installed${NC}"
    echo "Please install MongoDB first: sudo apt-get install -y mongodb-org"
    exit 1
fi

echo -e "${GREEN}✅ All prerequisites are available${NC}"

# Check ports
echo -e "${BLUE}🔍 Checking ports...${NC}"
check_port 8081 || exit 1
check_port 8082 || exit 1
echo -e "${GREEN}✅ Ports 8081 and 8082 are available${NC}"

# Check and start MongoDB
check_mongodb

# Build common module
build_common

# Start Admin Service
echo -e "${BLUE}🔧 Starting Admin Service (Port 8081)...${NC}"
cd admin-service
mvn spring-boot:run > ../logs/admin-service.log 2>&1 &
ADMIN_PID=$!
echo "Admin Service PID: $ADMIN_PID"
cd ..

# Wait a moment for admin service to start
sleep 5

# Start Student Service
echo -e "${BLUE}🔧 Starting Student Service (Port 8082)...${NC}"
cd student-service
mvn spring-boot:run > ../logs/student-service.log 2>&1 &
STUDENT_PID=$!
echo "Student Service PID: $STUDENT_PID"
cd ..

# Create logs directory if it doesn't exist
mkdir -p logs

# Save PIDs for cleanup
echo $ADMIN_PID > logs/admin-service.pid
echo $STUDENT_PID > logs/student-service.pid

echo ""
echo -e "${GREEN}🎉 LMS Services are starting up!${NC}"
echo ""
echo -e "${YELLOW}📊 Service URLs:${NC}"
echo "   Admin Portal:  http://localhost:8081/admin/login"
echo "   Student Portal: http://localhost:8082/student/login"
echo "   Admin API:     http://localhost:8081/api/admin/"
echo "   Student API:   http://localhost:8082/api/student/"
echo ""
echo -e "${YELLOW}👥 Default Accounts:${NC}"
echo "   Admin:    admin / admin123"
echo "   Student:  STU001 / password123"
echo "   Student:  STU002 / password123"
echo ""
echo -e "${BLUE}📝 Logs:${NC}"
echo "   Admin Service:  tail -f logs/admin-service.log"
echo "   Student Service: tail -f logs/student-service.log"
echo ""
echo -e "${YELLOW}🛑 To stop services:${NC}"
echo "   Run: ./stop-services.sh"
echo ""
echo -e "${GREEN}✨ Services will be ready in about 30-60 seconds...${NC}"
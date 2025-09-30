#!/bin/bash

echo "🛑 Stopping LMS Microservices..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to stop service by PID
stop_service() {
    local service_name=$1
    local pid_file=$2
    
    if [ -f "$pid_file" ]; then
        local pid=$(cat "$pid_file")
        if ps -p $pid > /dev/null 2>&1; then
            echo -e "${BLUE}Stopping $service_name (PID: $pid)...${NC}"
            kill $pid
            # Wait for graceful shutdown
            sleep 5
            # Force kill if still running
            if ps -p $pid > /dev/null 2>&1; then
                echo -e "${YELLOW}Force killing $service_name...${NC}"
                kill -9 $pid
            fi
            echo -e "${GREEN}✅ $service_name stopped${NC}"
        else
            echo -e "${YELLOW}$service_name is not running${NC}"
        fi
        rm -f "$pid_file"
    else
        echo -e "${YELLOW}No PID file found for $service_name${NC}"
    fi
}

# Stop services by port if PID files don't exist
stop_by_port() {
    local port=$1
    local service_name=$2
    
    local pids=$(lsof -ti:$port 2>/dev/null)
    if [ ! -z "$pids" ]; then
        echo -e "${BLUE}Stopping $service_name on port $port...${NC}"
        echo "$pids" | xargs kill -9 2>/dev/null
        echo -e "${GREEN}✅ $service_name stopped${NC}"
    else
        echo -e "${YELLOW}No service running on port $port${NC}"
    fi
}

# Create logs directory if it doesn't exist
mkdir -p logs

# Stop Admin Service
if [ -f "logs/admin-service.pid" ]; then
    stop_service "Admin Service" "logs/admin-service.pid"
else
    stop_by_port 8081 "Admin Service"
fi

# Stop Student Service
if [ -f "logs/student-service.pid" ]; then
    stop_service "Student Service" "logs/student-service.pid"
else
    stop_by_port 8082 "Student Service"
fi

# Optionally stop MongoDB (uncomment if needed)
# echo -e "${BLUE}Stopping MongoDB...${NC}"
# sudo pkill mongod
# echo -e "${GREEN}✅ MongoDB stopped${NC}"

echo ""
echo -e "${GREEN}🎉 All LMS services have been stopped!${NC}"
echo ""
echo -e "${BLUE}📝 Log files are preserved in logs/ directory${NC}"
echo ""
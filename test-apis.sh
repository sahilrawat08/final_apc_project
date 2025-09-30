#!/bin/bash

echo "🧪 Testing LMS APIs..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

ADMIN_BASE="http://localhost:8081"
STUDENT_BASE="http://localhost:8082"

# Function to make API call and check response
test_api() {
    local method=$1
    local url=$2
    local data=$3
    local headers=$4
    local description=$5
    
    echo -e "${BLUE}Testing: $description${NC}"
    echo "URL: $method $url"
    
    if [ "$method" = "POST" ] && [ ! -z "$data" ]; then
        if [ ! -z "$headers" ]; then
            response=$(curl -s -X POST "$url" -H "Content-Type: application/json" -H "$headers" -d "$data")
        else
            response=$(curl -s -X POST "$url" -H "Content-Type: application/json" -d "$data")
        fi
    elif [ "$method" = "GET" ]; then
        if [ ! -z "$headers" ]; then
            response=$(curl -s -H "$headers" "$url")
        else
            response=$(curl -s "$url")
        fi
    fi
    
    if [[ $response == *"\"success\":true"* ]] || [[ $response == *"\"success\": true"* ]]; then
        echo -e "${GREEN}✅ PASS${NC}"
    elif [[ $response == *"error"* ]] || [[ $response == *"Error"* ]]; then
        echo -e "${RED}❌ FAIL${NC}"
        echo "Response: $response"
    else
        echo -e "${YELLOW}⚠️ UNKNOWN - Check response manually${NC}"
        echo "Response: $response"
    fi
    echo "---"
}

# Wait for services to be ready
echo -e "${YELLOW}Waiting for services to be ready...${NC}"
sleep 10

echo ""
echo "=== ADMIN SERVICE TESTS ==="
echo ""

# Test Admin Login
echo -e "${BLUE}🔐 Testing Admin Login${NC}"
admin_login_response=$(curl -s -X POST "$ADMIN_BASE/api/auth/login" \
    -H "Content-Type: application/json" \
    -d '{"username":"admin","password":"admin123"}')

if [[ $admin_login_response == *"\"success\":true"* ]]; then
    echo -e "${GREEN}✅ Admin login successful${NC}"
    admin_token=$(echo $admin_login_response | grep -o '"token":"[^"]*' | cut -d'"' -f4)
    echo "Token received: ${admin_token:0:20}..."
else
    echo -e "${RED}❌ Admin login failed${NC}"
    echo "Response: $admin_login_response"
    exit 1
fi

echo ""

# Test Admin APIs with token
test_api "GET" "$ADMIN_BASE/api/admin/courses" "" "Authorization: Bearer $admin_token" "Get all courses"
test_api "GET" "$ADMIN_BASE/api/admin/students" "" "Authorization: Bearer $admin_token" "Get all students"
test_api "GET" "$ADMIN_BASE/api/admin/assignments" "" "Authorization: Bearer $admin_token" "Get all assignments"
test_api "GET" "$ADMIN_BASE/api/admin/submissions" "" "Authorization: Bearer $admin_token" "Get all submissions"

# Test creating a new course
course_data='{"title":"Test Course","description":"A test course","instructorName":"Dr. Test","maxStudents":25,"status":"ACTIVE"}'
test_api "POST" "$ADMIN_BASE/api/admin/courses" "$course_data" "Authorization: Bearer $admin_token" "Create new course"

echo ""
echo "=== STUDENT SERVICE TESTS ==="
echo ""

# Test Student Login
echo -e "${BLUE}🔐 Testing Student Login${NC}"
student_login_response=$(curl -s -X POST "$STUDENT_BASE/api/auth/login" \
    -H "Content-Type: application/json" \
    -d '{"username":"STU001","password":"password123"}')

if [[ $student_login_response == *"\"success\":true"* ]]; then
    echo -e "${GREEN}✅ Student login successful${NC}"
    student_token=$(echo $student_login_response | grep -o '"token":"[^"]*' | cut -d'"' -f4)
    echo "Token received: ${student_token:0:20}..."
else
    echo -e "${RED}❌ Student login failed${NC}"
    echo "Response: $student_login_response"
    exit 1
fi

echo ""

# Test Student APIs with token
test_api "GET" "$STUDENT_BASE/api/student/courses" "" "Authorization: Bearer $student_token" "Get available courses"
test_api "GET" "$STUDENT_BASE/api/student/courses/enrolled" "" "Authorization: Bearer $student_token" "Get enrolled courses"
test_api "GET" "$STUDENT_BASE/api/student/assignments" "" "Authorization: Bearer $student_token" "Get active assignments"
test_api "GET" "$STUDENT_BASE/api/student/submissions" "" "Authorization: Bearer $student_token" "Get my submissions"
test_api "GET" "$STUDENT_BASE/api/student/profile" "" "Authorization: Bearer $student_token" "Get my profile"

echo ""
echo "=== WEB INTERFACE TESTS ==="
echo ""

# Test web interfaces
echo -e "${BLUE}🌐 Testing Web Interfaces${NC}"

admin_web=$(curl -s -o /dev/null -w "%{http_code}" "$ADMIN_BASE/admin/login")
if [ "$admin_web" = "200" ]; then
    echo -e "${GREEN}✅ Admin web interface accessible${NC}"
else
    echo -e "${RED}❌ Admin web interface not accessible (HTTP $admin_web)${NC}"
fi

student_web=$(curl -s -o /dev/null -w "%{http_code}" "$STUDENT_BASE/student/login")
if [ "$student_web" = "200" ]; then
    echo -e "${GREEN}✅ Student web interface accessible${NC}"
else
    echo -e "${RED}❌ Student web interface not accessible (HTTP $student_web)${NC}"
fi

echo ""
echo -e "${GREEN}🎉 API Testing Complete!${NC}"
echo ""
echo -e "${YELLOW}📊 Access the applications:${NC}"
echo "   Admin Portal:  $ADMIN_BASE/admin/login"
echo "   Student Portal: $STUDENT_BASE/student/login"
echo ""
echo -e "${YELLOW}👥 Default Accounts:${NC}"
echo "   Admin:    admin / admin123"
echo "   Student:  STU001 / password123"
echo "   Student:  STU002 / password123"
echo ""
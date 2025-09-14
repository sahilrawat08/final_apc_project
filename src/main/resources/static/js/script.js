// Custom JavaScript for LMS

document.addEventListener('DOMContentLoaded', function() {
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Initialize popovers
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });

    // Form validation
    const forms = document.querySelectorAll('.needs-validation');
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });

    // Auto-hide alerts
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });

    // Confirm delete actions
    const deleteButtons = document.querySelectorAll('[data-confirm-delete]');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to delete this item?')) {
                e.preventDefault();
            }
        });
    });

    // Auto-refresh notices (if needed)
    if (window.location.pathname === '/') {
        setInterval(function() {
            // Could implement auto-refresh for notices here
        }, 30000); // 30 seconds
    }

    // Dynamic form handling
    const dynamicForms = document.querySelectorAll('[data-dynamic-form]');
    dynamicForms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const submitBtn = form.querySelector('button[type="submit"]');
            if (submitBtn) {
                submitBtn.disabled = true;
                submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Saving...';
            }
        });
    });

    // Search functionality
    const searchInputs = document.querySelectorAll('[data-search]');
    searchInputs.forEach(input => {
        input.addEventListener('input', function() {
            const searchTerm = this.value.toLowerCase();
            const targetTable = document.querySelector(this.dataset.search);
            if (targetTable) {
                const rows = targetTable.querySelectorAll('tbody tr');
                rows.forEach(row => {
                    const text = row.textContent.toLowerCase();
                    if (text.includes(searchTerm)) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }
        });
    });

    // Date/time picker enhancement
    const dateInputs = document.querySelectorAll('input[type="datetime-local"]');
    dateInputs.forEach(input => {
        // Set minimum date to today
        const today = new Date().toISOString().slice(0, 16);
        input.min = today;
    });

    // Grade color coding
    const gradeElements = document.querySelectorAll('[data-grade]');
    gradeElements.forEach(element => {
        const grade = element.dataset.grade;
        if (grade >= 90) {
            element.classList.add('grade-excellent');
        } else if (grade >= 80) {
            element.classList.add('grade-good');
        } else if (grade >= 70) {
            element.classList.add('grade-average');
        } else {
            element.classList.add('grade-poor');
        }
    });

    // Course enrollment toggle
    const enrollmentToggles = document.querySelectorAll('[data-enrollment-toggle]');
    enrollmentToggles.forEach(toggle => {
        toggle.addEventListener('click', function() {
            const studentId = this.dataset.studentId;
            const courseId = this.dataset.courseId;
            const isEnrolled = this.classList.contains('btn-success');
            
            // Toggle visual state
            if (isEnrolled) {
                this.classList.remove('btn-success');
                this.classList.add('btn-outline-success');
                this.textContent = 'Enroll';
            } else {
                this.classList.remove('btn-outline-success');
                this.classList.add('btn-success');
                this.textContent = 'Enrolled';
            }
            
            // Here you would typically make an AJAX call to update enrollment
            // fetch(`/api/enroll/${studentId}/${courseId}`, { method: 'POST' })
        });
    });
});

// Utility functions
function showAlert(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    const container = document.querySelector('.container');
    if (container) {
        container.insertBefore(alertDiv, container.firstChild);
        
        // Auto-hide after 5 seconds
        setTimeout(() => {
            const bsAlert = new bootstrap.Alert(alertDiv);
            bsAlert.close();
        }, 5000);
    }
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
}

function confirmAction(message, callback) {
    if (confirm(message)) {
        callback();
    }
}

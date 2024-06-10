function toggleRoleFields() {
    var role = document.getElementById("role").value;
    var studentFields = document.querySelectorAll(".student-field");
    var teacherFields = document.querySelectorAll(".teacher-field");

    studentFields.forEach(function(field) {
        field.style.display = role === "student" ? "block" : "none";
    });

    teacherFields.forEach(function(field) {
        field.style.display = role === "teacher" ? "block" : "none";
    });
}


document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registration-form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(form);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        console.log(data);

        if (data['role'] === 'ROLE_STUDENT') {
            const user_data = {"email":data['email'], "password":data['password'], "roles":[data['role']]};
            console.log(user_data);

            const student_data = {"firstName":data['firstName'], "lastName":data['lastName'], "email": data['email'], "cellNumber":data['cellNumber']};
            console.log(student_data);

            Promise.all([
                fetch('/myTeacher/users/', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(user_data)
                }),
                fetch('/myTeacher/students/', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(student_data)
                })
            ]).then(responses => {
                return Promise.all(responses.map(response => {
                    const contentType = response.headers.get('Content-Type');
                    if (response.ok && contentType && contentType.includes('application/json')) {
                        return response.json();
                    } else if (response.ok) {
                        return response.text().then(text => ({ text }));
                    } else {
                        return response.text().then(text => Promise.reject(new Error(text)));
                    }
                }));
            }).then(data => {
                console.log('Both requests were successful:', data);
                window.location.pathname= "/student.html";
                // Handle successful responses
            }).catch(error => {
                console.error('Error:', error);
                // Handle errors
            });
        } else {
        }

        
    });
});

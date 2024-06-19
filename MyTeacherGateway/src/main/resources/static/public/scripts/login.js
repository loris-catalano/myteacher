const URL = "http://localhost:8080";

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('login-form');
    form.addEventListener('submit', function(event) {

        event.preventDefault();

        const formData = new FormData(form);

        const email = formData.get("email")
        const password = formData.get("password")
        const role = formData.get("role")

        const base64Credentials = btoa(`${email}:${password}`);

        localStorage.setItem("Authorization", "Basic "+ base64Credentials);
        localStorage.setItem("Email", email);
        localStorage.setItem("Role", role);

        var xhttp = new XMLHttpRequest();
        var url = `${URL}/myTeacher/checkCredentials/`;

        xhttp.open("GET", url, false);
        xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"))
        xhttp.send();

        console.log(xhttp.status)
        if (xhttp.status === 200 && role === 'ROLE_STUDENT') {
            window.location.pathname= "/public/student/homepage.html";
        }else if (xhttp.status === 200 && role === 'ROLE_TEACHER'){
            window.location.pathname= "/public/teacher/homepage.html";
        }else{
            document.getElementById('login-form').reset();
            alert("Hai inserito una email o una password errata.")
        }


    })
});
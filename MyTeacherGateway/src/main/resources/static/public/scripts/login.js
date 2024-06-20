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


        console.log(xhttp.status)
        if (role === 'ROLE_STUDENT') {
            xhttp.open("GET", url+"student", false);
            xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"))
            xhttp.send();
            if(xhttp.status === 200) {
                window.location.pathname = "/public/student/homepage.html";
            }else if(xhttp.status === 403){
                alert("Hai scelto di entrare come studente ma le credenziali appartengono ad un docente. Riprova.")
            }else{
                document.getElementById('login-form').reset();
                alert("Hai inserito una email o una password errata.")
            }
        }else if (role === 'ROLE_TEACHER'){
            xhttp.open("GET", url+"teacher", false);
            xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"))
            xhttp.send();
            if(xhttp.status === 200) {
                window.location.pathname = "/public/teacher/homepage.html";
            }else if(xhttp.status === 403){
                alert("Hai scelto di entrare come docente ma le credenziali appartengono ad uno student. Riprova.")
            }else{
                document.getElementById('login-form').reset();
                alert("Hai inserito una email o una password errata.")
            }
        }else{
            alert("Seleziona un ruolo per effettuare l'autenticazione.")
        }
    })
});
const URL = "http://172.31.6.3:8080";


function getRequest(url){
    var xhttp = new XMLHttpRequest();

    xhttp.open("GET", url, false);
    xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"));
    xhttp.send();

    return xhttp;
}



document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('login-form');
    form.addEventListener('submit', function(event) {
        localStorage.clear()

        event.preventDefault();

        const formData = new FormData(form);

        const email = formData.get("email")
        const password = formData.get("password")
        const role = formData.get("role")

        const base64Credentials = btoa(`${email}:${password}`);

        localStorage.setItem("Authorization", "Basic "+ base64Credentials);
        localStorage.setItem("Email", email);
        localStorage.setItem("Role", role);

        //var xhttp = new XMLHttpRequest();
        var url = `${URL}/myTeacher/checkCredentials/`;


        if (role === 'ROLE_STUDENT') {

            let xhttp = getRequest(url+"student")

            if(xhttp.status === 200) {

                let currentStudentRequest = getRequest(`${URL}/myTeacher/currentUser/student`)
                if( currentStudentRequest.status ===200){
                    student = JSON.parse(currentStudentRequest.responseText);

                    localStorage.setItem("id",student.id);
                    localStorage.setItem("firstName",student.firstName);
                    localStorage.setItem("lastName",student.lastName);
                    localStorage.setItem("lessonBonusPoints",student.lessonBonusPoints);
                    //localStorage.setItem("",student.completedReviews);
                    localStorage.setItem("studentAgenda",student.studentAgenda);
                    localStorage.setItem("cellNumber",student.cellNumber);
                }

                window.location.pathname = "/public/student/homepage.html";
            }else if(xhttp.status === 403){
                alert("Hai scelto di entrare come studente ma le credenziali appartengono ad un docente. Riprova.")
            }else{
                document.getElementById('login-form').reset();
                alert("Hai inserito una email o una password errata.")
            }
        }else if (role === 'ROLE_TEACHER'){

            let xhttp = getRequest(url+"teacher")

            if(xhttp.status === 200) {
                let currentTeacherRequest = getRequest(`${URL}/myTeacher/currentUser/teacher`)
                if( currentTeacherRequest.status ===200){
                    teacher = JSON.parse(currentTeacherRequest.responseText);
                    localStorage.setItem("id",teacher.id);
                    localStorage.setItem("firstName",teacher.firstName);
                    localStorage.setItem("lastName",teacher.lastName);
                    localStorage.setItem("age",teacher.age);
                    localStorage.setItem("subjects",teacher.subjects);
                    localStorage.setItem("resume",teacher.resume);
                    localStorage.setItem("teacherAgenda",teacher.teacherAgenda);
                    localStorage.setItem("cellNumber",teacher.cellNumber);
                }


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
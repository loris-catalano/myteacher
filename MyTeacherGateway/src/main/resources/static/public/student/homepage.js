const URL = "http://localhost:8080";

function fillLessonsAgenda(){
    var xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;

    var url = `${URL}/myTeacher/lessonsAgendas`;
    xhttp.open("GET", url, false);
    xhttp.send();

    if (xhttp.status == "200") {
        console.log("dentro")
        let lessonsAgendas = JSON.parse(xhttp.responseText)
        for(let lesson in lessonsAgendas){

        }
    }
    else
        return null;


    //document.getElementById("booksList").innerHTML
}


function lessonHtml(lesson, teacher){
    return `
    <div class="lesson-element">
    <p><i>Docente</i>: ${teacher.firstName} ${teacher.lastName}</p>
    <p><i>Materia</i>: ${lesson.subject}<\p>
    <p><i>Prezzo</i>: € ${lesson.price}<\p>
    <p><i>Data</i>: ${lesson.startLesson}<\p>
    <p><i>Durata</i>: ${lesson.duration} ora<\p>
    <button type="submit" class="round-button" id="lesson-${lesson.id}">Prenota</button>
    </div>
    `
}




function getRequest(url){
    var xhttp = new XMLHttpRequest();

    xhttp.open("GET", url, false);
    xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"));
    xhttp.send();

    return xhttp;
}

function logout(){
    localStorage.clear()
    alert("Sei uscito dall'account corrente. Verrai reinderizzato alla pagina di accesso.")
    window.location.pathname = "/public/login.html";
}


function fillAvailableLessons(){
    var xhttp = new XMLHttpRequest();
    var lessonsurl = `${URL}/myTeacher/lessons/`;
    var teacherurl = `${URL}/myTeacher/teachers/`;

    let xhrLessons = getRequest(lessonsurl)

    if (xhrLessons.status === 200) {
        let lessons = JSON.parse(xhrLessons.responseText)
        for(i in lessons){
            let lesson = lessons[i]
            console.log(lesson)
            let xhrTeacher = getRequest(teacherurl + lesson.teacherId);
            let teacher = JSON.parse(xhrTeacher.responseText)

            document.getElementById("availableLessonsList").innerHTML += lessonHtml(lessons[i], teacher);
        }
    }
    else
        return null;
}





document.addEventListener('DOMContentLoaded', function() {
    if(localStorage.getItem("Authorization") !== null) {
        fillAvailableLessons();
    }


    document.querySelectorAll('.round-button').forEach(button => {
        button.addEventListener('click', function(){
            bookLesson(button.id)
        });
    });
})



function bookLesson(id){
    lessonId = id.split("-")[1]
    studentId = localStorage.getItem("id")


    url = URL + "/myTeacher/lessons/"+lessonId+"/student/"+studentId

    req = getRequest(url)
    if(req.status === 200){
        console.log("Prenotato")
    }

}


document.getElementById("logout").addEventListener("click", logout)




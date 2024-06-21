const URL = "http://localhost:8080";

const lessonsUrl = `${URL}/myTeacher/lessons/`;
const teacherUrl = `${URL}/myTeacher/teachers/`;
const lessonsAgendaUrl = `${URL}/myTeacher/lessonsAgendas/`;


function availableLessonHTML(lesson, teacher){
    return `
    <div class="lesson-element">
    <p><i>Docente</i>: ${teacher.firstName} ${teacher.lastName}</p>
    <p><i>Materia</i>: ${lesson.subject}<\p>
    <p><i>Prezzo</i>: € ${lesson.price}<\p>
    <p><i>Data</i>: ${lesson.startLesson}<\p>
    <p><i>Durata</i>: ${lesson.duration} ora<\p>
    <button type="submit" class="book-button" id="lesson-${lesson.id}">Prenota</button>
    </div>
    `
}

function agendaLessonHTML(lesson, teacher){
    return `
    <div class="lesson-element">
    <p><i>Docente</i>: ${teacher.firstName} ${teacher.lastName}</p>
    <p><i>Materia</i>: ${lesson.subject}<\p>
    <p><i>Prezzo</i>: € ${lesson.price}<\p>
    <p><i>Data</i>: ${lesson.startLesson}<\p>
    <p><i>Durata</i>: ${lesson.duration} ora<\p>
    <button type="submit" class="cancel-button" id="lesson-${lesson.id}">Disdici</button>
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

function putRequest(url, body){
    var xhttp = new XMLHttpRequest();

    xhttp.open("PUT", url, false);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"));
    xhttp.send(body);

    return xhttp;
}

function logout(){
    localStorage.clear()
    alert("Sei uscito dall'account corrente. Verrai reinderizzato alla pagina di accesso.")
    window.location.pathname = "/public/login.html";
}



function fillLessonsAgenda(){

    const lessonsAgendaId = localStorage.getItem("studentAgenda")
    const lessonsAgenda = JSON.parse( getRequest(lessonsAgendaUrl+lessonsAgendaId).responseText )
    console.log(lessonsAgenda)

    console.log(lessonsAgenda['lessons'])

    const lessonsIds = lessonsAgenda['lessons']

    for(i in lessonsIds){
        currLessonId = lessonsIds[i]

        let lesson = JSON.parse( getRequest(lessonsUrl+currLessonId).responseText )
        let teacher = JSON.parse(getRequest(teacherUrl + lesson.teacherId).responseText)

        document.getElementById("agendaLessonsList").innerHTML += agendaLessonHTML(lesson, teacher);
    }

}

function fillAvailableLessons(){

    let xhrLessons = getRequest(lessonsUrl) //get all lessons

    if (xhrLessons.status === 200) {
        let lessons = JSON.parse(xhrLessons.responseText)
        for(i in lessons){
            let lesson = lessons[i]
            if (lesson.studentId != null) continue; //skip booked lessons

            let teacher = JSON.parse(getRequest(teacherUrl + lesson.teacherId).responseText)

            document.getElementById("availableLessonsList").innerHTML += availableLessonHTML(lessons[i], teacher);
        }
    }
    else
        return null;
}





document.addEventListener('DOMContentLoaded', function() {
    if(localStorage.getItem("Authorization") !== null) {
        fillLessonsAgenda();
        fillAvailableLessons();
    }



    document.querySelectorAll('.cancel-button').forEach(button => {
        button.addEventListener('click', function(){
            cancelLesson(button.id)
        });
    });
    document.querySelectorAll('.book-button').forEach(button => {
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
        alert("Prenotazione effettuata!")
        const bookButton = document.getElementById('lesson-'+lessonId);

        bookButton.innerText = 'Prenotato';
        bookButton.style.backgroundColor = 'grey';
        bookButton.disabled = true;
        bookButton.style.cursor = 'not-allowed';
    }
}

function cancelLesson(buttonId){
    lessonId = buttonId.split("-")[1]
    studentId = localStorage.getItem("id")

    lesson = JSON.parse( getRequest(lessonsUrl+lessonId).responseText )
    lesson.studentId = null

    //TODO Pulire anche la lessonagenda, altrimenti non funziona

    req = putRequest(lessonsUrl, JSON.stringify(lesson))
    if(req.status === 200){
        console.log("Cancellato")
        alert("Cancellazione effettuata. Troppe cancellazioni porteranno al ban dell'account.")

        location.reload() //refresh the page to make the current lesson go away from agenda
    }
}


document.getElementById("logout").addEventListener("click", logout)




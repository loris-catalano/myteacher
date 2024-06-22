const URL = "http://localhost:8080";

const lessonsUrl = `${URL}/myTeacher/lessons/`;
const teacherUrl = `${URL}/myTeacher/teachers/`;
const studentUrl = `${URL}/myTeacher/students/`;
const reviewUrl = `${URL}/myTeacher/reviews/`;

const lessonsAgendaUrl = `${URL}/myTeacher/lessonsAgendas/`;


function availableLessonHTML(lesson, teacher){
    return `
    <div class="lesson-element">
    <p><i>Docente</i>: ${teacher.firstName} ${teacher.lastName}</p>
    <p><i>Materia</i>: ${lesson.subject}</p>
    <p><i>Prezzo</i>: € ${lesson.price}</p>
    <p><i>Data</i>: ${lesson.startLesson}</p>
    <p><i>Durata</i>: ${lesson.duration} ora</p>
    <button type="submit" class="book-button" id="lesson-${lesson.id}" onclick="bookLesson(id)">Prenota</button>
    </div>
    `
}

function agendaLessonHTML(lesson, teacher){
    return `
    <div class="lesson-element">
    <p><i>Docente</i>: ${teacher.firstName} ${teacher.lastName}</p>
    <p><i>Materia</i>: ${lesson.subject}</p>
    <p><i>Data</i>: ${lesson.startLesson}</p>
    <p><i>Durata</i>: ${lesson.duration} ore</p>
    <button type="submit" class="cancel-button" id="lesson-${lesson.id}" onclick="cancelLesson(id)">Disdici</button>
    </div>
    `
}

function doneLessonHTML(lesson, teacher){
    return `
    <div class="lesson-element">
    <p><i>Docente</i>: ${teacher.firstName} ${teacher.lastName}</p>
    <p><i>Materia</i>: ${lesson.subject}</p>
    <p><i>Prezzo</i>: € ${lesson.price}</p>
    <p><i>Data</i>: ${lesson.startLesson}</p>
    <button type="submit" class="make-review-button" id="lesson-${lesson.id}" onclick="showReviewPopup()">Recensisci</button>
    </div>
    `
}


function reviewHTML(review, lesson, teacher){
    return `
    <div class="review-element">
    
    <p><i>Docente</i>: ${teacher.firstName} ${teacher.lastName}</p>
    <p><i>Materia</i>: ${lesson.subject}</p>
    <p><i>Data</i>: ${lesson.startLesson}</p>
    
    
    <p><i>Stelle</i>: ${review.stars}</p>
    <p><i>Titolo</i>: ${review.title}</p>
    <p><i>Descrizione</i>: ${review.body}</p>
    <p><i>Data creazione</i>: ${review.creationTime}</p>
    
    <button type="submit" class="cancel-button" id="lesson-${lesson.id}">Disdici</button>
    
    <button onclick="editReview()">Modifica</button>
    <button onclick="deleteReview()">Elimina</button>
    
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

function postRequest(url, body){
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"));
    xhttp.send(body);

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


function fillDoneLessons(){
    var lessons = JSON.parse(getRequest(lessonsUrl).responseText)
    const now = new Date()

    for(i in lessons){
        lesson = lessons[i]

        if(Date.parse(lesson.startLesson) > now  || lesson.studentId.toString() !== localStorage.getItem("id")) continue; //Pass on lesson not done yet and on other students lessons

        let teacher = JSON.parse(getRequest(teacherUrl + lesson.teacherId).responseText)

        document.getElementById("doneLessonsList").innerHTML += doneLessonHTML(lesson, teacher);
    }
}





document.addEventListener('DOMContentLoaded', function() {
    if(localStorage.getItem("Authorization") !== null) {
        fillLessonsAgenda();
        fillAvailableLessons();
        fillDoneLessons();
    }

})

//tracks the X of the review popup
document.querySelector('.closeBtn').addEventListener('click', function() {
    document.getElementById('reviewPopup').style.display = 'none';
});


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
    const lessonId = buttonId.split("-")[1]
    const studentId = localStorage.getItem("id")

    // Remove student from lesson
    const lesson = JSON.parse( getRequest(lessonsUrl+lessonId).responseText )
    lesson.studentId = null
    const req = putRequest(lessonsUrl, JSON.stringify(lesson))

    // Remove lesson from student agenda
    const lessonsAgenda = JSON.parse( getRequest(lessonsAgendaUrl+localStorage.getItem("studentAgenda")).responseText )
    let lessons = lessonsAgenda['lessons']
    lessons = lessons.filter(function(less) {
        return less.toString() !== lessonId;
    });
    lessonsAgenda['lessons']=lessons;
    const req2 = putRequest(lessonsAgendaUrl, JSON.stringify(lessonsAgenda))


    if(req.status === 200 && req2.status === 200){
        console.log("Cancellato")
        alert("Cancellazione effettuata. Troppe cancellazioni porteranno al ban dell'account.")
        location.reload() //refresh the page to make the current lesson go away from agenda
    }
}

function showReviewPopup(){
    document.getElementById('reviewPopup').style.display = 'block';
}

function makeReview(){

    const stars = document.getElementById('stars').value;
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;


}



document.getElementById("logout").addEventListener("click", logout)




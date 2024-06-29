const URL = "http://172.31.6.3:8080";

const lessonsUrl = `${URL}/myTeacher/lessons/`;
const teacherUrl = `${URL}/myTeacher/teachers/`;
const studentUrl = `${URL}/myTeacher/students/`;
const reviewUrl = `${URL}/myTeacher/reviews/`;

const lessonsAgendaUrl = `${URL}/myTeacher/lessonsAgendas/`;



function agendaLessonHTML(lesson, student){
    let d = lesson.startLesson

    const date = d.split('T')[0]
    const time = d.split('T')[1].substring(0,5)

    if(student.status){ //if he has "status" field it is an error (there is no student)
        return `
        <div class="lesson-element">
        <p><strong>Studente</strong>: Nessuno studente prenotato</p>
        <p><strong>Materia</strong>: ${lesson.subject}</p>
        <p><strong>Data</strong>: ${date} ${time}</p>
        <p><strong>Durata</strong>: ${lesson.duration} ore</p>
        <button type="submit" class="cancel-button" id="lesson-${lesson.id}" onclick="cancelLesson(id)">Cancella</button>
        </div>
        `
    }else {
        return `
        <div class="lesson-element">
        <p><strong>Studente</strong>: ${student.firstName} ${student.lastName}</p>
        <p><strong>Materia</strong>: ${lesson.subject}</p>
        <p><strong>Data</strong>: ${date} ${time}</p>
        <p><strong>Durata</strong>: ${lesson.duration} ore</p>
        <button type="submit" class="cancel-button" id="lesson-${lesson.id}" onclick="cancelLesson(id)">Disdici</button>
        </div>
        `
    }


}

function doneLessonHTML(lesson, student){
    let d = lesson.startLesson

    const date = d.split('T')[0]
    const time = d.split('T')[1].substring(0,5)

    return `
    <div class="lesson-element">
    <p><strong>Studente</strong>: ${student.firstName} ${student.lastName}</p>
    <p><strong>Materia</strong>: ${lesson.subject}</p>
    <p><strong>Costo</strong>: € ${lesson.price}</p>
    <p><strong>Data</strong>: ${date} ${time}</p>
    <!--<button type="submit" class="make-review-button" id="lesson-${lesson.id}" onclick="showReviewPopup(id)">Recensisci</button>-->
    </div>
    `
}

function reviewHTML(review, student){
    const d = review.creationTime

    const date = d.split('T')[0]
    const time = d.split('T')[1].substring(0,5)


    return `
    <div class="review-element">
    <div class="review-header">
        <p class="review-student"><strong>Studente</strong>: ${student.firstName} ${student.lastName}</p>
        <p class="review-stars">
            <strong>Stelle</strong>: ${review.stars} <span class="stars">★</span>
        </p>
    </div>
    <h3 class="review-title">${review.title}</h3>
    <p class="review-body">${review.body}</p>
    <p class="review-date"><strong>Data creazione</strong>: ${date} ${time}</p>
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
function deleteRequest(url){
    var xhttp = new XMLHttpRequest();

    xhttp.open("DELETE", url, false);
    xhttp.setRequestHeader("Authorization", localStorage.getItem("Authorization"));
    xhttp.send();

    return xhttp;
}


function createLesson(){

    const teacherId = localStorage.getItem("id")

    const subject = document.getElementById('subject').value;
    const price = document.getElementById('price').value;
    const duration = document.getElementById('duration').value;

    const startLessonDate = document.getElementById('startLessonDate').value;
    const startLessonTime = document.getElementById('startLessonTime').value;
    const startLesson =  startLessonDate+"T"+startLessonTime+":00.000Z"

    let body = {
        teacherId: teacherId,
        subject: subject,
        price: price,
        duration: duration,
        startLesson:startLesson
    };

    body = JSON.stringify(body);

    const req = postRequest(lessonsUrl, body)

    if(req.status === 201){
        alert("Lezione creata.")
    }else{
        alert("C'è stato un errore")
    }

    location.reload()
}


function logout(){
    localStorage.clear()
    alert("Sei uscito dall'account corrente. Verrai reinderizzato alla pagina di accesso.")
    window.location.pathname = "/public/login.html";
}


function fillLessonsAgenda(){

    const lessonsAgendaId = localStorage.getItem("teacherAgenda")
    const lessonsAgenda = JSON.parse( getRequest(lessonsAgendaUrl+lessonsAgendaId).responseText )

    const lessonsIds = lessonsAgenda['lessons']
    const now = new Date()

    for(i in lessonsIds){
        currLessonId = lessonsIds[i]

        let lesson = JSON.parse( getRequest(lessonsUrl+currLessonId).responseText )

        if(Date.parse(lesson.startLesson) < now) continue; //Pass on lesson ended


        let student = JSON.parse(getRequest(studentUrl + lesson.studentId).responseText)

        document.getElementById("agendaLessonsList").innerHTML += agendaLessonHTML(lesson, student);
    }

}

function fillReviews(){
    const reviews = JSON.parse(getRequest(reviewUrl).responseText)
    for(i in reviews){
        review = reviews[i]

        if(review.teacherId.toString() !== localStorage.getItem("id")) continue;

        student = JSON.parse(getRequest(studentUrl+review.studentId).responseText)
        document.getElementById("reviewsList").innerHTML += reviewHTML(review, student);
    }

}

function fillDoneLessons(){
    var lessons = JSON.parse(getRequest(lessonsUrl).responseText)
    const now = new Date()

    for(i in lessons){
        lesson = lessons[i]

        if(Date.parse(lesson.startLesson) > now  || lesson.teacherId.toString() !== localStorage.getItem("id")) continue; //Pass on lesson not done yet and on other lessons
        if(lesson.studentId == null)continue;   // if lesson is not booked by any student
        let student = JSON.parse(getRequest(studentUrl + lesson.studentId).responseText)

        document.getElementById("doneLessonsList").innerHTML += doneLessonHTML(lesson, student);
    }
}



//tracks the X of the review popup
function hideLessonPopup(){
    document.getElementById('lessonPopup').style.display = 'none';
}


function cancelLesson(buttonId){
    const lessonId = buttonId.split("-")[1]
    const req = deleteRequest(lessonsUrl + lessonId)


    if(req.status === 204){
        console.log("Cancellato")
        alert("Cancellazione effettuata. Troppe cancellazioni porteranno al ban dell'account.")
        location.reload() //refresh the page to make the current lesson go away from agenda
    }
}

function showLessonPopup(id){
    document.getElementById('lessonPopup').style.display = 'block';
}


document.getElementById("logout-href").addEventListener("click", logout)


document.addEventListener('DOMContentLoaded', function() {
    topButton = document.getElementById("student-dropdown")

    if(localStorage.getItem("Authorization") !== null && localStorage.getItem("Role") === "ROLE_TEACHER") {
        topButton.innerText = `🧑‍🏫 ${localStorage.getItem("firstName")} ${localStorage.getItem("lastName")}`

        fillLessonsAgenda();
        fillDoneLessons();
        fillReviews();
    }else{
        document.querySelector(".dropdown-content").style.display = 'none'
        topButton.href = '../login.html'
    }
})


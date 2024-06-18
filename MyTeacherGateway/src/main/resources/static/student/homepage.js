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


function teacherHtml(teacher){
    return `
    <div class="teacher-element">
    <p><i>Nome</i>: ${teacher.firstName} ${teacher.lastName}<\p>
    <p><i>Materie</i>: ${teacher.subjects}<\p>
    <p><i>Età</i>: ${teacher.age}<\p>
    <button type="submit" formaction="">Book a lesson</button>
    </div>
    `
}

function fillTeachers(){

    console.log("dentro 1")
    var xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;


    var url = `${URL}/myTeacher/teachers/`;
    xhttp.open("GET", url, false);
    xhttp.send();

    if (xhttp.status == "200") {
        console.log("dentro 2")
        let teachers = JSON.parse(xhttp.responseText)
        console.log(teachers)
        for(i in teachers){
            document.getElementById("teachersList").innerHTML += teacherHtml(teachers[i]);
        }
    }
    else
        return null;
}



document.addEventListener('DOMContentLoaded', function() {
    fillTeachers();
})

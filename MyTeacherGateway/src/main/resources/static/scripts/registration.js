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

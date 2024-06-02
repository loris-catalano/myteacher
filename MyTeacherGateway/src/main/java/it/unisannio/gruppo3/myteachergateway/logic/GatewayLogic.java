package it.unisannio.gruppo3.myteachergateway.logic;

import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.entities.Review;
import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.entities.Teacher;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public interface GatewayLogic {
     Student getStudent(Long studentId);
     Response createStudent(Student student);
     Teacher getTeacher(Long teacherId);

     Response createTeacher(Teacher teacher);

     Response createReview(Review review);

     Review getReview(Long id);

     Lesson getLesson(Long id);

     Response createLesson(Lesson lesson);
}

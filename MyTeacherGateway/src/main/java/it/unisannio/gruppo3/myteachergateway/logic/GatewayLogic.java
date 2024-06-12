package it.unisannio.gruppo3.myteachergateway.logic;

import it.unisannio.gruppo3.entities.*;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public interface GatewayLogic {
     Student getStudent(Long studentId);
     Response createStudent(Student student);

     Teacher getTeacher(Long teacherId);
     Response createTeacher(Teacher teacher);

     Response createReview(Review review);
     Review getReview(Long id);
     Response updateReview(Review review);
     Response deleteReview(Long id);

     Lesson getLesson(Long id);
     Response createLesson(Lesson lesson);

     LessonsAgenda getLessonsAgenda(Long id);
     Response createLessonsAgenda(LessonsAgenda lessonsAgenda);
     Response updateLessonsAgenda(LessonsAgenda lessonsAgenda);

     Response createUser(User user);

     Payment getPayment(Long id);
     Response createPayment(Payment payment);


     Response payLesson(Long lessonId, Long studentId);

     ArrayList<LessonsAgenda> getAllLessonsAgendas();
}

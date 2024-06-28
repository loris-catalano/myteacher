package it.unisannio.gruppo3.myteachergateway.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unisannio.gruppo3.entities.*;
import it.unisannio.gruppo3.myteachergateway.GsonTypeAdapter.InstantTypeAdapter;
import jakarta.ws.rs.core.UriBuilder;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.io.IOException;
import java.net.URI;
import java.sql.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;


public class GatewayLogicImpl implements GatewayLogic  {
    public static final String SERVER_IP = "localhost";

    private static final String STUDENT_SERVICE_URL = "http://"+SERVER_IP+":8081/student/studentService/";
    private static final String TEACHER_SERVICE_URL = "http://"+SERVER_IP+":8082/teacher/teacherService/";
    private static final String REVIEW_SERVICE_URL = "http://"+SERVER_IP+":8083/review/reviewService/";
    private static final String LESSON_SERVICE_URL = "http://"+SERVER_IP+":8084/lesson/lessonService/";
    private static final String PAYMENT_SERVICE_URL = "http://"+SERVER_IP+":8085/payment/paymentService/";
    private static final String LESSONS_AGENDA_SERVICE_URL = "http://"+SERVER_IP+":8086/lessonsAgenda/lessonsAgendaService/";
    private static final String CHAT_SERVICE_URL = "http://"+SERVER_IP+":8088/chat/chatService";
    private static final String USER_SERVICE_URL = "http://"+SERVER_IP+":8089/user/userService/";

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_DELETE = "DELETE";


    private final OkHttpClient client;

    public GatewayLogicImpl(){
        client = new OkHttpClient();
    }


    @Override
    public Student getStudent(Long studentId) {
        try {
            String URL = String.format(STUDENT_SERVICE_URL + studentId);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 )return null;

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();


            Student student = gson.fromJson(responseBody, Student.class);
            return student;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response createStudent(Student student) {
        try {
            // Creates an empty lessons agenda and gets the id by looking at its location
            jakarta.ws.rs.core.Response res = createLessonsAgenda(new LessonsAgenda(new ArrayList<>()));
            String location = res.getLocation().toString();
            String[] segments = location.split("/");
            Long lessonsAgendaId = Long.parseLong(segments[segments.length - 1]);

            student.setStudentAgenda(getLessonsAgenda(lessonsAgendaId).getId());

            String URL = String.format(STUDENT_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            String json = gson.toJson(student);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Teacher getTeacher(Long teacherId) {
        try {
            String URL = String.format(TEACHER_SERVICE_URL + teacherId);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            Teacher teacher = gson.fromJson(responseBody, Teacher.class);
            return teacher;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        try {
            String URL = String.format(TEACHER_SERVICE_URL);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return (ArrayList<Teacher>) gson.fromJson(responseBody, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response createTeacher(Teacher teacher) {
        try {
            // Creates an empty lessons agenda and gets the id by looking at its location
            jakarta.ws.rs.core.Response res = createLessonsAgenda(new LessonsAgenda(new ArrayList<>()));
            String location = res.getLocation().toString();
            String[] segments = location.split("/");
            Long lessonsAgendaId = Long.parseLong(segments[segments.length - 1]);

            teacher.setTeacherAgenda(getLessonsAgenda(lessonsAgendaId).getId());


            String URL = String.format(TEACHER_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            String json = gson.toJson(teacher);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        try {
            String URL = String.format(REVIEW_SERVICE_URL + reviewId);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return gson.fromJson(responseBody, Review.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Review> getAllReviews() {
        try {
            String URL = String.format(REVIEW_SERVICE_URL);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    //    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return (ArrayList<Review>) gson.fromJson(responseBody, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response createReview(Review review) {
        try {
            String URL = String.format(REVIEW_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            String json = gson.toJson(review);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response updateReview(Review review) {
        try {
            String URL = String.format(REVIEW_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            String json = gson.toJson(review);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .put(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 200 )return null;

            return jakarta.ws.rs.core.Response.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response deleteReview(Long id) {
        try {
            String URL = String.format(REVIEW_SERVICE_URL + id);

            Request request = new Request.Builder()
                    .url(URL)
                    .delete()
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 204 && response.code() != 202 )return null;

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return jakarta.ws.rs.core.Response.noContent().build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Lesson getLesson(Long lessonId) {
        try {
            String URL = String.format(LESSON_SERVICE_URL + lessonId);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return gson.fromJson(responseBody, Lesson.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public jakarta.ws.rs.core.Response updateLesson(Lesson lesson){
        try {
            String URL = String.format(LESSON_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            String json = gson.toJson(lesson);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .put(body)
                    .build();

            System.out.println("body: "+body+"\nreq: "+ request.body().toString());


            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            if (response.code() != 200 )return null;

            return jakarta.ws.rs.core.Response.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Lesson> getAllLessons() {
        try {
            String URL = String.format(LESSON_SERVICE_URL);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    //    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return (ArrayList<Lesson>) gson.fromJson(responseBody, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response createLesson(@NotNull Lesson lesson) {
        try {

            String URL = String.format(LESSON_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            String json = gson.toJson(lesson);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();


            Response response = client.newCall(request).execute();

            //Get the teacher agenda from the id of the teacher of the lesson
            LessonsAgenda teacherAgenda = getLessonsAgenda(getTeacher(lesson.getTeacherId()).getTeacherAgenda());
            //Add this lesson to the teacher agenda
            ArrayList<Long> lessons = teacherAgenda.getLessons();

            //get id of the lesson from the path
            Long id = Long.parseLong(response.header("location").toString().split("/")[5]);
            lessons.add(id);
            //Update teacher agenda with the new lesson
            teacherAgenda.setLessons(lessons);
            updateLessonsAgenda(teacherAgenda);


            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response deleteLesson(Long id) {
        try {
            Lesson lesson = getLesson(id);
            Teacher teacher = getTeacher(lesson.getTeacherId());
            Student student = getStudent(lesson.getStudentId());

            //Update teacher agenda
            LessonsAgenda teacherAgenda = getLessonsAgenda(teacher.getTeacherAgenda());
            ArrayList<Long> lessonsIds = teacherAgenda.getLessons();
            lessonsIds.remove(id);
            teacherAgenda.setLessons(lessonsIds);
            updateLessonsAgenda(teacherAgenda);

            //Update student agenda if there was a student booked for the lesson
            if(student!=null){
                LessonsAgenda studentAgenda = getLessonsAgenda(student.getStudentAgenda());
                ArrayList<Long> lessIds = studentAgenda.getLessons();
                lessIds.remove(id);
                studentAgenda.setLessons(lessIds);
                updateLessonsAgenda(studentAgenda);
            }

            //Then delete also lesson

            String URL = String.format(LESSON_SERVICE_URL + id);


            Request request = new Request.Builder()
                    .url(URL)
                    .delete()
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 204 && response.code() != 202 )return null;

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return jakarta.ws.rs.core.Response.noContent().build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LessonsAgenda getLessonsAgenda(Long lessonsAgendaid){
        try {
            String URL = String.format(LESSONS_AGENDA_SERVICE_URL + lessonsAgendaid);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                //    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return gson.fromJson(responseBody, LessonsAgenda.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public jakarta.ws.rs.core.Response createLessonsAgenda(LessonsAgenda lessonsAgenda){
        try {
            String URL = String.format(LESSONS_AGENDA_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .create();

            String json = gson.toJson(lessonsAgenda);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public jakarta.ws.rs.core.Response updateLessonsAgenda(LessonsAgenda lessonsAgenda){
        try {
            String URL = String.format(LESSONS_AGENDA_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .create();

            String json = gson.toJson(lessonsAgenda);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .put(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 200 )return null;

            return jakarta.ws.rs.core.Response.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response createUser(User user) {
        try {
            String URL = String.format(USER_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            String json = gson.toJson(user);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Payment getPayment(Long id) {
        try {
            String URL = String.format(PAYMENT_SERVICE_URL + id);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .create();

            return gson.fromJson(responseBody, Payment.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response createPayment(Payment payment) {
        try {
            String URL = String.format(PAYMENT_SERVICE_URL);

            Gson gson = new GsonBuilder()
                    .create();

            String json = gson.toJson(payment);
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public jakarta.ws.rs.core.Response bookLesson(Long lessonId, Long studentId) {

        Lesson lesson = getLesson(lessonId);
        Student student = getStudent(studentId);

        lesson.setStudentId(studentId);

        jakarta.ws.rs.core.Response res = updateLesson(lesson);

        System.out.println("updateLesson response: " + res.getStatus());
        if(res.getStatus() != 200) return null;

        // Add lesson to student agenda

        LessonsAgenda studentAgenda = getLessonsAgenda(student.getStudentAgenda());
        studentAgenda.getLessons().add(lessonId);
        updateLessonsAgenda(studentAgenda);

        return jakarta.ws.rs.core.Response.ok().build();
    }

    @Override
    public ArrayList<LessonsAgenda> getAllLessonsAgendas() {
        try {
            String URL = String.format(LESSONS_AGENDA_SERVICE_URL);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    //    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return (ArrayList<LessonsAgenda>) gson.fromJson(responseBody, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public Student getCurrentStudent() {
        String email = getCurrentUserEmail();

        try {
            String URL = String.format(STUDENT_SERVICE_URL + "email/" + email);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return gson.fromJson(responseBody, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Teacher getCurrentTeacher() {
        String email = getCurrentUserEmail();

        try {
            String URL = String.format(TEACHER_SERVICE_URL + "email/" + email);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }

            String responseBody = response.body().string();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            return gson.fromJson(responseBody, Teacher.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}

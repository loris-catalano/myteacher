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


import java.io.IOException;
import java.net.URI;
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
    public jakarta.ws.rs.core.Response createTeacher(Teacher teacher) {
        try {
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


    @Override
    public jakarta.ws.rs.core.Response createLesson(Lesson lesson) {
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

            if (response.code() != 201 )return null;

            URI uri = UriBuilder.fromPath(response.header("location")).build();
            return jakarta.ws.rs.core.Response.created(uri).build();
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
}

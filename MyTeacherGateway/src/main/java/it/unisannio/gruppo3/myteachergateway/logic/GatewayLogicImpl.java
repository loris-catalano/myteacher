package it.unisannio.gruppo3.myteachergateway.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.entities.Teacher;
import it.unisannio.gruppo3.myteachergateway.GsonTypeAdapter.InstantTypeAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class GatewayLogicImpl implements GatewayLogic  {
    private final String studentAddress;
    private final String teacherAddress;
    public GatewayLogicImpl() {
        String studentHost = System.getenv("STUDENT_HOST");
        String studentPort = System.getenv("STUDENT_PORT");
        if (studentHost == null) {
            studentHost = "localhost";
        }
        if (studentPort == null) {
            studentPort = "8081";
        }
        studentAddress = "http://" + studentHost + ":" + studentPort;
        String teacherHost = System.getenv("TEACHER_HOST");
        String teacherPort = System.getenv("TEACHER_PORT");
        if (teacherHost == null) {
            teacherHost = "localhost";
        }
        if (teacherPort == null) {
            teacherPort = "8082";
        }
        teacherAddress = "http://" + teacherHost + ":" + teacherPort;
    }

    @Override
    public Student getStudent(Long studentId) {
        try {
            String URL = String.format(studentAddress + "/student/studentService/" + studentId);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            // Stampa il corpo della risposta
            String responseBody = response.body().string();

            if (response.code() != 200 ){
                return null;
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();


            System.out.println(responseBody);
            Student student = gson.fromJson(responseBody, Student.class);
            return student;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public Teacher getTeacher(Long teacherId) {
        try {
            String URL = String.format(teacherAddress + "/teacher/teacherService/" + teacherId);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            // Stampa il corpo della risposta
            String responseBody = response.body().string();

            if (response.code() != 200 ){
                return null;
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                    .create();

            Teacher teacher = gson.fromJson(responseBody, Teacher.class);
            return teacher;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

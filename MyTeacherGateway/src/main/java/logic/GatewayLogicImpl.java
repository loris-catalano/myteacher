package logic;

import com.google.gson.Gson;
import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.entities.Teacher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GatewayLogicImpl implements GatewayLogic  {
    private final String myTeacherAddress;

    public GatewayLogicImpl() {
        String myTeacherHost = System.getenv("MYTEACHER_HOST");
        String myTeacherPort = System.getenv("MYTEACHER_PORT");
        if (myTeacherHost == null) {
            myTeacherHost = "localhost";
        }
        if (myTeacherPort == null) {
            myTeacherPort = "8085";
        }
        myTeacherAddress = "http://" + myTeacherHost + ":" + myTeacherPort;
    }

    @Override
    public Student getStudent(Long studentId) {
        try {
            String URL = String.format(myTeacherAddress + "/student/studentService/" + studentId);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }
            Gson gson = new Gson();
            String body = response.body().string();
            Student student = gson.fromJson(body, Student.class);
            return student;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public Teacher getTeacher(Long teacherId) {
        try {
            String URL = String.format(myTeacherAddress + "/teacher/teacherService/" + teacherId);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200 ){
                return null;
            }
            Gson gson = new Gson();
            String body = response.body().string();
            Teacher teacher= gson.fromJson(body, Teacher.class);
            return teacher;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

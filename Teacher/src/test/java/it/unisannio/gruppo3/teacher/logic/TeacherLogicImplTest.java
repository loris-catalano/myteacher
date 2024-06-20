package it.unisannio.gruppo3.teacher.logic;

import it.unisannio.gruppo3.entities.Teacher;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherLogicImplTest {
    static boolean init=false;
    static TeacherLogic teacherLogic;
    static Long th1Id;
    static Teacher th1;

    @BeforeAll
    public static void setup(){
        if(!init) {
            teacherLogic = new TeacherLogicImpl();
            th1Id = teacherLogic.getNextId();
            assertTrue(th1Id > 0);
            th1 = new Teacher(th1Id, "Francesco","Fuccio",40,false,null,null,12.0,192.0,null,"basiDiDati",null,"fra@gmail.com","1981288");
            Long TeacherId = teacherLogic.createTeacher(th1);
            assertEquals(TeacherId, th1Id);
            init = true;
        }
    }


    @Test
    @Order(1)
    void createTeacher() {
        Long nextId = teacherLogic.getNextId();
        assertTrue(nextId > 0);
        assertEquals(nextId, th1Id+1); Teacher thCreated = new Teacher(nextId, "Michele","Fuccio",40,false,null,null,12.0,192.0,null,"basiDiDati",null,"michele@gmail.com","1981288");

        Long newTeacherId = teacherLogic.createTeacher(thCreated);
        assertEquals(newTeacherId, th1Id+1);
        teacherLogic.deleteTeacher(newTeacherId);
    }

    @Test
    @Order(2)
    void getTeacher() {
        Teacher teacher = teacherLogic.getTeacher(th1Id);
        assertEquals(th1,teacher);
    }


    @Test
    @Order(3)
    void getAllTeachers() {
        ArrayList<Teacher> allTeachers = teacherLogic.getAllTeachers();
        assertFalse(allTeachers.isEmpty());
    }

    @Test
    @Order(4)
    void updateTeacher() {
        th1.setFirstName("Loris");
        Teacher teacher = teacherLogic.updateTeacher(th1);
        assertEquals(th1, teacher);
    }

    @Test
    @Order(5)
    void deleteTeacher() {
        teacherLogic.deleteTeacher(th1Id);
    }

}


package it.unisannio.gruppo3.teacher.persistence;

import it.unisannio.gruppo3.entities.Teacher;

import it.unisannio.gruppo3.teacher.persistance.TeacherDAO;
import it.unisannio.gruppo3.teacher.persistance.TeacherDAOMongo;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherDAOMongoTest {
    static boolean init=false;
    static TeacherDAO teacherDAO;
    static Long th1Id;
    static Teacher th1;

    @BeforeAll
    public static void setup(){
        if(!init) {
            teacherDAO = new TeacherDAOMongo();
            th1Id = teacherDAO.getNextId();
            assertTrue(th1Id > 0);
            th1 = new Teacher(th1Id, "Francesco","Fuccio",40,false,null,null,12.0,192.0,null,"basiDiDati",null,"fra@gmail.com","1981288");
            Long teacherId = teacherDAO.createTeacher(th1);
            assertEquals(teacherId, th1Id);
            init = true;
        }
    }

    @Test
    @Order(1)
    void createTeacher() {
        Long nextId = teacherDAO.getNextId();
        assertTrue(nextId > 0);
        assertEquals(nextId, th1Id+1);
        Teacher thCreated = new Teacher(nextId, "Michele","Fuccio",40,false,null,null,12.0,192.0,null,"basiDiDati",null,"michele@gmail.com","1981288");
        Long teacherId = teacherDAO.createTeacher(thCreated);
        assertEquals(teacherId, th1Id+1);
        System.out.println("nextId = " + nextId);
        teacherDAO.deleteTeacher(teacherId);
    }

    @Test
    @Order(2)
    void getTeacher() {
        Teacher teacher = teacherDAO.getTeacher(th1Id);
        assertEquals(th1,teacher);
    }


    @Test
    @Order(3)
    void getAllTeachers() {
        ArrayList<Teacher> allTeachers = teacherDAO.getAllTeachers();
        assertFalse(allTeachers.isEmpty());
    }

    @Test
    @Order(4)
    void updateTeacher() {
        th1.setFirstName("Loris");
        Teacher teacher = teacherDAO.updateTeacher(th1);
        assertEquals(th1, teacher);
    }

    @Test
    @Order(5)
    void deleteTeacher() {
        teacherDAO.deleteTeacher(th1Id);
        assertNull(teacherDAO.getTeacher(th1Id));
    }
}


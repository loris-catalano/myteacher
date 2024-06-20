package it.unisannio.gruppo3.lesson.persistence;

import it.unisannio.gruppo3.entities.Lesson;

import it.unisannio.gruppo3.lesson.Persistence.LessonDAO;
import it.unisannio.gruppo3.lesson.Persistence.LessonDAOMongo;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LessonDAOMongoTest {
    static boolean init=false;
    static LessonDAO lessonDAO;
    static Long le1Id;
    static Lesson le1;

    @BeforeAll
    public static void setup(){
        if(!init) {
            lessonDAO = new LessonDAOMongo();
            le1Id = lessonDAO.getNextId();
            assertTrue(le1Id > 0);
            le1 = new Lesson(le1Id, 70,"basiDiDati",0L,null,3,0L);
            Long lessonId = lessonDAO.createLesson(le1);
            assertEquals(lessonId, le1Id);
            init = true;
        }
    }

    @Test
    @Order(1)
    void createLesson() {
        Long nextId = lessonDAO.getNextId();
        assertTrue(nextId > 0);
        assertEquals(nextId, le1Id+1);
        Lesson leCreated = new Lesson(le1Id, 70,"basiDiDati",0L,null,3,0L);
        Long lessonId = lessonDAO.createLesson(leCreated);
        assertEquals(lessonId, le1Id+1);
        System.out.println("nextId = " + nextId);
        lessonDAO.deleteLesson(lessonId);
    }

    @Test
    @Order(2)
    void getLesson() {
        Lesson lesson = lessonDAO.getLesson(le1Id);
        assertEquals(le1,lesson);
    }


    @Test
    @Order(3)
    void getAllLessons() {
        ArrayList<Lesson> allLessons = lessonDAO.getAllLessons();
        assertFalse(allLessons.isEmpty());
    }

    @Test
    @Order(4)
    void updateLesson() {
        le1.setPrice(30);
        Lesson lesson = lessonDAO.updateLesson(le1);
        assertEquals(le1, lesson);
    }

    @Test
    @Order(5)
    void deleteLesson() {
        lessonDAO.deleteLesson(le1Id);
    }
}

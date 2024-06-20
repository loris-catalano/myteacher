package it.unisannio.gruppo3.lesson.logic;

import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.lesson.Logic.LessonLogic;
import it.unisannio.gruppo3.lesson.Logic.LessonLogicImpl;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LessonLogicImplTest {
    static boolean init=false;
    static LessonLogic lessonLogic;
    static Long le1Id;
    static Lesson le1;

    @BeforeAll
    public static void setup(){
        if(!init) {
            lessonLogic = new LessonLogicImpl();
            le1Id = lessonLogic.getNextId();
            assertTrue(le1Id > 0);
            le1 = new Lesson(le1Id, 70,"basiDiDati",0L,null,3,0L);
            Long LessonId = lessonLogic.createLesson(le1);
            assertEquals(LessonId, le1Id);
            init = true;
        }
    }


    @Test
    @Order(1)
    void createLesson() {
        Long nextId = lessonLogic.getNextId();
        assertTrue(nextId > 0);
        assertEquals(nextId, le1Id+1);
        Lesson leCreated = new Lesson(le1Id, 70,"basiDiDati",0L,null,3,0L);
        Long newLessonId = lessonLogic.createLesson(leCreated);
        assertEquals(newLessonId, le1Id+1);
        lessonLogic.deleteLesson(newLessonId);
    }

    @Test
    @Order(2)
    void getLesson() {
        Lesson lesson = lessonLogic.getLesson(le1Id);
        assertEquals(le1,lesson);
    }


    @Test
    @Order(3)
    void getAllLessons() {
        ArrayList<Lesson> allLessons = lessonLogic.getAllLessons();
        assertFalse(allLessons.isEmpty());
    }

    @Test
    @Order(4)
    void updateLesson() {
        le1.setPrice(30);
        Lesson lesson = lessonLogic.updateLesson(le1);
        assertEquals(le1, lesson);
    }

    @Test
    @Order(5)
    void deleteLesson() {
        lessonLogic.deleteLesson(le1Id);
    }

}


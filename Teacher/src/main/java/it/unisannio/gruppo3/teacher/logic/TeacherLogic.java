package it.unisannio.gruppo3.teacher.logic;

import it.unisannio.gruppo3.entities.Teacher;

import java.util.ArrayList;

//
public interface TeacherLogic {
    Long createTeacher(Teacher teacher);

    Teacher getTeacher(Long id);

    Teacher updateTeacher(Teacher teacher);

    boolean deleteTeacher(Long id);

    ArrayList<Teacher> getTeachersByAge(int age);

    ArrayList<Teacher> getTeachersByAgeGte(int age);

    ArrayList<Teacher> getTeachersByAgeLte(int age);

    ArrayList<Teacher> getTeachersBySubjects(String subject);


    public Long getNextId() ;
    ArrayList<Teacher> getAllTeachers();

    Teacher getTeacherByEmail(String email);
}

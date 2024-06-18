package it.unisannio.gruppo3.teacher.logic;

import it.unisannio.gruppo3.entities.Teacher;
import it.unisannio.gruppo3.teacher.persistance.TeacherDAO;
import it.unisannio.gruppo3.teacher.persistance.TeacherDAOMongo;

import java.util.ArrayList;

public class TeacherLogicImpl implements TeacherLogic {
    TeacherDAO teacherDAO;

    public TeacherLogicImpl() {
        teacherDAO = new TeacherDAOMongo();
    }

    @Override
    public Long createTeacher(Teacher teacher) {
         /*if(teacher.getId()==null) {
             teacher.setId(teacherDAO.getNextId());
         }*/
        return teacherDAO.createTeacher(teacher);
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherDAO.getTeacher(id);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherDAO.updateTeacher(teacher);
    }

    @Override
    public boolean deleteTeacher(Long id) {
        return teacherDAO.deleteTeacher(id);
    }

    @Override
    public ArrayList<Teacher> getTeachersByAge(int age) {
        return teacherDAO.getTeachersByAge(age);
    }

    @Override
    public ArrayList<Teacher> getTeachersByAgeGte(int age) {
        return teacherDAO.getTeachersByAgeGte(age);
    }

    @Override
    public ArrayList<Teacher> getTeachersByAgeLte(int age) {
        return teacherDAO.getTeachersByAgeLte(age);
    }

    @Override
    public ArrayList<Teacher> getTeachersBySubjects(String subject) {
        return teacherDAO.getTeachersBySubjects(subject);
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return teacherDAO.getAllTeachers();
    }


}
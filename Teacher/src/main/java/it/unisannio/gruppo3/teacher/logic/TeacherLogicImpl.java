package it.unisannio.gruppo3.teacher.logic;

import it.unisannio.gruppo3.entities.Teacher;
import it.unisannio.gruppo3.teacher.persistance.TeacherDAO;
import it.unisannio.gruppo3.teacher.persistance.TeacherDAOMongo;

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
}
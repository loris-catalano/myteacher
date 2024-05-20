package it.unisannio.gruppo3.teacher.logic;

import it.unisannio.gruppo3.entities.Teacher;
//
public interface TeacherLogic {
    Long createTeacher(Teacher teacher);

    Teacher getTeacher(Long id);

    Teacher updateTeacher(Teacher teacher);

    boolean deleteTeacher(Long id);

    

}

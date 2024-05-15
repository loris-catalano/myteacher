package it.unisannio.gruppo3.student.logic;

import it.unisannio.gruppo3.entities.Student;

public interface StudentLogic {
    Long createStudent(Student student);

    Student getStudent(Long id);

    Student updateStudent(Student student);

    boolean deleteStudent(Long id);
}

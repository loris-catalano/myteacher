package it.unisannio.gruppo3.student.logic;

import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.student.persistence.*;

public class StudentLogicImpl implements StudentLogic{

    StudentDAO studentDAO;

    public StudentLogicImpl(){
        studentDAO = new StudentDAOMongo();
    }

    @Override
    public Long createStudent(Student student) {
        if(student.getId()==null){
            student.setId(studentDAO.getNextId());
        }
        return studentDAO.createStudent(student);
    }

    @Override
    public Student getStudent(Long id) {
        return studentDAO.getStudent(id);
    }
}

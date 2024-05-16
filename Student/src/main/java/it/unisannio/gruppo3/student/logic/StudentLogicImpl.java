package it.unisannio.gruppo3.student.logic;

import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.student.persistence.*;

import java.util.ArrayList;

public class StudentLogicImpl implements StudentLogic{

    StudentDAO studentDAO;

    public StudentLogicImpl(){
        studentDAO = new StudentDAOMongo();
    }

    @Override
    public Long createStudent(Student student) {
        return studentDAO.createStudent(student);
    }

    @Override
    public Student getStudent(Long id) {
        return studentDAO.getStudent(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentDAO.deleteStudent(id);
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}

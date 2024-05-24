package it.unisannio.gruppo3.myteachergateway.logic;

import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.entities.Teacher;

public interface GatewayLogic {
     Student getStudent(Long studentId);
     Teacher getTeacher(Long teacherId);
}

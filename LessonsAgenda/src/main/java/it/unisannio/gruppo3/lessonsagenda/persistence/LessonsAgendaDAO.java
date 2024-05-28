package it.unisannio.gruppo3.lessonsagenda.persistence;

import it.unisannio.gruppo3.entities.LessonsAgenda;

import java.util.ArrayList;

public interface LessonsAgendaDAO {

    String DATABASE_NAME = "myteacher";
    String COLLECTION_LESSONS_AGENDAS = "LessonsAgendas";

    String ELEMENT_ID = "id";
    String ELEMENT_LESSONS = "lessons";

    String ELEMENT_HIGHEST_ID = "highest";

    boolean dropDB();

    boolean createDB();

    Long createLessonsAgenda(LessonsAgenda lessonsAgenda);

    LessonsAgenda getLessonsAgenda(Long id);

    ArrayList<LessonsAgenda> getAllLessonsAgendas();

    LessonsAgenda updateLessonsAgenda(LessonsAgenda lessonsAgenda);

    boolean deleteLessonsAgenda(Long id);

    boolean closeConnection();
}

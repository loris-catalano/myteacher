package it.unisannio.gruppo3.lessonsagenda.logic;

import it.unisannio.gruppo3.entities.LessonsAgenda;

import java.util.ArrayList;

public interface LessonsAgendaLogic {

    Long createLessonsAgenda(LessonsAgenda lessonsAgenda);

    LessonsAgenda getLessonsAgenda(Long id);

    LessonsAgenda updateLessonsAgenda(LessonsAgenda lessonsAgenda);

    boolean deleteLessonsAgenda(Long id);

    ArrayList<LessonsAgenda> getAllLessonsAgendas();
}

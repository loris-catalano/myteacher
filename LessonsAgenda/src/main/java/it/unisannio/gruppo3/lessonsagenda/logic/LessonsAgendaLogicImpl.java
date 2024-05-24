package it.unisannio.gruppo3.lessonsagenda.logic;

import it.unisannio.gruppo3.entities.LessonsAgenda;
import it.unisannio.gruppo3.lessonsagenda.persistence.*;

import java.util.ArrayList;

public class LessonsAgendaLogicImpl implements LessonsAgendaLogic{

    LessonsAgendaDAO lessonsAgendaDAO;

    public LessonsAgendaLogicImpl(){
        lessonsAgendaDAO = new LessonsAgendaDAOMongo();
    }

    @Override
    public Long createLessonsAgenda(LessonsAgenda lessonsAgenda) {
        return lessonsAgendaDAO.createLessonsAgenda(lessonsAgenda);
    }

    @Override
    public LessonsAgenda getLessonsAgenda(Long id) {
        return lessonsAgendaDAO.getLessonsAgenda(id);
    }

    @Override
    public LessonsAgenda updateLessonsAgenda(LessonsAgenda lessonsAgenda) {
        return lessonsAgendaDAO.updateLessonsAgenda(lessonsAgenda);
    }

    @Override
    public boolean deleteLessonsAgenda(Long id) {
        return lessonsAgendaDAO.deleteLessonsAgenda(id);
    }

    @Override
    public ArrayList<LessonsAgenda> getAllLessonsAgendas() {
        return lessonsAgendaDAO.getAllLessonsAgendas();
    }
}

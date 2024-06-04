package it.unisannio.gruppo3.chat.Persistence;

import it.unisannio.gruppo3.entities.Chat;

public interface ChatDAO {

    String DATABASE_NAME = "myteacher";
    String COLLECTION_LESSONS = "Chats";
    String ELEMENT_ID = "id";
    String ELEMENT_STUDENT_ID = "studentId";
    String ELEMENT_TEACHER_ID = "teacherId";
    String ELEMENT_MESSAGES = "messages";
    String ELEMENT_HIGHEST_ID = "highest";

    boolean dropDB();

    boolean createDB();

    Long createChat(Chat chat);

    Chat getChat(Long id);

    Chat updateChat(Chat chat);

    boolean deleteChat(Long id);

    boolean closeConnection();
}

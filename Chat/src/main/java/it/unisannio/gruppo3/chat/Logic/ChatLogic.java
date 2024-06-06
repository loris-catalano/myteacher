package it.unisannio.gruppo3.chat.Logic;

import it.unisannio.gruppo3.entities.Chat;

public interface ChatLogic {

    Long createChat(Chat chat);

    Chat getChat(Long id);

    Chat updateChat(Chat chat);

    boolean deleteChat(Long id);
}

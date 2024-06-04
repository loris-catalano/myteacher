package it.unisannio.gruppo3.chat.Logic;

import it.unisannio.gruppo3.chat.Persistence.ChatDAO;
import it.unisannio.gruppo3.chat.Persistence.ChatDAOMongo;
import it.unisannio.gruppo3.entities.Chat;

public class ChatLogicImpl implements ChatLogic{
    private ChatDAO chatDAO;

    public ChatLogicImpl(){
        chatDAO=new ChatDAOMongo();
    }

    @Override
    public Long createChat(Chat chat) {
        return chatDAO.createChat(chat);
    }

    @Override
    public Chat getChat(Long id) {
        return chatDAO.getChat(id);
    }

    @Override
    public Chat updateChat(Chat chat) {
        return chatDAO.updateChat(chat);
    }

    @Override
    public boolean deleteChat(Long id) {
        return chatDAO.deleteChat(id);
    }
}

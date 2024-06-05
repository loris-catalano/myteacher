package it.unisannio.gruppo3.user.logic;

import it.unisannio.gruppo3.entities.User;

import java.util.ArrayList;

public interface UserLogic {
    Long createUser(User user);

    User getUser(Long id);

    User updateUser(User user);

    boolean deleteUser(Long id);

    ArrayList<User> getAllUsers();

    Long getNextId();
}

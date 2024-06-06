package it.unisannio.gruppo3.user.persistence;

import it.unisannio.gruppo3.entities.User;

import java.util.ArrayList;

public interface UserDAO {

    String DATABASE_NAME = "security";
    String COLLECTION_USERS = "Users";

    String ELEMENT_ID = "_id"; // !! Warning: This id is not the same as the student or teacher id
    String ELEMENT_EMAIL = "email";
    String ELEMENT_PASSWORD = "password";
    String ELEMENT_ROLES = "roles";

    String ELEMENT_HIGHEST_ID = "highest";

    boolean dropDB();

    Long createUser(User user);

    User getUser(Long id);

    ArrayList<User> getAllUsers();

    User updateUser(User user);

    boolean deleteUser(Long id);

    boolean closeConnection();

    Long getNextId();
}

package it.unisannio.gruppo3.entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class User {
    Long id;
    String email;
    String password;
    List<String> roles;

    public User(Long id, String email, String password, List<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        setPasswordHashed(password);
    }

    public void setPasswordHashed(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        this.password = Base64.getEncoder().encodeToString(hashBytes);
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {this.id = id;
    }
    public Long getId(){return id;}
}

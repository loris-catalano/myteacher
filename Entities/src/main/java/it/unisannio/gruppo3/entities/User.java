package it.unisannio.gruppo3.entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class User implements Comparable<User>{
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.id == user.getId() &&
          this.email.equals(user.getEmail());
    }

    public int compareTo(User user){
return this.id.compareTo(user.id);
    }

    public int hashCode(){
        final int PRIME = 2;
        int result = 1;
        result = PRIME * result + id.hashCode();
        result = PRIME * result + email.hashCode();
        return result;
    }

}


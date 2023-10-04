package com.example.demo.entities;

public class User {

    private final Long id;

    private final String userName;
    private String firstName;
    private String lastName;
    private String repositorySecret;

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(Long id, String userName, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRepositorySecret() {
        return repositorySecret;
    }

    public void setRepositorySecret(String repositorySecret) {
        this.repositorySecret = repositorySecret;
    }
}

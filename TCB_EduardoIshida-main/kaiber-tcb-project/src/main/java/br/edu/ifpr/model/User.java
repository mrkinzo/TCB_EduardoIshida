package br.edu.ifpr.model;

public class User {
    private int iduser;
    private String name;
    private String institution;
    private String role;

    // Construtores
    public User() {}

    public User(int iduser, String name, String institution, String role) {
        this.iduser = iduser;
        this.name = name;
        this.institution = institution;
        this.role = role;
    }

    // Getters e Setters
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + iduser +
                ", name='" + name + '\'' +
                ", institution='" + institution + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
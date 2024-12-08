package org.ar.ecommerce.models;

import java.util.Date;

public class Client {
    private Integer idClient;
    private String name;
    private String email;
    private Date registerDate;

    public Client() {
    }

    public Client(Integer idClient) {
        this.idClient = idClient;
    }

    public Client(String name, String email, Date registerDate) {
        this.name = name;
        this.email = email;
        this.registerDate = registerDate;
    }

    public Client(Integer idClient, Date registerDate, String email, String name) {
        this.idClient = idClient;
        this.registerDate = registerDate;
        this.email = email;
        this.name = name;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}

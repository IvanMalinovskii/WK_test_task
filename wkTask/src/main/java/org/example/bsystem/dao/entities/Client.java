package org.example.bsystem.dao.entities;

import java.util.Objects;

/**
 * a data base immutable client entity
 */
public class Client {
    /**
     * client's id
     */
    private long id;
    /**
     * client's login
     */
    private String login;
    /**
     * client's email
     */
    private String email;
    /**
     * client's surname
     */
    private String surname;
    /**
     * client's balance
     */
    private double balance;
    /**
     * client's rate
     */
    private Rate rate;

    /**
     * initializes an instance with input values
     * @param id client's id
     * @param login client's login
     * @param email client's email
     * @param surname client's surname
     * @param balance client's balance
     * @param rate client's rate
     */
    public Client(long id, String login, String email, String surname, double balance, Rate rate) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.surname = surname;
        this.balance = balance;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public double getBalance() {
        return balance;
    }

    public Rate getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Double.compare(client.balance, balance) == 0 &&
                Objects.equals(login, client.login) &&
                Objects.equals(email, client.email) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(rate, client.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, surname, balance, rate);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", balance=" + balance +
                ", rate=" + rate +
                '}';
    }
}

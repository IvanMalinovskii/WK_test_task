package org.example.bsystem.dao.entities;

import java.util.Objects;

/**
 * a data base immutable rate entity
 */
public class Rate {
    /**
     * rate's id
     */
    private long id;
    /**
     * rate's name
     */
    private String rate;

    /**
     * initializes an instance with input values
     * @param id rate's id
     * @param rate rate's name
     */
    public Rate(long id, String rate) {
        this.id = id;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public String getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate1 = (Rate) o;
        return id == rate1.id &&
                Objects.equals(rate, rate1.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", rate='" + rate + '\'' +
                '}';
    }
}

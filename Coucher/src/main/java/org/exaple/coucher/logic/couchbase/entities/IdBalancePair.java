package org.exaple.coucher.logic.couchbase.entities;

import java.util.Objects;

/**
 * describes a client_id/balance pair
 */
public class IdBalancePair {
    private long id;
    private double balance;

    public IdBalancePair(long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdBalancePair that = (IdBalancePair) o;
        return id == that.id &&
                Double.compare(that.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }

    @Override
    public String toString() {
        return id + "/" + balance;
    }
}

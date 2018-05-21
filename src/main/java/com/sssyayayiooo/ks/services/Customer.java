package com.sssyayayiooo.ks.services;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * Created by e079608 on 5/3/2018.
 */
@Table
public class Customer {

    @PrimaryKey
    private UUID id;

    private String firstName;

    private String lastName;

    public Customer() {
    }

    public Customer(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s', lastName='%s']", this.id,
                this.firstName, this.lastName);
    }

}

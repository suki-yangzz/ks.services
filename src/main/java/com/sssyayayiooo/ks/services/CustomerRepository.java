package com.sssyayayiooo.ks.services;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by e079608 on 5/3/2018.
 */
public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Query("Select * from customer where firstname=?0")
    public Customer findByFirstName(String firstName);

    @Query("Select * from customer where lastname=?0")
    public List<Customer> findByLastName(String lastName);

}

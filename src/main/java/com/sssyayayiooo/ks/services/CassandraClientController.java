package com.sssyayayiooo.ks.services;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Suki Yang on 5/4/2018.
 */
public interface CassandraClientController {
    @RequestMapping("/connect-cassandra")
    String connect(String... args) throws Exception;
}

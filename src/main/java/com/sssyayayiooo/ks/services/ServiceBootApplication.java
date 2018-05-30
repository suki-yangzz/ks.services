package com.sssyayayiooo.ks.services;

import com.datastax.driver.core.utils.UUIDs;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Suki Yang on 5/4/2018.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceBootApplication implements GreetingController, CassandraClientController {

    @Qualifier("eurekaClient")
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    private CustomerRepository repository;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String portNumber;

    public static void main(String[] args) {
        SpringApplication.run(ServiceBootApplication.class, args);
    }

    @Override
    public String greeting() {
        System.out.println("Request received on port number " + portNumber);
        return String.format("Hello from '%s with Port Number %s'!", eurekaClient.getApplication(appName).getName(), portNumber);
    }

    @Override
    public String connect(String... args) throws Exception {
        System.out.println("Request received on port number " + portNumber);

        this.repository.deleteAll();

        // save a couple of customers
        this.repository.save(new Customer(UUIDs.timeBased(), "Alice", "Smith"));
        this.repository.save(new Customer(UUIDs.timeBased(), "Bob", "Smith"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : this.repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
        return String.format("Customers found with LastName Smith %s from '%s with Port Number %s'!",
                this.repository.findByLastName("Smith").toString(), eurekaClient.getApplication(appName).getName(), portNumber);
    }
}

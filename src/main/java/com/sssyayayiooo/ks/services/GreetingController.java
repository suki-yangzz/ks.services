package com.sssyayayiooo.ks.services;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Suki Yang on 5/4/2018.
 */
public interface GreetingController {
    @RequestMapping("/greeting")
    String greeting();
}

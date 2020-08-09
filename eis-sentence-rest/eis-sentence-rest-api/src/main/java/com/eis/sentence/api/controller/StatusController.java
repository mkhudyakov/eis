package com.eis.sentence.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.ALL_VALUE;

/**
 * @author Maksym Khudiakov
 */
@RestController
public class StatusController {

    @GetMapping(value = "/status", consumes = ALL_VALUE, produces = ALL_VALUE)
    public String getStatus() {
        return "OK";
    }
}

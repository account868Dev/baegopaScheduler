package com.high.baegopa.controllers;

import com.high.baegopa.jobs.LocationApiJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by high on 2017. 7. 9..
 */
@RestController
public class TestController {

    @Autowired private LocationApiJob locationApiJob;

    @RequestMapping("/location")
    public String setLocation() {
        locationApiJob.setLocationArea();
        return "success";
    }
}

package com.osevol.measureme.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.osevol.measureme.domain.Website;
import com.osevol.measureme.service.WebsiteService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Controller
public class HomeController {


    private final WebsiteService websiteService;
    private final Counter visitCounter;

    public HomeController(WebsiteService websiteService, MeterRegistry registry) {

        this.websiteService = websiteService;
        this.visitCounter = Counter.builder("api.homepage").register(registry);
    }

    @GetMapping(value="/")
    @Timed(value="api.getHomePage")
    public String getHomePage(Map<String, Object> model) {
        visitCounter.increment();
        List<Website> websiteList = websiteService.findAll();
        model.put("websiteList", websiteList);
        return "home";
    }
}

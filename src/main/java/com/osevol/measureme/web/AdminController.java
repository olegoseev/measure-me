package com.osevol.measureme.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appinfo")
public class AdminController {
	
    @GetMapping
    public String admin(){
        return "appInfo";
    }
}

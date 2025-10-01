package org.zerock.b01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class SampleController {

//   @GetMapping("/")
//    public String home(Model model) {
//        log.info("index....");
//        return "index";
//    }

    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("hello....");
        model.addAttribute("message", "Hello World");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {        
        List<String> list = Arrays.asList("1", "2", "3");
        model.addAttribute("list", list);
        log.info("model : " + model);
    }
}

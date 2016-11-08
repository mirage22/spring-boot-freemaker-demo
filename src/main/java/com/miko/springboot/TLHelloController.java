package com.miko.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Miro Kopecky (@miragemiko)
 * @since 07.11.2016
 */
@Controller
public class TLHelloController {


    @RequestMapping(value = "/thyme")
    public String something(Model model){
        System.out.println("Thymeleaf");
        model.addAttribute("thyme", "The Thymeleaf");
        return "thyme";
    }

}

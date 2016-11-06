package com.miko.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Miro Kopecky (@miragemiko)
 * @since 06.11.2016
 */

@Controller
public class HelloController {


    @RequestMapping("/")
    public String index(){
        System.out.println("INDEX");
        return "index";
    }

    @RequestMapping("/magic")
    public String hello(Model model, @RequestParam(value = "word", required=false, defaultValue="MagicHappens") String word) {
        model.addAttribute("word", word);
        return "magic";
    }
}

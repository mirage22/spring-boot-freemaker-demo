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
public class FMHelloController {


    @RequestMapping("/")
    public String index(){
        System.out.println("INDEX");
        return "index";
    }

    @RequestMapping("/magic")
    public String magic(Model model, @RequestParam(value = "word", required=false, defaultValue="MagicHappens") String word) {
        System.out.println("MAGIC");
        model.addAttribute("word", word);
        return "magic";
    }

}

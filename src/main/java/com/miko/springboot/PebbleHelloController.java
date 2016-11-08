package com.miko.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Miro Kopecky (@miragemiko)
 * @since 08.11.2016
 */

@Controller
public class PebbleHelloController {

    @RequestMapping(value = "/pebble")
    public String something(Model model){
        System.out.println("Pebble");
        model.addAttribute("pebble", "The Pebble");
        return "pebble";
    }
}

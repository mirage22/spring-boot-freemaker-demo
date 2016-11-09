/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This TLHelloController.java is part of spring-boot-freemaker-demo.
 *
 *     spring-boot-freemaker-demo is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     spring-boot-freemaker-demo is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with spring-boot-freemaker-demo .  If not, see <http://www.gnu.org/licenses/>.
 */

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

/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This MagicApplication.java is part of spring-boot-freemaker-demo.
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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author Miro Kopecky (@miragemiko)
 * @since 06.11.2016
 */

@SpringBootApplication
public class MagicApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MagicApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(MagicApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}

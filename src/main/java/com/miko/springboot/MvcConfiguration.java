/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This MvcConfiguration.java is part of spring-boot-freemaker-demo.
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

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring4.PebbleViewResolver;
import com.mitchellbosecke.pebble.spring4.extension.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author Miro Kopecky (@miragemiko)
 * @since 06.11.2016
 */

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ServletContext servletContext;

    @Bean(name = "freeMarkerViewResolver")
    public ViewResolver getFreeMakerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setSuffix(".ftl");
        resolver.setCache(true);
        return resolver;
    }

    @Bean(name = "velocityViewResolver")
    public ViewResolver getVelocityViewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setSuffix(".vm");
        resolver.setCache(true);
        return resolver;
    }


    @Bean(name="pebbleViewResolver")
    public ViewResolver getPebbleViewResolver(){
        PebbleViewResolver resolver = new PebbleViewResolver();
        resolver.setPrefix("/pebble/");
        resolver.setSuffix(".html");
        resolver.setPebbleEngine(pebbleEngine());
        return resolver;
    }

    @Bean(name = "thymeleafViewResolver")
    public ViewResolver getThymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(getThymeleafTemplateEngine());
        resolver.setCache(true);
        return resolver;
    }

    @Bean(name = "viewResolver")
    public ViewResolver contentNegotiatingViewResolver( ContentNegotiationManager manager) {
        List<ViewResolver> resolvers =
                Arrays.asList(
                        getVelocityViewResolver(),
                        getFreeMakerViewResolver(),
                        getPebbleViewResolver()
//                        getThymeleafViewResolver()
                );
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    //Pebble extension
    @Bean
    public Loader templatePebbleLoader(){
        return new ServletLoader(servletContext);
    }

    @Bean
    public SpringExtension pebbleSpringExtension() {
        return new SpringExtension();
    }

    @Bean
    public PebbleEngine pebbleEngine() {
        return new PebbleEngine.Builder()
                .loader(this.templatePebbleLoader())
                .extension(pebbleSpringExtension())
                .build();
    }

    /* thymeleaf */
    @Bean(name ="thymeleafTemplateEngine")
    public SpringTemplateEngine getThymeleafTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getThymeleafTemplateResolver());
        return templateEngine;
    }

    @Bean(name ="thymeleafTemplateResolver")
    public ServletContextTemplateResolver getThymeleafTemplateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/thymeleaf/");
        templateResolver.setSuffix(".htm");
        return templateResolver;
    }
}

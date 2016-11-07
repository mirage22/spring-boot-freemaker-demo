package com.miko.springboot;

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

import java.util.LinkedList;
import java.util.List;

/**
 * @author Miro Kopecky (@miragemiko)
 * @since 06.11.2016
 */

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean(name = "freeMakerViewResolver")
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

    @Bean(name = "viewResolver")
    public ViewResolver contentNegotiatingViewResolver( ContentNegotiationManager manager) {
        List<ViewResolver> resolvers = new LinkedList<>();
        resolvers.add(getFreeMakerViewResolver());
        resolvers.add(getVelocityViewResolver());
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}

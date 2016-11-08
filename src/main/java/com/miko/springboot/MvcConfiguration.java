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
import java.util.LinkedList;
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
        List<ViewResolver> resolvers = new LinkedList<>();
        resolvers.add(getFreeMakerViewResolver());
        resolvers.add(getVelocityViewResolver());
//        resolvers.add(getPebbleViewResolver());
        resolvers.add(getThymeleafViewResolver());
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
    public Loader templateLoader(){
        return new ServletLoader(servletContext);
    }

    @Bean
    public SpringExtension springExtension() {
        return new SpringExtension();
    }

    @Bean
    public PebbleEngine pebbleEngine() {
        return new PebbleEngine.Builder()
                .loader(this.templateLoader())
                .extension(springExtension())
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
        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("XHTML");
        return templateResolver;
    }
}

package com.jd2.elibrary.web.spring;

import com.jd2.elibrary.service.impl.config.ServiceConfig;
import com.jd2.elibrary.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
public class WebConfig {
    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public CustomerPageController customerPageController() {
        return new CustomerPageController(serviceConfig.bookService(), serviceConfig.orderService());
    }

    @Bean
    public EditBookCatalogueController editBookCatalogueController() {
        return new EditBookCatalogueController(serviceConfig.bookService());
    }

    @Bean
    public EditController editController() {
        return new EditController(serviceConfig.userService());
    }

    @Bean
    public IndexController indexController() {
        return new IndexController();
    }

    @Bean
    public LibrarianPageController librarianPageController() {
        return new LibrarianPageController(serviceConfig.userService());
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(serviceConfig.userService());
    }

    @Bean
    public LogoutController logoutController(){
        return new LogoutController();
    }

    @Bean
    public RegistrationController registrationController(){
        return new RegistrationController(serviceConfig.userService());
    }

    @Bean OrderPageController orderPageController(){
        return new OrderPageController(serviceConfig.orderService(), serviceConfig.bookService());
    }

//    @Bean
//    ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

    @Bean
    public UrlBasedViewResolver tilesViewResolver(){
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }
}

package com.example.mvc.configmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//WebMvcConfigurer это интерфейс в Spring MVC, который предоставляет методы для настройки
// поведения веб-приложения, связанного с обработкой запросов и отображением представлений

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //метод который позволяет добавить обработчики для статических ресурсов,
    // таких как сss, JavaScript, изображения и указать их расположение
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/parts/**")//указываем путь, по которому будет доступна папка static
                .addResourceLocations("classpath:/parts/");//указываем расположение папки в проекте
    }
}//позволяем приложению использовать папку

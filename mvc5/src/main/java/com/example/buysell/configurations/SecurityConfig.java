package com.example.buysell.configurations;
import com.example.buysell.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor//конструкторы
@EnableGlobalMethodSecurity(prePostEnabled = true)
//WebSecurityConfigurerAdapter класс из фреймворка Spring Security
//который предоставляет удобные методы для настройки безопасности веб-приложения
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {// метод который позволяет настроить
        // конфигурацию безопасности httpзапросов
        http      //начинаем конфигурацию для обработки запросов на авторизацию
                 //возвращает объект ExpressionInterceptUrlRegistry который предоставляет методы для определения правил авторизации
                .authorizeRequests()
                // antMatchers определяет пути для которых требуется определенный уровень доступа
                .antMatchers("/", "/product/**", "/images/**", "/registration", "/user/**", "/static/**")
                // пути доступны для всех permitAll без требования аутентификации
                .permitAll()
                //метод указывает что все остальные запросы должны быть аутентифицированы
                .anyRequest().authenticated()
                .and()
                //formLogin() определяет настройки для формы входа в систему
                .formLogin()
                //метод loginPage("/login") указывает url-адрес страницы входа в систему.
                .loginPage("/login")
                //доступ всем
                .permitAll()
                .and()
                //настройки выхода из системы
                .logout()
                .permitAll();
    }

    @Override
    //метод который позволяет настроить аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)//метод который создает и возвращает UserDetailsService для использования в аутентификации.
                .passwordEncoder(passwordEncoder());//шифрорвание хэширование
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
// метод является частью конфигурации Spring и создает экземпляр класса BCryptPasswordEncoder(для реализации класса энкодера)
//который реализует интерфейс PasswordEncoder
//passwordEncoder() создает экземпляр BCryptPasswordEncoder с параметром 8  который указывает на силу хеширования
//будет выполнено 2 в 8 раундов хеширования для создания зашифрованного пароля
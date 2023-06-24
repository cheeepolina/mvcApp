package com.example.mvc.services;

import com.example.mvc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service//класс логика приложения
@RequiredArgsConstructor//конструкторы
//UserDetailsService интерфейс который является частью Spring Security и
// используется для загрузки пользовательских данных для аутентификации и авторизации
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
//переопределяем метод loadUserByUsername для поиска юзера по емайлу
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);//возвращаем объект UserDetails который представляет информацию пользоваетля
    }
}
//если пользователь не найден бросает исключение UsernameNotFoundException
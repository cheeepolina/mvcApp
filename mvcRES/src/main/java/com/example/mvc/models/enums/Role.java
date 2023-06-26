package com.example.mvc.models.enums;

import org.springframework.security.core.GrantedAuthority;
//enum это особый класс, который представляет фиксированный набор констант
//GrantedAuthority интерфейс Spring с методом для определения ролей
public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN;//роли

    @Override
    public String getAuthority() {
        return name();
    }//переопределяем метод
    //возвращаем роль в виде строки
}

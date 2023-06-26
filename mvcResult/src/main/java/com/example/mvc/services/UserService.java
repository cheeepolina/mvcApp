package com.example.mvc.services;

import com.example.mvc.models.User;
import com.example.mvc.models.enums.Role;
import com.example.mvc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;//если юзер с емайлом не найдем возвращаем false
        user.setActive(true);//иначе ставим кативность на тру
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);//устнавливаем роль
        log.info("Создан новый пользователь с email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Бан юзера с id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Разбанен юзер с id = {}; email: {}", user.getId(), user.getEmail());
            }
            userRepository.save(user);
        }
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
               //преобразуем роли в строки и собираем их в множество
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();//удаляем текщую роль пользователя
        for (String key : form.keySet()) {//проходится по каждому ключу key из form.keySet()
            if (roles.contains(key)) {//если ключ соответствует одной из возможных ролей
                user.getRoles().add(Role.valueOf(key));// получаем соответствующую роль по ключу и добавляем в список ролей пользователя
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        var user = userRepository.findByEmail(principal.getName());
        if (!user.isActive()) {
            throw new RuntimeException("User is banned");
        }
        return user;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

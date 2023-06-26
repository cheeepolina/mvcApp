package com.example.mvc.controllers;

import com.example.mvc.models.User;
import com.example.mvc.models.enums.Role;
import com.example.mvc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller//класс обработки запросов (админ)
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")//доступ к методам этого контроллера только для админа
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")// oтражение список пользователей и информацию о текущем пользователе
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")//бан пользователей
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")//отображение формы редактирования
    public String userEdit(@PathVariable("user") Long userId, Model model, Principal principal) {
        var user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }
}


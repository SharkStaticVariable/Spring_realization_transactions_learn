package org.example.spring_realization_transactions.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.spring_realization_transactions.dto.LoginDto;
import org.example.spring_realization_transactions.entity.RolesEntity;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.repository.UsersRepository;
import org.example.spring_realization_transactions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    public UserService userService;
    public UsersRepository usersRepository;
    @GetMapping
    public String loginPage() {
        return "login";
    }
    @PostMapping("/in")
    public String login(Model model, @ModelAttribute LoginDto loginDto) {
        log.info("Данные для входа: {}", loginDto);
        Integer userId = userService.getUserIdByUsername(loginDto.getUsername());

        try {
            if (userHasRole(userId)) {
                return "redirect:/admin?userId=" + userId;
            } else {
                return "redirect:/licabinet?userId="+ userId;
            }
        } catch (UsernameNotFoundException e) {
            log.error("User not found with userId: {}", userId);
            // Обработка случаев, когда пользователь не найден
            // Например, перенаправление на страницу ошибки или возврат на страницу входа с сообщением об ошибке
            return "redirect:/login?error";
        }
    }

    private boolean userHasRole(Integer userId) {
        // Получаем пользователя по его идентификатору
        UsersEntity user = userService.getUserById(userId);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with userId: " + userId);
        }

        // Проверяем, имеет ли пользователь роль "ADMIN"
        return RolesEntity.ADMIN.equals(user.getRoles());
    }
//    @PostMapping("/in")
//    public String login(Model model, @ModelAttribute LoginDto loginDto) {
//        log.info("Данные для входа: {}", loginDto);
//        Integer userId = userService.getUserIdByUsername(loginDto.getUsername());
//        if (userHasRole(loginDto.getUsername(), "ADMIN")) {
//            return "redirect:/admin?userId=" + userId;
//        } else {
//            return "redirect:/licabinet?userId="+ userId;
//        }
//    }
//
//    private boolean userHasRole(String username, String role) {
//        // Здесь может быть логика проверки роли пользователя в вашей системе
//        // В данном примере просто предполагаем, что пользователь с ролью ADMIN
//        // имеет имя пользователя "admin"
//        return "ADMIN".equals(role);
//    }


}
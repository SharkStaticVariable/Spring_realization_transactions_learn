package org.example.spring_realization_transactions.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.spring_realization_transactions.entity.RolesEntity;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return userService.findById(id)
                .map(user->{
                    model.addAttribute("user", user);
                    model.addAttribute("roles", RolesEntity.values());
                    return "user";
                })
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public String adminPage(Model model) {
        log.info("Переход на страницу админ-панели");
        List<UsersEntity> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "user/admin";
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editUser(@PathVariable Integer id, @RequestBody Map<String, String> updates) {
        log.info("Received request to update user with ID: {}", id);
        log.info("Update details: {}", updates);
        String newRole = updates.get("roles");
        log.info("New role: {}", newRole);

        boolean updated = userService.updateUserRole(id, newRole);
        if (!updated) {
            log.info("New role: {}", newRole);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        log.info("Successfully updated user with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

}

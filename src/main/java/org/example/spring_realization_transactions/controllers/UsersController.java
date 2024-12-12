package org.example.spring_realization_transactions.controllers;

import lombok.AllArgsConstructor;
import org.example.spring_realization_transactions.dto.UserDto;
import org.example.spring_realization_transactions.entity.RolesEntity;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.mapper.UserMapper;
import org.example.spring_realization_transactions.service.AccountsService;
import org.example.spring_realization_transactions.service.EncryptDecryptService;
import org.example.spring_realization_transactions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    EncryptDecryptService encryptDecryptService;
    private final UserMapper userMapper;
    @Autowired
    private AccountsService accountService;


    @PutMapping("/api/user/put/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Integer id, @RequestBody UserDto userDto){
        Optional<UserDto> updatedUser = userService.update(id, userDto);
        return userService.update(id, userDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/save/users")
    public String create(Model model, @ModelAttribute UserDto userDto) {
        userDto.setRoles(RolesEntity.USER);
        UsersEntity usersEntity = userMapper.toUserEntity(userDto);

        String encryptedPassword = encryptDecryptService.encryptMessage(usersEntity.getPassword());
        usersEntity.setPassword(encryptedPassword);

        userService.save(usersEntity);
        List<UsersEntity> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "redirect:/login";
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UsersEntity>> findAll() {
        List<UsersEntity> users = userService.findAll();
//        users.forEach(user -> {
//            String decryptedPassword = encryptDecryptService.decryptMessage(user.getPassword());
//            user.setPassword(decryptedPassword);
//        });
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        model.addAttribute("roles", Arrays.asList(RolesEntity.values()));
        return "roles";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute UserDto userDto) {

        UserDto build = UserDto.builder()
                .password("FASFA")
                .age(12)
                .email("123@gmail.com")
                .roles(RolesEntity.USER)
                .address("fasfa")
                .middleName("sfasf")
                .firstName("fafaf")
                .lastName("fasfa")
                .username("afsa")
                .phoneNumber("SAFSFAFA")
                .build();
        model.addAttribute("user", build);
        model.addAttribute("roles", RolesEntity.values());
        return "user/registration";
    }


    @DeleteMapping("/api/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            boolean isDeleted = userService.delete(id);
            if (!isDeleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error deleting user with ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id, Model model){
//        return userService.findById(id)
//                .map(user->{
//                    model.addAttribute("user", user);
//                    model.addAttribute("roles", RolesEntity.values());
//                    return "licabinet";
//                })
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
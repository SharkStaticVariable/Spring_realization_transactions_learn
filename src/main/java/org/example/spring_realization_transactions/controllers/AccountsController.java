package org.example.spring_realization_transactions.controllers;

import lombok.AllArgsConstructor;
import org.example.spring_realization_transactions.entity.AccountsEntity;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.service.AccountsService;
import org.example.spring_realization_transactions.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/acc")
public class AccountsController {

    private final AccountsService accountsService;
    private final UserService userService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<AccountsEntity> createAccount(@PathVariable Integer userId) {
        // Получаем пользователя по его ID
        UsersEntity user = userService.getUserById(userId);

        // Проверяем, что пользователь существует
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Проверяем, если у пользователя уже есть счет
        if (user.getAccountId() != null) { // Если accountId не null, значит у пользователя уже есть счет
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Можно вернуть ошибку 400, если счет уже существует
        }

        // Создаем счет для пользователя
        AccountsEntity createdAccount = accountsService.createAccountForUser(user);

        // Привязываем созданный счет к пользователю
        user.setAccountId(createdAccount.getId());
        userService.save(user);  // Сохраняем пользователя с обновленным account_id

        return ResponseEntity.ok(createdAccount);
    }


    @GetMapping("/api/accounts")
    public ResponseEntity<List<AccountsEntity>> readAll() {
        return new ResponseEntity<>(accountsService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the unprotected page";
    }

    @PostMapping("/api/save")
    public ResponseEntity<AccountsEntity> save(@RequestBody AccountsEntity accountsEntity) {
        return new ResponseEntity<>(accountsService.save(accountsEntity), HttpStatus.OK);
    }

    @PutMapping("/api/put")
    public ResponseEntity<AccountsEntity> update(@RequestBody AccountsEntity accountsEntity) {
        return new ResponseEntity<>(accountsService.update(accountsEntity), HttpStatus.OK);
    }

    @DeleteMapping("/api/delete/{id}")
    public HttpStatus delete(@PathVariable Integer id) {
        accountsService.delete(id);
        return HttpStatus.OK;
    }
}

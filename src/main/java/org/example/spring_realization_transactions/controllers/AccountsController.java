package org.example.spring_realization_transactions.controllers;

import lombok.AllArgsConstructor;
import org.example.spring_realization_transactions.entity.AccountsEntity;
import org.example.spring_realization_transactions.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/acc")
public class AccountsController {

    private final AccountsService accountsService;

    // Обработка POST-запроса для создания счета
    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createAccount(@PathVariable("userId") Integer userId) {
        try {
            accountsService.createAccount(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Счет успешно создан");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка при создании счета: " + e.getMessage());
        }
    }

    // Получение счета по ID пользователя
    @GetMapping("/api/account/{userId}")
    public ResponseEntity<AccountsEntity> getAccountByUserId(@PathVariable Integer userId) {
        AccountsEntity account = accountsService.getAccountByUserId(userId); // Ваш сервис для получения счета
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Если счет не найден
        }
        return ResponseEntity.ok(account); // Возвращаем найденный счет
    }






    @PostMapping("/{accountId}/deposit")
    public String deposit(@PathVariable Integer accountId, @RequestParam BigDecimal amount) {
        accountsService.deposit(accountId, amount);
        return "Deposit successful.";
    }

    @PostMapping("/{sourceAccountId}/transfer")
    public String transfer(@PathVariable Integer sourceAccountId, @RequestParam Integer targetAccountId, @RequestParam BigDecimal amount) {
        accountsService.transfer(sourceAccountId, targetAccountId, amount);
        return "Transfer successful.";
    }









    // Получение списка всех счетов
    @GetMapping("/api/accounts")
    public ResponseEntity<List<AccountsEntity>> getAllAccounts() {
        List<AccountsEntity> accounts = accountsService.readAll(); // Ваш сервис для получения списка счетов
        return ResponseEntity.ok(accounts); // Возвращаем список счетов
    }
    // Сохранение нового счета
    @PostMapping("/api/save")
    public ResponseEntity<AccountsEntity> saveAccount(@RequestBody AccountsEntity accountsEntity) {
        AccountsEntity savedAccount = accountsService.save(accountsEntity); // Сохраняем счет через сервис
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount); // Возвращаем сохраненный счет
    }

    // Обновление существующего счета
    @PutMapping("/api/put")
    public ResponseEntity<AccountsEntity> updateAccount(@RequestBody AccountsEntity accountsEntity) {
        AccountsEntity updatedAccount = accountsService.update(accountsEntity); // Обновляем счет через сервис
        return ResponseEntity.ok(updatedAccount); // Возвращаем обновленный счет
    }

    // Удаление счета
    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
        try {
            accountsService.delete(id); // Удаляем счет через сервис
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Счет успешно удален");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка при удалении счета: " + e.getMessage());
        }
    }
}
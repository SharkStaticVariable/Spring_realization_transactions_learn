package org.example.spring_realization_transactions.service;

import lombok.AllArgsConstructor;
import org.example.spring_realization_transactions.entity.AccountsEntity;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.repository.AccountsRepository;
import org.example.spring_realization_transactions.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;



    public void createAccount(Integer userId) {
        UsersEntity user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        // Создаем новый объект счета
        AccountsEntity account = new AccountsEntity();
        account.setBalance(BigDecimal.ZERO); // Начальный баланс = 0
        account.setCreationDate(LocalDate.now()); // Дата создания = текущая дата
        account.setNumber(generateAccountNumber()); // Генерируем номер счета
        account.setUser(user); // Привязываем счет к пользователю

        accountsRepository.save(account); // Сохраняем счет в базе данных
    }

    private int generateAccountNumber() {
        // Пример генерации уникального номера счета (можно улучшить)
        return (int) (Math.random() * 1000000);
    }

    // Метод для получения счета по userId
    public AccountsEntity getAccountByUserId(Integer userId) {
        // Предполагаем, что счет связан с пользователем через поле userId
        return accountsRepository.findByUserId(userId);  // Метод репозитория для поиска по userId
    }


    // Сохранение счета
    public AccountsEntity save(AccountsEntity accountsEntity) {
        return accountsRepository.save(accountsEntity);
    }

    // Получение всех счетов
    public List<AccountsEntity> readAll() {
        return accountsRepository.findAll();
    }

    // Обновление счета
    public AccountsEntity update(AccountsEntity accountsEntity) {
        return accountsRepository.save(accountsEntity);
    }

    // Удаление счета по ID
    public void delete(Integer id) {
        accountsRepository.deleteById(id);
    }
}

package org.example.spring_realization_transactions.service;

import lombok.AllArgsConstructor;
import org.example.spring_realization_transactions.entity.AccountsEntity;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.repository.AccountsRepository;
import org.example.spring_realization_transactions.repository.UsersRepository;
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


    public AccountsEntity createAccountForUser(UsersEntity user) {
        // Создаем новый счет
        AccountsEntity account = new AccountsEntity();
        account.setNumber(generateAccountNumber());
        account.setBalance(new BigDecimal("0.00")); // Устанавливаем начальный баланс
        account.setCreationDate(LocalDate.now()); // Устанавливаем текущую дату как дату создания счета
        account.setUser(user); // Привязываем пользователя к счету

        // Сохраняем созданный счет в базе данных
        AccountsEntity savedAccount = accountsRepository.save(account);

        // Обновляем пользователя, чтобы привязать ему ID счета
        user.setAccountId(savedAccount.getId()); // Присваиваем ID счета пользователю
        usersRepository.save(user); // Сохраняем обновленного пользователя

        return savedAccount; // Возвращаем сохраненный счет
    }




    // Генерация случайного номера счета
    private int generateAccountNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Генерация случайного числа от 100000 до 999999
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

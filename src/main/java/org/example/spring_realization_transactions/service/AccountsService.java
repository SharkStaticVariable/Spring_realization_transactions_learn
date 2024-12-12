package org.example.spring_realization_transactions.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.spring_realization_transactions.entity.AccountsEntity;
import org.example.spring_realization_transactions.entity.TransactionLogEntity;
import org.example.spring_realization_transactions.entity.TransactionsEntity;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.repository.AccountsRepository;
import org.example.spring_realization_transactions.repository.TransactionLogRepository;
import org.example.spring_realization_transactions.repository.TransactionsRepository;
import org.example.spring_realization_transactions.repository.UsersRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;
    private final TransactionsRepository transactionRepository;
    private final TransactionLogRepository transactionLogRepository;


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







    @Transactional
    public void deposit(Integer accountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            // Создаем транзакцию как не выполненную, если сумма некорректна
            TransactionsEntity transaction = new TransactionsEntity();
            AccountsEntity account = accountsRepository.findById(accountId)
                    .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));

            transaction.setSenderAccount(account); // Отправитель и получатель — один и тот же аккаунт
            transaction.setReceiverAccount(account);
            transaction.setAmount(amount);
            transaction.setType("DEPOSIT");
            transaction.setDescription("Deposit failed, invalid amount.");
            transaction.setCreatedAt(LocalDateTime.now());

            // Сохраняем транзакцию как не выполненную
            transactionRepository.save(transaction);


            return; // Выход из метода, баланс не обновляется
        }

        // Получаем аккаунт
        AccountsEntity account = accountsRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));

        // Обновляем баланс
        account.setBalance(account.getBalance().add(amount));
        accountsRepository.save(account); // Сохраняем обновленный баланс

        // Создаем транзакцию
        TransactionsEntity transaction = new TransactionsEntity();
        transaction.setSenderAccount(account); // Отправитель и получатель — один и тот же аккаунт
        transaction.setReceiverAccount(account);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");  // Используем строковое значение для типа транзакции
        transaction.setDescription("Account deposit");
        transaction.setCreatedAt(LocalDateTime.now());

        // Сохраняем транзакцию в базе данных
        transactionRepository.save(transaction); // Транзакция теперь имеет ID

        // Проверяем, что транзакция была сохранена и имеет ID
        if (transaction.getId() == null) {
            throw new IllegalStateException("Transaction could not be saved.");
        }

    }










    @Transactional
    public void transfer(Integer sourceAccountId, Integer targetAccountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        AccountsEntity sourceAccount = accountsRepository.findById(sourceAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found."));
        AccountsEntity targetAccount = accountsRepository.findById(targetAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Target account not found."));

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        accountsRepository.save(sourceAccount);
        accountsRepository.save(targetAccount);

        TransactionsEntity transaction = new TransactionsEntity();
        transaction.setSenderAccount(sourceAccount);
        transaction.setReceiverAccount(targetAccount);
        transaction.setAmount(amount);
        transaction.setType("Transfer");
        transaction.setDescription("Funds transfer");
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);

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
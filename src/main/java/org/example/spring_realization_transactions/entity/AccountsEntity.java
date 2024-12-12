package org.example.spring_realization_transactions.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "accounts")
public class AccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="balance")
    private BigDecimal balance;
    @Column(name="creationDate")
    private LocalDate creationDate;
    @Column(name="number")
    private int number;

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private Set<TransactionsEntity> sentTransactions;

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private Set<TransactionsEntity> receivedTransactions;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UsersEntity user;


    public Set<TransactionsEntity> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(Set<TransactionsEntity> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public Set<TransactionsEntity> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(Set<TransactionsEntity> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    // Конструкторы
    public AccountsEntity(BigDecimal balance , int number, LocalDate creationDate) {
        this.balance = balance;
        this.number = number;
    }

    public AccountsEntity() {
    }

    // Метод для корректного установления баланса с BigDecimal
    public void setBalance() {
        this.balance = balance;
    }
}

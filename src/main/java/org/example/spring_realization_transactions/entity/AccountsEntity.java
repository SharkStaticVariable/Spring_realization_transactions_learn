package org.example.spring_realization_transactions.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.time.LocalDate;

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


    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UsersEntity user;


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

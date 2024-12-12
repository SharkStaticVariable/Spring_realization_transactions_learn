package org.example.spring_realization_transactions.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name="age")
    private int age;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name="address")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="password", length = 4096)
    private String password;



    public RolesEntity getRoles() {
        return roles;
    }
    public void setRoles(RolesEntity roles) {
        this.roles = roles;
    }
    @Enumerated(EnumType.STRING)
    private RolesEntity roles;


    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountsEntity account;

    public void setAccountId(Integer id) {
    }

    public Integer getAccountId() {
        return this.id;
    }
}
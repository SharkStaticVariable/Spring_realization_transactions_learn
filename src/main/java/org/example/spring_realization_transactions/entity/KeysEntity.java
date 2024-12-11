package org.example.spring_realization_transactions.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name="keys")
public class KeysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "publickey", columnDefinition = "bytea")
    private byte[] publicKey;


    @Column(name = "privatekey", columnDefinition = "bytea")
    private byte[] privateKey;

    public KeysEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}

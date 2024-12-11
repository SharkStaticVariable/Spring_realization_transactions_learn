package org.example.spring_realization_transactions.service;

import jakarta.annotation.PostConstruct;
import org.example.spring_realization_transactions.entity.KeysEntity;
import org.example.spring_realization_transactions.repository.KeysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class EncryptDecryptService {

    @Autowired
    public KeysRepository keysRepository;

    public static Map<String, Object> map = new HashMap<>();

    @PostConstruct
    public void init() {
        // Load keys from database if they exist
        KeysEntity keysEntity = keysRepository.findById(1).orElse(null);
        if (keysEntity != null) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");

                byte[] publicKeyBytes = keysEntity.getPublicKey();
                X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
                PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

                byte[] privateKeyBytes = keysEntity.getPrivateKey();
                PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
                PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

                map.put("publicKey", publicKey);
                map.put("privateKey", privateKey);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error loading keys from database: " + e.getMessage());
            }
        } else {
            createKeys();
        }
    }

    public void createKeys() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096); // 4096 bits is a large and secure key size
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            map.put("publicKey", publicKey);
            map.put("privateKey", privateKey);

            KeysEntity keysEntity = KeysEntity.builder()
                    .publicKey(publicKey.getEncoded())
                    .privateKey(privateKey.getEncoded())
                    .build();

            keysRepository.save(keysEntity);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error generating keys: " + e.getMessage());
        }
    }

    public String encryptMessage(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
            PublicKey publicKey = (PublicKey) map.get("publicKey");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypt = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypt); // directly return Base64-encoded string
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error encrypting message: " + e.getMessage());
        }
        return "";
    }

    public String decryptMessage(String encryptedMessage) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
            PrivateKey privateKey = (PrivateKey) map.get("privateKey");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            return new String(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error decrypting message: " + e.getMessage());
        }
        return "";
    }
}

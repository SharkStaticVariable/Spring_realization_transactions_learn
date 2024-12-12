CREATE TABLE IF NOT EXISTS accounts(
                                       id SERIAL PRIMARY KEY,
                                       balance DECIMAL,
                                       isActive BOOLEAN,
                                       isLocked BOOLEAN
);
ALTER TABLE accounts
    ADD COLUMN balance DECIMAL,              -- добавление столбца balance
    ADD COLUMN creationDate DATE,            -- добавление столбца creationDate
    ADD COLUMN number INT;


ALTER TABLE accounts
    ADD COLUMN user_id BIGINT,
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE;



ALTER TABLE accounts
    add column number int ;

ALTER TABLE accounts
    drop column isLocked  ;

CREATE TABLE IF NOT EXISTS users(
                                    id SERIAL PRIMARY KEY,
                                    first_name VARCHAR NOT NULL,
                                    last_name VARCHAR NOT NULL,
                                    age INT,
                                    phoneNumber VARCHAR,
                                    address VARCHAR,
                                    documentNumber VARCHAR,
                                    email VARCHAR,
                                    accounts_id INTEGER REFERENCES accounts(id)
);

-- Добавляем новый столбец для хранения ID счета
ALTER TABLE users
    ADD COLUMN account_id INT;

-- Добавляем внешний ключ для связи с таблицей accounts
ALTER TABLE users
    ADD CONSTRAINT fk_account_id
        FOREIGN KEY (account_id) REFERENCES accounts(id);


ALTER TABLE users
    add column password varchar ;
ALTER TABLE users
    drop column accounts_id  ;
ALTER TABLE users
    add column middleName varchar ;

ALTER TABLE users
    drop column documentNumber  ;
ALTER TABLE users
    add column username varchar ;

CREATE TABLE roles(
                      id SERIAL PRIMARY KEY,
                      name varchar(100) not null
);

CREATE TABLE users_roles(
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,
                            FOREIGN KEY(user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id),
                            UNIQUE (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS keys(
                                   id SERIAL PRIMARY KEY,
                                   publicKey varchar(4096),
                                   privateKey varchar(4096)

);
DELETE FROM keys
WHERE id = 1;

ALTER TABLE keys
    ALTER COLUMN publicKey
        SET DATA TYPE bytea
        USING publicKey::bytea;

UPDATE users
SET roles = 'ADMIN'
WHERE id = 1; -- Замените 123 на идентификатор пользователя


ALTER TABLE users ALTER COLUMN address TYPE VARCHAR(500); -- Adjust the length as per your requirements

ALTER TABLE users ALTER COLUMN username TYPE VARCHAR(512);

ALTER TABLE users ALTER COLUMN password TYPE VARCHAR(4096);


-- Создание таблицы transactions
CREATE TABLE IF NOT EXISTS  transactions (
                              id SERIAL PRIMARY KEY,                    -- Уникальный идентификатор транзакции
                              sender_account_id BIGINT NOT NULL,           -- Идентификатор счёта отправителя
                              receiver_account_id BIGINT NOT NULL,         -- Идентификатор счёта получателя
                              amount DECIMAL(15, 2) NOT NULL CHECK (amount > 0),  -- Сумма транзакции
                              type VARCHAR(50) NOT NULL,                  -- Тип транзакции (например, 'DEPOSIT', 'TRANSFER')
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- Дата и время создания транзакции
                              description TEXT,                           -- Описание транзакции

    -- Связи с таблицей accounts
                              CONSTRAINT fk_sender_account FOREIGN KEY (sender_account_id) REFERENCES accounts (id) ON DELETE SET NULL,
                              CONSTRAINT fk_receiver_account FOREIGN KEY (receiver_account_id) REFERENCES accounts (id) ON DELETE SET NULL
);

-- Создание таблицы transactions_log
CREATE TABLE IF NOT EXISTS transactions_log (
                                  id SERIAL PRIMARY KEY,                   -- Уникальный идентификатор записи лога
                                  transaction_id BIGINT NOT NULL,             -- Идентификатор связанной транзакции
                                  details TEXT NOT NULL,                      -- Детали лога
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- Дата и время записи

    -- Связь с таблицей transactions
                                  CONSTRAINT fk_transaction FOREIGN KEY (transaction_id) REFERENCES transactions (id) ON DELETE CASCADE
);


SELECT setval('public.transactions_id_seq', 1, false);

DELETE FROM transactions WHERE id = 10;

SELECT pg_get_serial_sequence('transactions', 'id');

ALTER SEQUENCE public.transactions_id_seq RESTART WITH 1;
SELECT last_value FROM transactions_id_seq;
ALTER SEQUENCE transactions_id_seq RESTART WITH 1;

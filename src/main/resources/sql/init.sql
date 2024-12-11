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

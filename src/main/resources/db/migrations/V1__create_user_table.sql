CREATE TABLE produtos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE
);

INSERT INTO produtos(id, name, email)
VALUES (1, 'QUARKED', 'email@email.gov.email');
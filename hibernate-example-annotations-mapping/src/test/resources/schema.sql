CREATE TABLE IF NOT EXISTS engines (
    id IDENTITY PRIMARY KEY,
    model VARCHAR(25) NOT NULL,
    power INTEGER NOT NULL
);

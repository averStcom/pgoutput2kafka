-- Пользователь для логической репликации (подключаться будет subscriber)
CREATE ROLE replicator WITH LOGIN REPLICATION PASSWORD 'replicator';

-- Тестовая схема/таблица
CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.accounts (
  id BIGSERIAL PRIMARY KEY,
  owner TEXT NOT NULL,
  balance NUMERIC(14,2) NOT NULL DEFAULT 0,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

INSERT INTO app.accounts(owner, balance) VALUES
  ('Alice', 100.00),
  ('Bob',   50.00);

-- Публикация для pgoutput
DROP PUBLICATION IF EXISTS cdc_pub;
CREATE PUBLICATION cdc_pub FOR ALL TABLES;

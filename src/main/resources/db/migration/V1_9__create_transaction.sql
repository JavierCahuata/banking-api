CREATE TABLE transaction (
  transaction_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  movimiento_1 DECIMAL(10,2) NOT NULL,
  fromAccountNumber VARCHAR(50) NOT NULL,
  toAccountNumber VARCHAR(50) NOT NULL,
  PRIMARY KEY(transaction_id),
  INDEX IX_transaction_bank_account_id_1 (fromAccountNumber),
  INDEX IX_transaction_bank_account_id_2 (toAccountNumber)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
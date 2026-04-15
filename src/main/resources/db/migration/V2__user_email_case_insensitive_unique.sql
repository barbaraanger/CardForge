DROP INDEX IF EXISTS uk_user_account_email_lower;
CREATE UNIQUE INDEX IF NOT EXISTS uk_user_account_email_lower ON user_account (LOWER(email));
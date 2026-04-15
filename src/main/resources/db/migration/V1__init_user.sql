CREATE TABLE IF NOT EXISTS user_account (
                                            id UUID PRIMARY KEY,
                                            name VARCHAR(120) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
                             );

CREATE INDEX IF NOT EXISTS idx_user_account_email ON user_account (email);
CREATE INDEX IF NOT EXISTS idx_user_account_created_at ON user_account (created_at);
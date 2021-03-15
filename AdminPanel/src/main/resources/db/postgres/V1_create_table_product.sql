CREATE TABLE IF NOT EXISTS productSchema.product (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    price REAL NOT NULL,
    category_id TEXT NOT NULL,
    image TEXT,
);
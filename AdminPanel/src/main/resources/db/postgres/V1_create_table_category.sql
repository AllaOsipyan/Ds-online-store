CREATE TABLE IF NOT EXISTS productSchema.category (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    parentCategory_id integer,
);
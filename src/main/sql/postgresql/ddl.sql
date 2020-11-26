CREATE TABLE prices (
    id SERIAL PRIMARY KEY,
    price NUMERIC,
    sample_date TIMESTAMP WITH TIME ZONE,
    source VARCHAR
);

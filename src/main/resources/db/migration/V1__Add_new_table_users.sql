CREATE SCHEMA IF NOT EXISTS test_sh;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE test_sh.users(
    id UUID DEFAULT uuid_generate_v4(),
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    age INT4 NOT NULL,
    UNIQUE (id)
);
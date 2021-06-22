CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE test_sh.tasks(
    task_id UUID DEFAULT uuid_generate_v4(),
    title VARCHAR(50) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    UNIQUE (task_id)
);
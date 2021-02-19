CREATE TABLE tasks(
    task_id serial PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL
);
CREATE TABLE user_task(
    id integer,
    task_id integer,
    primary key(id, task_id),
    foreign key(id) references users(id),
    foreign key(task_id) references tasks(task_id) 
);
CREATE TABLE test_sh.user_task(
    id UUID references test_sh.users(id),
    task_id UUID references test_sh.tasks(task_id),
    primary key(id, task_id)
);
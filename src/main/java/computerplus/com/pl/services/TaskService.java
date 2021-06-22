package computerplus.com.pl.services;

import computerplus.com.pl.exceptions.NotFoundException;
import computerplus.com.pl.models.Task;
import computerplus.com.pl.repositiries.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity saveTask(Task task) {
        taskRepository.save(task);
           return ResponseEntity.ok().build();
    }

    public void deleteTaskFromDb(UUID id) throws NotFoundException {
        System.out.println("ID: " + id);
        taskRepository.findById(id).
                map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new NotFoundException("Task with id " + id + "  not found"));
    }

    public Optional<Task> findTaskById(UUID taskId) {
        return taskRepository.findById(taskId);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}

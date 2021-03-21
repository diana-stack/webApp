package computerplus.com.pl.controller;

import computerplus.com.pl.exceptions.NotFoundException;
import computerplus.com.pl.models.Task;
import computerplus.com.pl.models.User;
import computerplus.com.pl.repositiries.TaskRepository;
import computerplus.com.pl.repositiries.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("users", new User());
        return "startPage";
    }

    @RequestMapping("/findUserById")
    public String startFindById(Model model, User user) throws NotFoundException {
        Long userId = user.getId();
        repository.findById(userId)
                .map(findUser -> {
                    ResponseEntity.ok(findUser);
                    model.addAttribute("users", findUser);
                    return "index";
                }).orElseThrow(() -> new NotFoundException("User not found with id " + userId));
        return "index";
    }

    @RequestMapping("/findUserByName")
    public String startFindByName(Model model, User user) {      
        String userFisrtName = user.getFirstname();
        List<User> users = repository.findAll();
        List<User> resultUsers = new ArrayList<User>();
        users.forEach(findUser -> {
            if (userFisrtName.equals(findUser.getFirstname())) {        
                repository.findById(findUser.getId()).map(resultUser -> {
                    resultUsers.add(resultUser);
                    model.addAttribute("users", resultUsers);
                    return "index";
                });
            }
        });
        return "index";
    }
    
    @RequestMapping("/findUserByLastName")
    public String startFindByLastName(Model model, User user) {      
        String userLastName = user.getLastname();
        List<User> users = repository.findAll();
        List<User> resultUsers = new ArrayList<User>();
        users.forEach(findUser -> {
            if (userLastName.equals(findUser.getLastname())) {        
                repository.findById(findUser.getId()).map(resultUser -> {
                    resultUsers.add(resultUser);
                    model.addAttribute("users", resultUsers);
                    return "index";
                });
            }
        });
        return "index";
    }

    @GetMapping("/allUsers")
    public String viewAllUsers(Model model) {
        model.addAttribute("users", repository.findAll());
        return "/allUsers";
    }

    @GetMapping("/user")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/user";
    }    

    @PostMapping("/add")
    public String addNewUser(User user) {
        ResponseEntity.ok(repository.save(user));
        return "redirect:/allUsers";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long userId) throws NotFoundException {
        repository.findById(userId)
                .map(user -> {
                    repository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new NotFoundException("User not found with id " + userId));
        return "redirect:/allUsers";
    }
    
    @GetMapping("/task")
    public String createNewTask(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }
    
    @PostMapping("/addTask")
    public String addNewTask(Task task) {
        ResponseEntity.ok(taskRepository.save(task));
        return "redirect:/allTasks";
    }
    
    @GetMapping("/allTasks")
    public String viewAllTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "/allTasks";
    }
    
    @RequestMapping(value = "/deleteTask/{taskId}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Long taskId) throws NotFoundException {
        taskRepository.findById(taskId)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new NotFoundException("Task not found with id " + taskId));
        return "redirect:/allTasks";
    }
}


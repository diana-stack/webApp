package computerplus.com.pl.controller;

import computerplus.com.pl.exceptions.NotFoundException;
import computerplus.com.pl.models.Task;
import computerplus.com.pl.models.User;
import computerplus.com.pl.services.TaskService;
import computerplus.com.pl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("users", new User());
        return "startPage";
    }

    @RequestMapping("/findUserById")
    public Optional<User> findUserById(@PathVariable UUID userId) throws NotFoundException {
        return userService.findUserById(userId);
    }

    @RequestMapping("/findUserByName")
    public String findUserByName(Model model, User user) {
        model.addAttribute("users", userService.findUserByName(user));
        return "index";
    }
    
    @RequestMapping("/findUserByLastName")
    public String findUserByLastName(Model model, User user) {
        model.addAttribute("users", userService.findUserByLastName(user));
        return "index";
    }

    @GetMapping("/allUsers")
    public String viewAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/allUsers";
    }

    @GetMapping("/user")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/user";
    }    

    @PostMapping("/add")
    public String addNewUser(User user) {
        ResponseEntity.ok(userService.saveUser(user));
        return "redirect:/allUsers";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable UUID userId) throws NotFoundException {
        userService.deleteUserFromDb(userId);
        return "redirect:/allUsers";
    }
    
    @GetMapping("/task")
    public String createNewTask(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }
    
    @PostMapping("/addTask")
    public String addNewTask(Task task) {
        ResponseEntity.ok(taskService.saveTask(task));
        return "redirect:/allTasks";
    }
    
    @GetMapping("/allTasks")
    public String viewAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "/allTasks";
    }
    
    @RequestMapping(value = "/deleteTask/{taskId}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable UUID taskId) throws NotFoundException {
        taskService.deleteTaskFromDb(taskId);
        return "redirect:/allTasks";
    }
    
    @RequestMapping(value = "/viewUser/{userId}", method = RequestMethod.GET)
    public String viewUserById(Model model, @PathVariable UUID userId) throws NotFoundException {
        model.addAttribute("user", userService.findUserById(userId));
        return "/viewUser";
    }    
    
    @RequestMapping(value = "/viewTask/{taskId}", method = RequestMethod.GET)
    public String viewTaskById(Model model, @PathVariable UUID taskId) throws NotFoundException {
        model.addAttribute("task", taskService.findTaskById(taskId));
        return "/viewTask";
    }  
}


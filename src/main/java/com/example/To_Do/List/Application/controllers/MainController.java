package com.example.To_Do.List.Application.controllers;

import com.example.To_Do.List.Application.models.Task;
import com.example.To_Do.List.Application.security.CustomUserDetails;
import com.example.To_Do.List.Application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class MainController {

    private final TaskService taskService;

    @Autowired
    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/hello")
    public String sayHello(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("user", customUserDetails.getUser());
        System.out.println(customUserDetails.getUser());
        return "hello";
    }

    @GetMapping("/user")
    public String showTask(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        List<Task> tasks = taskService.findByUserId(customUserDetails.getUser().getId());
        model.addAttribute("tasks", tasks);
        return "show";
    }
    @GetMapping("/new")
    public String newTask(Model model){
        model.addAttribute("task", new Task());
        return"task/new";
    }
    @PostMapping("/tasks")
    public String create(@ModelAttribute("task") @Valid Task task,
                         BindingResult bindingResult){
        //personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "task/new";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        taskService.save(task, customUserDetails.getUser());
        return "redirect:/user";
    }
    @GetMapping("/task/{id}")
    public String showInfoForTask(@PathVariable("id") int id, Model model){
        model.addAttribute("task", taskService.findByTaskId(id).get());
        return"/task/show";
    }
    @DeleteMapping("/task/{id}")
    public String delete(@PathVariable("id") int id){
        taskService.delete(id);
        return "redirect:/user";
    }
}


package com.example.To_Do.List.Application.controllers;

import com.example.To_Do.List.Application.models.Task;
import com.example.To_Do.List.Application.security.CustomUserDetails;
import com.example.To_Do.List.Application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/user/{id}")
    public String showTask(@PathVariable("id") int id,
                           Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        List<Task> tasks = taskService.findByUserId(customUserDetails.getUser().getId());
        model.addAttribute("tasks", tasks);
        return "show";
    }
    @GetMapping("/showUserInfo")
    @Transactional
    public String showUserInfo(){


        return"hello";
    }
}

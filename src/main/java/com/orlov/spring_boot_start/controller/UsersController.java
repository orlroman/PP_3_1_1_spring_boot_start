package com.orlov.spring_boot_start.controller;

import com.orlov.spring_boot_start.entity.User;
import com.orlov.spring_boot_start.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
    
    private final UserService userService;
    
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/index";
    }
    
    @GetMapping(value = "/new")
    public String newPerson(@ModelAttribute("user") User User) {
        return "users/new";
    }
    
    @PostMapping(value = "/save")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.save(user);
        return "redirect:/users";
    }
    
    @GetMapping(value = "/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }
    
    @PostMapping(value = "/edit")
    public String update(@ModelAttribute(name = "user") @Valid User user, BindingResult bindingResult,
                       @RequestParam("id") int id) {
        
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.update(id, user);
        return "redirect:/users";
    }
    
    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
    
    
}

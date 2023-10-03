package com.springboot.controller;


import com.springboot.model.User;
import com.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "pages/index";
    }

    @GetMapping("/{id}")
    public String user(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "pages/user";
    }

    @GetMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        return "pages/newUser";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            return"pages/newUser";
        } else {
            userService.add(user);
        }
        return "redirect:/";
    }

    @GetMapping("/change")
    public String change(ModelMap model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "pages/change";
    }

    @PatchMapping("/change/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            return "pages/change";
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @DeleteMapping ("/deleteUser")
    public String delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}

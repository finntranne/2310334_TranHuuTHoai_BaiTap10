package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.StringUtils;
import java.util.List;

@Controller
@RequestMapping("admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("add")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User()); 
        return "admin/users/add";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdateUser(@ModelAttribute("user") User user, ModelMap model) {
        userService.save(user);
        model.addAttribute("message", "User saved successfully!");
        return new ModelAndView("redirect:/admin/users", model);
    }

    @RequestMapping("") 
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users/list";
    }

    @GetMapping("edit/{userId}")
    public String editUser(@PathVariable("userId") int userId, ModelMap model) {
        User user = userService.findById(userId).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "admin/users/edit"; 
        }
        model.addAttribute("message", "User not found!");
        return "redirect:/admin/users";
    }

    @GetMapping("delete/{userId}")
    public ModelAndView deleteUser(@PathVariable("userId") int userId, ModelMap model) {
        userService.deleteById(userId);
        model.addAttribute("message", "User is deleted!");
        return new ModelAndView("redirect:/admin/users", model);
    }

    @RequestMapping("search")
    public String searchUsers(ModelMap model, @RequestParam(name = "username", required = false) String username) {
        List<User> users = null;
        if (StringUtils.hasText(username)) {
            users = userService.findByUsernameContaining(username);
        } else {
            users = userService.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("searchUsername", username);
        return "admin/users/search"; 
    }
}
package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import jakarta.servlet.http.HttpSession; 
import java.util.Optional; 

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              ModelMap model,
                              HttpSession session) {
        
        Optional<User> userOptional = userService.findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get(); 
            String role = null; 
            if (user.getRoleid() == 1) {
                role = "ADMIN";
            } else if (user.getRoleid() == 2) {
                role = "USER";
            } else {
                model.addAttribute("error", "Tài khoản của bạn có vai trò không hợp lệ. Vui lòng liên hệ quản trị viên.");
                return "login"; 
            }

            session.setAttribute("isLoggedIn", true);
            session.setAttribute("user", user);
            session.setAttribute("userRole", role);

            if ("ADMIN".equals(role)) {
                return "redirect:/admin/categories"; 
            } else {
                return "redirect:/user/home"; 
            }
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "login"; 
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/login";
    }
    @GetMapping("/user/home")
    public String userHome() {
        return "users/userhome"; 
    }
}

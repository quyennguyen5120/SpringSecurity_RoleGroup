package com.example.spring_security_lab1.Controller;

import com.example.spring_security_lab1.Dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public String home(HttpSession session, Model model){
        UserDto user = (UserDto) session.getAttribute("user");
        model.addAttribute("user", user);
        return "UserIndex";
    }

}

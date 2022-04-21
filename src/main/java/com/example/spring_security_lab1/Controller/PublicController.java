package com.example.spring_security_lab1.Controller;

import com.example.spring_security_lab1.Dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublicController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("objectU", new UserDto());
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/403")
    public String Accessdeni(){
        return "Accessdeni";
    }

}

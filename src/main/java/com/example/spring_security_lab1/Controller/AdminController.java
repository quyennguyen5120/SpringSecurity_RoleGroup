package com.example.spring_security_lab1.Controller;

import com.example.spring_security_lab1.Dto.UserDto;
import com.example.spring_security_lab1.Entity.Role;
import com.example.spring_security_lab1.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RoleRepository roleRepository;

    @Secured("ADMIN")
    @GetMapping("/")
    public String home(HttpSession session, Model model, HttpServletRequest request){
        UserDto user =  null;
        user = (UserDto) session.getAttribute("user");
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(c -> {
                        if(c.getName().equals("remember-me")){
                            byte[] decodedBytes = Base64.getDecoder().decode("YWRtaW46MTY1MTkwNzIxMjU3ODpkMWQ4NGJlMjJiYzg0ODk4NTA5OWMyMDlhNzE5ZTQ3Zg");
                            String decodedString = new String(decodedBytes);
                            System.out.println("zxc");
                        }
                    });
        }
        model.addAttribute("user", user);
        List<Role>  lstRole = roleRepository.findAll();
        model.addAttribute("lstRole", lstRole);
        return "AdminIndex";
    }
}

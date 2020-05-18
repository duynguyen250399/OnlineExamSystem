package com.system.exam.exam.controllers;

import com.system.exam.exam.entities.Role;
import com.system.exam.exam.entities.User;
import com.system.exam.exam.repositories.RoleRepo;
import com.system.exam.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("/login")
    public String login(Principal principal){
        return principal == null ? "login" : "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model, Principal principal){
        if(principal != null){
            return "redirect:/";
        }

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerAccount(User user, @RequestParam(name = "role") int roleId){
        String hashedPassword = passwordEncoder.encode(user.getHashedPassword());
        user.setHashedPassword(hashedPassword);
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = roleRepo.findById(roleId);
        Role role = optionalRole.isPresent() ? optionalRole.get() : null;
        if(role == null){
            return "error";
        }

        roles.add(role);
        user.setRoles(roles);

        User createdUser = this.userService.createUser(user);
        if(createdUser == null){
            return "error";
        }

        return "redirect:/login";
    }
}

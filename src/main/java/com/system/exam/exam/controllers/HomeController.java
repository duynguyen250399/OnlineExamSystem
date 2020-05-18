package com.system.exam.exam.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userDetails instanceof UserDetails){
            model.addAttribute("userDetails", (UserDetails)userDetails);
            Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) ((UserDetails) userDetails).getAuthorities();
            Optional<GrantedAuthority> optionalGrantedAuthority = authorities.stream().findFirst();
            if(optionalGrantedAuthority.isPresent()){
                if(optionalGrantedAuthority.get().getAuthority().equals("admin")){
                    return "admin";
                }
            }
            else {
                return "redirect:/login";
            }
        }

        return "user";
    }
}

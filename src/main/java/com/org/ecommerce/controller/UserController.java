package com.org.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.User;
import com.org.ecommerce.requests.ChangePasswordRequest;
import com.org.ecommerce.requests.CreateAdminRequest;
import com.org.ecommerce.service.UserServices;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;


        // authentication

        @GetMapping("/login")
        public String login() {
            return "userLogin";
        }

        @GetMapping("/register")
        public String register() {
            return "userRegister";
        }

        @PostMapping("/login")
        public RedirectView userLogin(
            Model model, 
            @ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes) {
            User authenticatedUser = userServices.authenticate(user.getEmail(), user.getPwd());
            System.out.println(user.getEmail());
            System.out.println(user.getPwd());
            if(authenticatedUser == null) {
                redirectAttributes.addFlashAttribute("user", authenticatedUser);
                return new RedirectView("/user/login");
            }
            model.addAttribute("error", "Invalid Credentials");
            return new RedirectView("/user/home");
        }

        @PostMapping("/register")
        public RedirectView register(
            Model model, 
            User user,
            RedirectAttributes redirectAttributes) {
            System.out.println(user.getFname());
            System.out.println(user.getLname());
            System.out.println(user.getEmail());
            System.out.println(user.getPwd());
            System.out.println(user.getAddress());
            System.out.println(user.getAge());

            User registeredUser = userServices.createUser(user);
            if(registeredUser != null) {
                redirectAttributes.addFlashAttribute("user", registeredUser);
                return new RedirectView("/user/login");
            }
            model.addAttribute("error", "Invalid Credentials");
            return new RedirectView("/user/login");
        }

        // home
        @GetMapping("/home")
        public String home() {
            return "userHome";
        }

    
}

package com.org.ecommerce.controller;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.requests.ChangePasswordRequest;
import com.org.ecommerce.requests.CreateAdminRequest;
import com.org.ecommerce.requests.LoginRequest;
import com.org.ecommerce.response.ErrorRes;
import com.org.ecommerce.response.LoginRes;
import com.org.ecommerce.service.AdminService;
import com.org.ecommerce.utils.JwtUtil;

import jakarta.servlet.http.HttpSession;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping(path="/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // @PostMapping("/login")
    // public String login(@Valid @RequestBody LoginRequest request){
    //     Admin admin = adminService.getAdminByUsername(request.getUsername());
    //     if(admin == null) return "admin not found";

    //     return adminService.getAdminByUsername(request.getUsername()).getUsername();
    // }


    @RestController
    public class AuthController {

        private final AuthenticationManager authenticationManager;


        private JwtUtil jwtUtil;
        public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
            this.authenticationManager = authenticationManager;
            this.jwtUtil = jwtUtil;

        }
        
        @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
        public RedirectView login(Model model, @ModelAttribute("loginRequest") LoginRequest loginReq, HttpSession session)  {

            try {
                String username = loginReq.getUsername();
                System.out.println(username);
                Admin admin = adminService.getAdminByUsername(username);
                
                if(admin == null) throw new Exception("incorrect username or password");
                
                boolean isPasswordCorrect = adminService.verifyPassword(
                    loginReq.getPassword(), 
                    admin.getSalt(), 
                    admin.getHasedPassword()
                    );
                
                if(!isPasswordCorrect) throw new Exception("incorrect username or password");

                String token = jwtUtil.createToken(admin);
                LoginRes loginRes = new LoginRes(username,token);

                session.setAttribute(token, loginRes);

                return new RedirectView("/admin/dashboard");

            }catch (BadCredentialsException e){
                System.out.println(e.getMessage());
                return new RedirectView("/admin/login");

            }catch (Exception e){
                // ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
                // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
                System.out.println(e.getMessage());

                return new RedirectView("/admin/login");
            }
        }
    
    @PostMapping("/admin/password")
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest paswd){
        String username = paswd.getUsername();

        Admin admin = adminService.getAdminByUsername(username);

        if(admin == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("admin not found");

        boolean isPasswordCorrect = adminService.verifyPassword(
            paswd.getOldPassword(), 
            admin.getSalt(), 
            admin.getHasedPassword()
            );
        if(!isPasswordCorrect) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("incorrect password");

        String newSalt = adminService.generateSalt();

        String newHashedPassword = adminService.hashPassword(
            paswd.getNewPassword(), 
            newSalt
            );

        admin.setHasedPassword(newHashedPassword);

        admin.setSalt(newSalt);

        adminService.updateAdmin(admin, admin.getId());

        return ResponseEntity.ok("password changed");
    
    }
    }



    @GetMapping("/list")
    public List<Admin> getAdmins(){
        return adminService.getAdmins();
    }

    @PostMapping(path="/create")
    public ResponseEntity createAdmin(@RequestBody CreateAdminRequest admin){
        
       try {
            Admin existingAdmin = adminService.getAdminByUsername(admin.getUsername());
            ErrorRes adminExistsErr =  new ErrorRes(HttpStatus.BAD_REQUEST, "username already exists");
            if(existingAdmin != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminExistsErr);
            
            Admin newAdmin = new Admin();

            String userSalt = adminService.generateSalt();
            String userHashedPassword = adminService.hashPassword(
                admin.getPassword(), userSalt);

            newAdmin.setUsername(admin.getUsername());
            newAdmin.setEmail(admin.getEmail());
            newAdmin.setHasedPassword(userHashedPassword);
            newAdmin.setSalt(userSalt);

            adminService.createAdmin(newAdmin);
    
            return ResponseEntity.ok(newAdmin);
       } catch (Exception e) {
        ErrorRes errRes = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);
    }

 
    }   
       // ui

        @GetMapping("/login")
        public String loginView(Model model) {
            // model.addAttribute("login", adminService);
            return "login";
        }

        @GetMapping("/dashboard")
        public String homeView() {
            return "dashboard";
        }


}

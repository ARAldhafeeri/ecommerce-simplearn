package com.org.ecommerce.controller;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.Category;
import com.org.ecommerce.modal.Product;
import com.org.ecommerce.requests.ChangePasswordRequest;
import com.org.ecommerce.requests.CreateAdminRequest;
import com.org.ecommerce.requests.LoginRequest;
import com.org.ecommerce.response.ErrorRes;
import com.org.ecommerce.response.LoginRes;
import com.org.ecommerce.service.AdminService;
import com.org.ecommerce.service.CategoryService;
import com.org.ecommerce.service.ProductService;
import com.org.ecommerce.service.ProductServiceImpl;
import com.org.ecommerce.service.PurchaseService;
import com.org.ecommerce.service.UserServices;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping(path="/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private PurchaseService purchasesService;

    @RestController
    public class AuthController {

        private final AuthenticationManager authenticationManager;


        private JwtUtil jwtUtil;
        public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
            this.authenticationManager = authenticationManager;
            this.jwtUtil = jwtUtil;

        }
        
        @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
        public RedirectView login(
            Model model, 
            @ModelAttribute("loginRequest") LoginRequest loginReq, 
            HttpSession session,
            RedirectAttributes redirectAttributes)  {

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

                session.setAttribute("token", loginRes.getToken());
                redirectAttributes.addFlashAttribute("message", "You successfully logged in");
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
    public RedirectView changePassword( 
        Model model, 
        @ModelAttribute("ChangePasswordRequest") ChangePasswordRequest paswd,
        RedirectAttributes redirectAttributes) {
            String username = paswd.getUsername();
            Admin admin = adminService.getAdminByUsername(username);
            redirectAttributes.addFlashAttribute("message", "No admin found with this username");
            if(admin == null) return new RedirectView("/admin/password");

            boolean isPasswordCorrect = adminService.verifyPassword(
                paswd.getOldPassword(), 
                admin.getSalt(), 
                admin.getHasedPassword()
                );
            redirectAttributes.addFlashAttribute("message", "incorrect old password");
            if(!isPasswordCorrect) return new RedirectView("/admin/password");

            String newSalt = adminService.generateSalt();

            String newHashedPassword = adminService.hashPassword(
                paswd.getNewPassword(), 
                newSalt
                );

            admin.setHasedPassword(newHashedPassword);

            admin.setSalt(newSalt);

            adminService.updateAdmin(admin, admin.getId());
            redirectAttributes.addFlashAttribute("message", "password changed");
            return new RedirectView("/admin/password");
        
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
        public String homeView(HttpSession session, Model model) {
            String token = (String) session.getAttribute("token");
            System.out.println(token);
            return "dashboard";
        }

        @GetMapping("/password")
        public String passwordView(HttpSession session, Model model) {
            return "password";
        }

        @GetMapping("/logout")
        public String logout(HttpSession session, Model model) {
            session.removeAttribute("token");
            return "login";
        }

        // products 

        @GetMapping("/products")
        public String productView(HttpSession session, Model model) {
            System.out.println(categoryService.getCategoriesDropDown());
            model.addAttribute("catogry", categoryService.getCategoriesDropDown());
            model.addAttribute("products", productService.getAllProducts());
            return "products";
        }

        
        @PostMapping("/products/create")
        public RedirectView createProduct(@ModelAttribute("Product") Product body,  RedirectAttributes redirectAttributes){
            body.setDateAdded(new java.util.Date().toString());
            Product created = productService.createProduct(body);
            if (created == null) {
                redirectAttributes.addFlashAttribute("message", "product not created");
                return new RedirectView("/admin/products");
            }
            redirectAttributes.addFlashAttribute("message", "product created");
            return new RedirectView("/admin/products");
        }

        @PostMapping("/products/update")
        public RedirectView updateProduct(
            @ModelAttribute("Product") Product body,
            RedirectAttributes redirectAttributes, 
            @RequestParam Long id){

            body.setID(id);

            productService.updateProduct(body);
            redirectAttributes.addFlashAttribute("message", "product updated");
            return new RedirectView("/admin/products");
        }


        @PostMapping("/products/delete")
        public RedirectView deleteProduct(@RequestParam Long id, RedirectAttributes redirectAttributes){
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("message", "password deleted");
            return new RedirectView("/admin/products");
        }


        // categories
        
        @GetMapping("/categories")
        public String categoryView(HttpSession session, Model model) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories";
        }

        @PostMapping("/categories/create")
        public RedirectView createCategory(@ModelAttribute("Category") Category body,  RedirectAttributes redirectAttributes){

            com.org.ecommerce.modal.Category created = categoryService.createCategory(body);
            if (created == null) {
                redirectAttributes.addFlashAttribute("message", "category not created");
                return new RedirectView("/admin/categories");
            }
            redirectAttributes.addFlashAttribute("message", "category created");
            return new RedirectView("/admin/categories");
        }

        @PostMapping("/categories/update")
        public RedirectView updateCategory(
            @ModelAttribute("Category") Category body,
            RedirectAttributes redirectAttributes, 
            @RequestParam Long id){

            body.setID(id);

            categoryService.updateCategory(body);
            redirectAttributes.addFlashAttribute("message", "category updated");
            return new RedirectView("/admin/categories");
        }

        @PostMapping("/categories/delete")
        public RedirectView deleteCategory(@RequestParam Long id, RedirectAttributes redirectAttributes){
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("message", "category deleted");
            return new RedirectView("/admin/categories");
        }

        // users 
        @GetMapping("/users")
        public String userView(HttpSession session, Model model) {
            model.addAttribute("users", userServices.getAllUsers());
            return "users";
        }

        // purchases
        @GetMapping("/purchases")
        public String purchaseView(HttpSession session, Model model) {
            model.addAttribute("purchases", purchasesService.getAllItems());
            return "purchases";
        }
}

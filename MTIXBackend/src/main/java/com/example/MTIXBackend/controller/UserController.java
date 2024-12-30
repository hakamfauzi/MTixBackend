package com.example.MTIXBackend.controller;

import com.example.MTIXBackend.model.Keranjang;
import com.example.MTIXBackend.service.KeranjangService;
import com.example.MTIXBackend.controller.KeranjangController;
import com.example.MTIXBackend.model.User;
import com.example.MTIXBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users") // Define base URL for User-related operations
public class UserController {

    //////////////////////////////////////////////////////// Attributes and Contructors
    private final UserService userService;
    private final KeranjangController keranjangController;

    @Autowired
    public UserController(UserService userService, KeranjangController keranjangController) {
        this.userService = userService;
        this.keranjangController = keranjangController;  // Ensure proper initialization
    }

    //////////////////////////////////////////////////////// Business Methods
    @PostMapping("/registrasi")
    public ResponseEntity<?> registrasi(@RequestBody User user) {
        // Check if user with the same email already exists
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("message", "Email is already in use"));
        }

        // Create a new Keranjang for the user
        Keranjang newKeranjang = new Keranjang();
        newKeranjang = keranjangController.createKeranjangUser(newKeranjang);

        // Set the Keranjang for the user
        user.setKeranjang(newKeranjang);

        // Create the user
        User createdUser = userService.createUser(user);

        // Return the created user
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());

        if (authenticatedUser == null) {
            throw new RuntimeException("Invalid email or password");
        }

        return authenticatedUser;
    }

    //////////////////////////////////////////////////////// CRUD Methods
    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers(); // Delegate to the service layer
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id); // Get user by ID
    }

    // Create a new user
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        // First create the Keranjang
        Keranjang newKeranjang = new Keranjang();
        // Set fields for Keranjang if needed (do not set keranjang_id, let it auto-generate)
        newKeranjang = keranjangController.createKeranjang(newKeranjang); // Now correctly use the instance

        // Set the keranjang_id in the user object
        user.setKeranjang(newKeranjang); // Assuming a one-to-one or many-to-one relationship

        // Now create the user with the keranjang_id
        return userService.createUser(user);
    }

    // Update a user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setUser_id(id); // Set the ID in the user object
        return userService.updateUser(user); // Update the user
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id); // Delete the user by ID
    }
}

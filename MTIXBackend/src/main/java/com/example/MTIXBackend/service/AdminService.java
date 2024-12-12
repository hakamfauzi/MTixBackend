package com.example.MTIXBackend.service;

import com.example.MTIXBackend.model.Admin;
import com.example.MTIXBackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElse(null); // Return the user if found, else return null
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin); // Save the new user to the database
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin); // Save the updated user to the database
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id); // Delete the user by ID
    }
}

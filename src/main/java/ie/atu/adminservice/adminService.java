package ie.atu.adminservice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class adminService {

    private final adminRepo adminRepo;

    public adminService(adminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public adminUser createAdmin(String username, String email, String role) {
        adminUser newAdmin = new adminUser();
        newAdmin.setUsername(username);
        newAdmin.setEmail(email);
        newAdmin.setRole(role);
        return adminRepo.save(newAdmin);
    }

    public Optional<adminUser> getAdminById(String adminId) {
        return adminRepo.findById(adminId);
    }

    public List<adminUser> getAllAdmins() {
        return adminRepo.findAll();
    }

    public adminUser updateAdmin(String adminId, String username, String email, String role) {
        Optional<adminUser> existingAdmin = adminRepo.findById(adminId);
        if (existingAdmin.isPresent()) {
            adminUser admin = existingAdmin.get();
            admin.setUsername(username);
            admin.setEmail(email);
            admin.setRole(role);
            return adminRepo.save(admin);
        }
        return null;
    }

    public boolean deleteAdmin(String adminId) {
        if (adminRepo.existsById(adminId)) {
            adminRepo.deleteById(adminId);
            return true;
        }
        return false;
    }
}

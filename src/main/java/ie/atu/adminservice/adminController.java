package ie.atu.adminservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class adminController {

    private final adminService adminService;

    public adminController(adminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<adminUser> createAdmin(@RequestParam String username,
                                                 @RequestParam String email,
                                                 @RequestParam String role) {
        adminUser admin = adminService.createAdmin(username, email, role);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<adminUser> getAdminById(@PathVariable String id) {
        Optional<adminUser> admin = adminService.getAdminById(id);
        return admin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<adminUser>> getAllAdmins() {
        List<adminUser> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<adminUser> updateAdmin(@PathVariable String id,
                                                 @RequestParam String username,
                                                 @RequestParam String email,
                                                 @RequestParam String role) {
        adminUser updatedAdmin = adminService.updateAdmin(id, username, email, role);
        if (updatedAdmin != null) {
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String id) {
        boolean isDeleted = adminService.deleteAdmin(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

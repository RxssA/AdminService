package ie.atu.adminservice;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class adminServiceTest {

    @Mock
    private adminRepo adminRepo;

    @InjectMocks
    private adminService adminService;

    public adminServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAdmin() {
        adminUser mockAdmin = new adminUser();
        mockAdmin.setId("1");
        mockAdmin.setUsername("JohnDoe");
        mockAdmin.setEmail("john.doe@example.com");
        mockAdmin.setRole("Admin");

        when(adminRepo.save(any(adminUser.class))).thenReturn(mockAdmin);

        adminUser createdAdmin = adminService.createAdmin("JohnDoe", "john.doe@example.com", "Admin");

        assertNotNull(createdAdmin);
        assertEquals("JohnDoe", createdAdmin.getUsername());
        assertEquals("Admin", createdAdmin.getRole());
    }

    @Test
    void testGetAdminById() {
        adminUser mockAdmin = new adminUser();
        mockAdmin.setId("1");
        mockAdmin.setUsername("JaneDoe");

        when(adminRepo.findById("1")).thenReturn(Optional.of(mockAdmin));

        Optional<adminUser> retrievedAdmin = adminService.getAdminById("1");

        assertTrue(retrievedAdmin.isPresent());
        assertEquals("JaneDoe", retrievedAdmin.get().getUsername());
    }

    @Test
    void testGetAllAdmins() {
        adminUser admin1 = new adminUser();
        admin1.setId("1");
        admin1.setUsername("Admin1");

        adminUser admin2 = new adminUser();
        admin2.setId("2");
        admin2.setUsername("Admin2");

        List<adminUser> admins = new ArrayList<>();
        admins.add(admin1);
        admins.add(admin2);

        when(adminRepo.findAll()).thenReturn(admins);

        List<adminUser> retrievedAdmins = adminService.getAllAdmins();

        assertEquals(2, retrievedAdmins.size());
    }

    @Test
    void testUpdateAdmin() {
        adminUser mockAdmin = new adminUser();
        mockAdmin.setId("1");
        mockAdmin.setUsername("OldName");

        when(adminRepo.findById("1")).thenReturn(Optional.of(mockAdmin));
        when(adminRepo.save(any(adminUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        adminUser updatedAdmin = adminService.updateAdmin("1", "NewName", "new.email@example.com", "Admin");

        assertNotNull(updatedAdmin);
        assertEquals("NewName", updatedAdmin.getUsername());
        assertEquals("new.email@example.com", updatedAdmin.getEmail());
    }

    @Test
    void testDeleteAdmin() {
        when(adminRepo.existsById("1")).thenReturn(true);

        boolean isDeleted = adminService.deleteAdmin("1");

        assertTrue(isDeleted);
        verify(adminRepo, times(1)).deleteById("1");
    }
}

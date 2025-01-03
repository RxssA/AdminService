package ie.atu.adminservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(adminController.class)
class adminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private adminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAdmin() throws Exception {
        adminUser mockAdmin = new adminUser();
        mockAdmin.setId("1");
        mockAdmin.setUsername("TestAdmin");
        mockAdmin.setEmail("test@admin.com");
        mockAdmin.setRole("Admin");

        Mockito.when(adminService.createAdmin("TestAdmin", "test@admin.com", "Admin"))
                .thenReturn(mockAdmin);

        mockMvc.perform(post("/api/admins")
                        .with(csrf()) // Add CSRF token
                        .with(user("admin").roles("ADMIN")) // Simulate authenticated user
                        .param("username", "TestAdmin")
                        .param("email", "test@admin.com")
                        .param("role", "Admin"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("TestAdmin"))
                .andExpect(jsonPath("$.email").value("test@admin.com"))
                .andExpect(jsonPath("$.role").value("Admin"));
    }

    @Test
    void testGetAdminById() throws Exception {
        adminUser mockAdmin = new adminUser();
        mockAdmin.setId("1");
        mockAdmin.setUsername("TestAdmin");
        mockAdmin.setEmail("test@admin.com");
        mockAdmin.setRole("Admin");

        Mockito.when(adminService.getAdminById("1")).thenReturn(Optional.of(mockAdmin));

        mockMvc.perform(get("/api/admins/1")
                        .with(user("admin").roles("ADMIN"))) // Simulate authenticated user
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("TestAdmin"))
                .andExpect(jsonPath("$.email").value("test@admin.com"))
                .andExpect(jsonPath("$.role").value("Admin"));
    }

    @Test
    void testGetAdminByIdNotFound() throws Exception {
        Mockito.when(adminService.getAdminById("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/admins/1")
                        .with(user("admin").roles("ADMIN"))) // Simulate authenticated user
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllAdmins() throws Exception {
        List<adminUser> mockAdmins = new ArrayList<>();
        adminUser admin1 = new adminUser();
        admin1.setId("1");
        admin1.setUsername("Admin1");
        admin1.setEmail("admin1@example.com");
        admin1.setRole("Admin");

        adminUser admin2 = new adminUser();
        admin2.setId("2");
        admin2.setUsername("Admin2");
        admin2.setEmail("admin2@example.com");
        admin2.setRole("User");

        mockAdmins.add(admin1);
        mockAdmins.add(admin2);

        Mockito.when(adminService.getAllAdmins()).thenReturn(mockAdmins);

        mockMvc.perform(get("/api/admins")
                        .with(user("admin").roles("ADMIN"))) // Simulate authenticated user
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("Admin1"))
                .andExpect(jsonPath("$[1].username").value("Admin2"));
    }

    @Test
    void testUpdateAdmin() throws Exception {
        adminUser mockAdmin = new adminUser();
        mockAdmin.setId("1");
        mockAdmin.setUsername("UpdatedAdmin");
        mockAdmin.setEmail("updated@admin.com");
        mockAdmin.setRole("Admin");

        Mockito.when(adminService.updateAdmin("1", "UpdatedAdmin", "updated@admin.com", "Admin"))
                .thenReturn(mockAdmin);

        mockMvc.perform(put("/api/admins/1")
                        .with(csrf()) // Add CSRF token
                        .with(user("admin").roles("ADMIN")) // Simulate authenticated user
                        .param("username", "UpdatedAdmin")
                        .param("email", "updated@admin.com")
                        .param("role", "Admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("UpdatedAdmin"))
                .andExpect(jsonPath("$.email").value("updated@admin.com"))
                .andExpect(jsonPath("$.role").value("Admin"));
    }

    @Test
    void testDeleteAdmin() throws Exception {
        Mockito.when(adminService.deleteAdmin("1")).thenReturn(true);

        mockMvc.perform(delete("/api/admins/1")
                        .with(csrf()) // Add CSRF token
                        .with(user("admin").roles("ADMIN"))) // Simulate authenticated user
                .andExpect(status().isNoContent());
    }
}

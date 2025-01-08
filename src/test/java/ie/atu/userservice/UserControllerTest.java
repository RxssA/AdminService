package ie.atu.userservice;

import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }



    @Test
    void getAllUsers_ShouldReturnUsers() throws Exception {
        // Mocking the service method
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        // Perform the request and verify the result
        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void deleteUserById_ShouldReturnNoContent() throws Exception {
        // Perform the request and verify the result
        mockMvc.perform(delete("/api/users/123"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUserById("123");
    }
}

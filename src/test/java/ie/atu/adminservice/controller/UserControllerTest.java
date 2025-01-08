package ie.atu.adminservice.controller;

import ie.atu.adminservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnResponse() {
        when(userService.getAllUsers()).thenReturn(java.util.Collections.emptyList());

        ResponseEntity<?> response = userController.getAllUsers();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void deleteUser_ShouldReturnNoContent() {
        String userId = "1";
        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUser(userId);
    }
}

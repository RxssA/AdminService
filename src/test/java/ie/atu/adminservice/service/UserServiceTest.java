package ie.atu.adminservice.service;

import ie.atu.adminservice.exception.ResourceNotFoundException;
import ie.atu.adminservice.model.User;
import ie.atu.adminservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnUsers() {
        when(userRepository.findAll()).thenReturn(java.util.Collections.emptyList());
        assertNotNull(userService.getAllUsers());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void deleteUser_ExistingId_ShouldDeleteUser() {
        String userId = "1";
        when(userRepository.existsById(userId)).thenReturn(true);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteUser_NonExistingId_ShouldThrowException() {
        String userId = "999";
        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository, never()).deleteById(userId);
    }
}

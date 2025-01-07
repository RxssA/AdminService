package ie.atu.adminservice.service;

import ie.atu.adminservice.model.User;
import ie.atu.adminservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnUsers() {
        // Arrange
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // Act
        List<?> users = userService.getAllUsers();

        // Assert
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("1", ((User) users.get(0)).getId());
    }

    @Test
    void deleteUser_ShouldCallRepositoryDelete() {
        // Arrange
        String userId = "1";
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}

package ie.atu.adminservice;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<?> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
package ie.atu.adminservice;

import ie.atu.adminservice.model.Booking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8081/api/users")
public interface UserServiceClient {
    @GetMapping("/{id}")
    ResponseEntity<Booking> getUserById(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<Booking>> getAllUsers();
}

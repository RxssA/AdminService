package ie.atu.adminservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ie.atu.adminservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}

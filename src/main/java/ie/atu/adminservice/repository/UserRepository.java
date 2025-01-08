package ie.atu.adminservice.repository;

import ie.atu.adminservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

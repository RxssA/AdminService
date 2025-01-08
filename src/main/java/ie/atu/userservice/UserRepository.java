package ie.atu.userservice;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableFeignClients
public interface UserRepository extends MongoRepository<User, String> {
}

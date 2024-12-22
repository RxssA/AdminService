package ie.atu.adminservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface adminRepo extends MongoRepository<adminUser, String> {
}

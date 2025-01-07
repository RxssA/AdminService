package ie.atu.adminservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ie.atu.adminservice.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
package ie.atu.adminservice;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "ie.atu.adminservice.repository",
        mongoTemplateRef = "bookingMongoTemplate"
)
public class BookingServiceMongo {
    @Bean(name = "bookingMongoTemplate")
    public MongoTemplate bookingMongoTemplate(MongoClient mongoClient) throws Exception {
        return new MongoTemplate(mongoClient, "booking");
    }

    @Bean
    public MongoClient bookingMongoClient() {
        return MongoClients.create("mongodb://localhost:27017/bookingdb");
    }
}

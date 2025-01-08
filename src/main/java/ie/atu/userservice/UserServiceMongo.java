package ie.atu.userservice;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "ie.atu.userservice",
        mongoTemplateRef = "userMongoTemplate"
)
public class UserServiceMongo {
    @Bean(name = "userMongoTemplate")
    public MongoTemplate userMongoTemplate(MongoClient mongoClient) throws Exception {
        return new MongoTemplate(mongoClient, "user");
    }

    @Bean
    @Primary
    public MongoClient userMongoClient() {
        return MongoClients.create("mongodb://localhost:27017/userdb");
    }
}

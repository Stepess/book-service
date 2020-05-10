package ua.micro.bookservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"ua.micro.bookservice.persistence"})
public class MongoDbConfig {
}

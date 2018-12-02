package biz.lci.springboot.redditclone;

import biz.lci.springboot.redditclone.config.RedditcloneProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableConfigurationProperties(RedditcloneProperties.class)
public class RedditcloneApplication {

    @Autowired
    private RedditcloneProperties redditcloneProperties;

    public static void main(String[] args) {
        SpringApplication.run(RedditcloneApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner devOnlyRunner() {
        return args -> {
            System.out.println("This will only run when the profile is dev.");
        };
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println(redditcloneProperties.getWelcomeMsg());
        };
    }
}

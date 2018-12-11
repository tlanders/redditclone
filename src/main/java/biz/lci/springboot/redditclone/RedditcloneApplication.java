package biz.lci.springboot.redditclone;

import biz.lci.springboot.redditclone.config.RedditcloneProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(RedditcloneApplication.class);

    public static void main(String[] args) {
//        log.trace("RedditcloneApplication.main starting");
        SpringApplication.run(RedditcloneApplication.class, args);
        log.trace("RedditcloneApplication.main done");
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

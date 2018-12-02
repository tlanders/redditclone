package biz.lci.springboot.redditclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedditcloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditcloneApplication.class, args);
        System.out.println("Welcome to reddit clone.");
    }
}

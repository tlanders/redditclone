package biz.lci.springboot.redditclone;

import biz.lci.springboot.redditclone.config.RedditcloneProperties;
import biz.lci.springboot.redditclone.domain.Comment;
import biz.lci.springboot.redditclone.domain.Link;
import biz.lci.springboot.redditclone.repository.CommentRepository;
import biz.lci.springboot.redditclone.repository.LinkRepository;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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

    /**
     * Create pretty time bean that other beans can use
     * @return
     */
    @Bean
    PrettyTime prettyTime() {
        return new PrettyTime();
    }

    //@Bean
    CommandLineRunner databaseLoader(LinkRepository linkRepository, CommentRepository commentRepository) {
        return args -> {
            System.out.println("databaseLoader...");

            Link link = new Link("getting started with spring boot 2", "https://therealdanvega.com/spring-boot-2-docs");
            linkRepository.save(link);

            Comment comment = new Comment("Spring 2 link is awesome", link);
            commentRepository.save(comment);
            link.addComment(comment);

//            System.out.println(link.toString());
//            System.out.println(link.getComments());
        };
    }
}

package biz.lci.springboot.redditclone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Gets run after spplication starts.
 */
@Component
public class ExampleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("ExampleRunner....");
    }
}

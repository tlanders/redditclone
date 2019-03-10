package biz.lci.springboot.redditclone.repository;

import biz.lci.springboot.redditclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

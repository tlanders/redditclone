package biz.lci.springboot.redditclone.repository;

import biz.lci.springboot.redditclone.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}

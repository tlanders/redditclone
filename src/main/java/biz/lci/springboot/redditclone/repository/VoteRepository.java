package biz.lci.springboot.redditclone.repository;

import biz.lci.springboot.redditclone.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}

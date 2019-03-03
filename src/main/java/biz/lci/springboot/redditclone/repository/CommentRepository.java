package biz.lci.springboot.redditclone.repository;

import biz.lci.springboot.redditclone.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

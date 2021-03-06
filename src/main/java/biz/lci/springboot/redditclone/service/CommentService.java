package biz.lci.springboot.redditclone.service;

import biz.lci.springboot.redditclone.domain.Comment;
import biz.lci.springboot.redditclone.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {
    final private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment c) {
        return commentRepository.save(c);
    }
}

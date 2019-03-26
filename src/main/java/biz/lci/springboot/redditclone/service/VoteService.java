package biz.lci.springboot.redditclone.service;

import biz.lci.springboot.redditclone.domain.Vote;
import biz.lci.springboot.redditclone.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote v) {
        return voteRepository.save(v);
    }
}

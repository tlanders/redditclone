package biz.lci.springboot.redditclone.service;

import biz.lci.springboot.redditclone.domain.Link;
import biz.lci.springboot.redditclone.repository.LinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LinkService {
    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    public Optional<Link> findById(Long id) {
        return linkRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public Link save(Link l) {
        return linkRepository.save(l);
    }
}

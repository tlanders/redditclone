package biz.lci.springboot.redditclone.controller;

import biz.lci.springboot.redditclone.domain.Link;
import biz.lci.springboot.redditclone.repository.LinkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
//@RequestMapping("/links")
public class LinkController {
    private LinkRepository linkRepository;

    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

//    @GetMapping("/foo")
//    public String foo(Model model) {
//        model.addAttribute("pageTitle", "This is page foo");
//        return "foo";
//    }

    // list
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("links", linkRepository.findAll());
        return "link/list";
    }

//    // CRUD
//    @PostMapping("/create")
//    public Link create(@ModelAttribute Link link) {
//        return linkRepository.save(link);
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Link> read(@PathVariable Long id) {
//        return linkRepository.findById(id);
//    }
//    @PutMapping("/{id}")
//    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
//        return linkRepository.save(link);
//    }
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        linkRepository.deleteById(id);
//    }
}

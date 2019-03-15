package biz.lci.springboot.redditclone.controller;

import biz.lci.springboot.redditclone.domain.Comment;
import biz.lci.springboot.redditclone.domain.Link;
import biz.lci.springboot.redditclone.repository.CommentRepository;
import biz.lci.springboot.redditclone.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

//@RestController
@Controller
//@RequestMapping("/links")
public class LinkController {
    private LinkRepository linkRepository;
    private CommentRepository commentRepository;

    private Logger logger = LoggerFactory.getLogger(LinkController.class);

    public LinkController(LinkRepository linkRepository, CommentRepository commentRepository) {
        this.linkRepository = linkRepository;
        this.commentRepository = commentRepository;
    }

//    @GetMapping("/foo")
//    public String foo(Model model) {
//        model.addAttribute("pageTitle", "This is page foo");
//        return "foo";
//    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link",new Link());
        return "link/submit";
    }

    @PostMapping("/link/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            logger.debug("validation errors found when submitting a link");
            model.addAttribute("link", link);
            return "link/submit";
        } else {
            // save link
            linkRepository.save(link);
            logger.info("link was saved successfully");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/link/{id}";
        }
    }

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

    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id,Model model) {
        Optional<Link> link = linkRepository.findById(id);
        if( link.isPresent() ) {
            Link currentLink = link.get();
            Comment comment = new Comment();
            comment.setLink(currentLink);
            model.addAttribute("comment",comment);
            model.addAttribute("link",currentLink);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        } else {
            return "redirect:/";
        }
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() ) {
            logger.info("Something went wrong.");
        } else {
            logger.info("New Comment Saved!");
            commentRepository.save(comment);
        }
        return "redirect:/link/" + comment.getLink().getId();
    }

//    @PutMapping("/{id}")
//    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
//        return linkRepository.save(link);
//    }
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        linkRepository.deleteById(id);
//    }
}

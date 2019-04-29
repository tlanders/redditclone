package biz.lci.springboot.redditclone.service;

import biz.lci.springboot.redditclone.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class MailService {
    private final Logger log = LoggerFactory.getLogger(MailService.class);
    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final Environment env;
    private final MessageService messageService;

    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine, Environment e, MessageService ms) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.env = e;
        this.messageService = ms;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultiPart, boolean isHtml) {
        log.debug("Sending Email");

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom(env.getProperty("redditclone.email.replyto"));
            message.setSubject(subject);
            message.setText(content,isHtml);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
        }
    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String subject) {
        Locale locale = LocaleContextHolder.getLocale();
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("environment", env);
        String content = templateEngine.process(templateName,context);
        sendEmail(user.getEmail(),subject,content,false,true);
    }

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/activation",
                messageService.getMessage("redditclone.email.activation.subject", "User Activation Subject"));
                    }

    @Async
    public void sendWelcomeEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/welcome",
                messageService.getMessage("redditclone.email.welcome.subject", "User Welcome Subject"));
    }
}

package org.baeldung.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;
    @Value("${support.email}")
    private String from;
    @Autowired
    public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

	/*
	 * public void prepareAndSend(String recipient, String message,String subject )
	 * { MimeMessagePreparator messagePreparator = mimeMessage -> {
	 * MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	 * messageHelper.setFrom(from); messageHelper.setTo(recipient);
	 * messageHelper.setSubject(subject); String content =
	 * mailContentBuilder.build(message); messageHelper.setText(content, true); };
	 * try { mailSender.send(messagePreparator); } catch (MailException e) { //
	 * runtime exception; compiler will not force you to handle it } }
	 */
    public void prepareAndSend(final SimpleMailMessage simpleMailMessage, final String imageResourceName, String imageFileName, String [] messages) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
        	final FileSystemResource image = new FileSystemResource(new File(imageFileName));
        	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(from);
            messageHelper.setTo(simpleMailMessage.getTo());
            messageHelper.setSubject(simpleMailMessage.getSubject());
            System.out.println("image=   "+image);
            messageHelper.addInline(imageResourceName, new ClassPathResource("static/img/logo.png"));
            String content = mailContentBuilder.build(messages,simpleMailMessage.getText());
            messageHelper.setText(content, true);
            messageHelper.addAttachment("logo.svg", new ClassPathResource("abi_cl.svg"));
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }

}

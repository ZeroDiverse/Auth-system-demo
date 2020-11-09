package com.zerod.authdemo.services;

import com.zerod.authdemo.models.Mail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    /**
     * Send message helper function
     *
     * @param mail mail model to be sent
     */
    @Async
    public void sendMimeMessage(
            Mail mail) {
        try {
            javaMailSender.send(buildMimeMessagePreparator("angular-course@email.com", mail.getClient(), mail.getSubject(), mail.getBody(), "test"));
            log.info("Email sent successfully !!");
        } catch (MailException e) {
            log.error(e.getMessage());
        }
    }

    private MimeMessagePreparator buildMimeMessagePreparator(String from, String to, String subject, String mailContent, final String attachmentUrl) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);
            //String[] toMailList = to.toArray(new String[to.size()]);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(mailContent, true);
            /*
            if (StringUtils.isNotEmpty(attachmentUrl) && isHttpUrl(attachmentUrl)) {
                URL url = new URL(attachmentUrl);
                String filename = url.getFile();
                byte fileContent[] = getFileContent(url);
                messageHelper.addAttachment(filename, new ByteArrayResource(fileContent));
            }
             */
        };
        return messagePreparator;
    }
}

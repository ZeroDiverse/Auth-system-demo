package com.zerod.authdemo.mappers;

import com.zerod.authdemo.dto.MailDto;
import com.zerod.authdemo.models.Mail;
import org.springframework.stereotype.Component;

/**
 * Mail mapper class
 */
@Component
public class MailMapper {
    /**
     * Map dto to mail
     *
     * @param mailDto mail dto
     * @return mail
     */
    public Mail mapDtoToMail(MailDto mailDto) {
        //Builder
        return Mail.builder()
                //map the subject
                .subject(mailDto.getSubject())
                //map body
                .body(mailDto.getBody())
                .client(mailDto.getClient())
                .build();
    }
}

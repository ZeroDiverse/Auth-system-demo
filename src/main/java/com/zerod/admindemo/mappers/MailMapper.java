package com.zerod.admindemo.mappers;

import com.zerod.admindemo.dto.MailDto;
import com.zerod.admindemo.models.Mail;
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
        return Mail.builder()
                .subject(mailDto.getSubject())
                .body(mailDto.getBody())
                .client(mailDto.getClient())
                .build();
    }
}

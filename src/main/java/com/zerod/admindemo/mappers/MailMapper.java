package com.zerod.admindemo.mappers;

import com.zerod.admindemo.dto.MailDto;
import com.zerod.admindemo.models.Mail;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {
    public Mail mapDtoToMail(MailDto mailDto) {
        return Mail.builder()
                .subject(mailDto.getSubject())
                .body(mailDto.getBody())
                .client(mailDto.getClient())
                .build();
    }
}

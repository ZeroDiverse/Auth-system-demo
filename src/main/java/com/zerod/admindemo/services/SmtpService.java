package com.zerod.admindemo.services;

import com.zerod.admindemo.dto.MailDto;
import com.zerod.admindemo.mappers.MailMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SmtpService {
    private final MailService mailService;
    private final MailMapper mailMapper;

    public void sendMail(MailDto mailDto) {
        mailService.sendMimeMessage(mailMapper.mapDtoToMail(mailDto));
    }
}

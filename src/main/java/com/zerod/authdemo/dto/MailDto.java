package com.zerod.authdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mail dto class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    //Subject for mail
    private String subject;
    //Body for mail
    private String body;
    //Client of the mail
    private String client;
}

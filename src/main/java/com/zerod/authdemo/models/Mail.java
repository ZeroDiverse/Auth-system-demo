package com.zerod.authdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Mail model
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail {
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Client of the mail
    private String client;
    //Subject of the mail
    private String subject;
    //Body of the mail
    @Lob
    private String body;
    //Mail type
    @Enumerated(EnumType.STRING)
    private MailType mailType;

}

package com.digital.wallet.modelResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Email {
    private String senderUserName;
    private String receiver;
    private String subject;
    private String mailBody;
    private String password;
    private Date sentDate;

    public Email(String receiver,String subject,String mailBody){
        this.receiver=receiver;
        this.mailBody=mailBody;
        this.subject=subject;
        this.senderUserName="mppdigitalwalet@gmail.com";
        this.password="MPP@123@MiU";
        this.sentDate= new Date();
    }

}

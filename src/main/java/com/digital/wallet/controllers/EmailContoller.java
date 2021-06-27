package com.digital.wallet.controllers;

import com.digital.wallet.utils.EmailUtil;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
public class EmailContoller {


    @RequestMapping(value = "/sendEmailFromAPI")
    public String sendEmailAPI(@RequestHeader("reciever") String reciever,
                               @RequestHeader("subject") String subject,
                               @RequestHeader("body") String body) throws AddressException, MessagingException, IOException {
        EmailUtil.sendEmailFromAPI(reciever,subject,body);
        return "Email sent successfully via Header";
    }


}

package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Slack;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SlackClient {

    //for Slack Message
 /*  public String sendMessage(String text){
        return WebClient.create().post()
                .uri("https://hooks.slack.com/services/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")//link of channel you want to send text or data api to it.
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new SlackPayLoad(text))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    } */
}

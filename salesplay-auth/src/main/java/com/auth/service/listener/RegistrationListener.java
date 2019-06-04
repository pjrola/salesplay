package com.auth.service.listener;

import com.auth.service.entity.Account;
import com.auth.service.event.OnRegistrationCompleteEvent;
import com.auth.service.event.OnResendVerificationTokenEmail;
import com.auth.service.service.AccountService;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private AccountService accountService;

    private final RabbitTemplate rabbitTemplate;
    private final Exchange exchange;

    @Autowired
    public RegistrationListener(AccountService accountService, RabbitTemplate rabbitTemplate, Exchange exchange) {
        this.accountService = accountService;
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    /**
     * New user registered event
     * call exchange, passing account
     * details
     *
     * @param event
     */
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        String routingKey = "user.created";
        Account account = event.getAccount();
        String token = UUID.randomUUID().toString();
        accountService.createVerificationToken(account, token);
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, account.toString());
    }

    private void resendVerificationToken(OnResendVerificationTokenEmail event) {
        String routingKey = "user.resend.verification";
    }

    private void sendPasswordResetToken() {
        String routingKey = "user.resend.verification";
    }
}
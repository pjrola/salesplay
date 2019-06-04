package com.auth.service.event;

import org.springframework.context.ApplicationEvent;

public class OnSendPasswordResetToken extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public OnSendPasswordResetToken(Object source) {
        super(source);
    }
}

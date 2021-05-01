package com.zte.auth.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件源
 */
public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *  创建一个事件源
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }
}

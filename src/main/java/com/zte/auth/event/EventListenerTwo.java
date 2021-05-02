package com.zte.auth.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听者2
 */
@Component
@Slf4j
public class EventListenerTwo implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.info(event.getSource().toString());
    }
}

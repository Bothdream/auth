package com.zte.auth.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听者1
 *
 */
@Component
@Slf4j
public class EventListener1 {
    @EventListener
    public void listener(MyEvent myEvent) {
        log.info(myEvent.getSource().toString());
    }
}

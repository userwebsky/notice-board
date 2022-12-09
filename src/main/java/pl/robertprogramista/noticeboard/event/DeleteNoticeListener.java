package pl.robertprogramista.noticeboard.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DeleteNoticeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteNoticeListener.class);

    @Async
    @EventListener
    public void deleteNoticeEvent(DeleteNoticeEvent deleteNoticeEvent) {
        LOGGER.info("Notice deleted! {}", deleteNoticeEvent);
    }
}

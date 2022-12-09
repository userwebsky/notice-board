package pl.robertprogramista.noticeboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.robertprogramista.noticeboard.event.DeleteNoticeEvent;
import pl.robertprogramista.noticeboard.model.Notice;
import pl.robertprogramista.noticeboard.model.NoticeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public void create(Notice notice) {
        repository.save(notice);
    }

    public void update(Notice notice) {
        repository.findById(notice.getId()).ifPresent(foundNotice -> {
            foundNotice.updateNotice(notice);
            repository.save(foundNotice);
        });
    }

    public List<Notice> findAll() {
        return repository.findAll();
    }

    public void remove(Notice notice) {
        repository.deleteById(notice.getId());
        eventPublisher.publishEvent(new DeleteNoticeEvent(notice));
    }
}

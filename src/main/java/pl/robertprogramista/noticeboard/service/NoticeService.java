package pl.robertprogramista.noticeboard.service;

import org.springframework.stereotype.Service;
import pl.robertprogramista.noticeboard.model.Notice;
import pl.robertprogramista.noticeboard.model.NoticeRepository;

import java.util.List;

@Service
public class NoticeService {
    private NoticeRepository repository;

    public NoticeService(NoticeRepository repository) {
        this.repository = repository;
    }

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
    }
}

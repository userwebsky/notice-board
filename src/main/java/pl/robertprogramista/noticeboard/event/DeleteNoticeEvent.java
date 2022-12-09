package pl.robertprogramista.noticeboard.event;

import pl.robertprogramista.noticeboard.model.Notice;

public class DeleteNoticeEvent {
    private final Notice notice;

    public DeleteNoticeEvent(Notice notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " notice id: " + notice.getId();
    }
}

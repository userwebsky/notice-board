package pl.robertprogramista.noticeboard.contoller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import pl.robertprogramista.noticeboard.model.Notice;
import pl.robertprogramista.noticeboard.service.NoticeService;

import static org.junit.jupiter.api.Assertions.*;

class NoticeControllerTest {
    private NoticeController noticeController;

    @BeforeEach
    void setUp() {
        NoticeService noticeService = Mockito.mock(NoticeService.class);
        noticeController = new NoticeController(noticeService);
    }

    @Test
    void showNotices() {
       //given
       Model model = Mockito.mock(Model.class);
       Boolean isSort = true;

       //when
       String result = noticeController.showNotices(model, true);

       //then
       assertEquals(result, "index");
    }

    @Test
    void save() {
        //given
        Notice notice = Mockito.mock(Notice.class);

        //when
        String result = noticeController.update(notice);

        //then
        assertEquals(result, "index");
    }

    @Test
    void create() {
        //given
        Notice notice = Mockito.mock(Notice.class);

        //when
        String result = noticeController.create(notice);

        //then
        assertEquals(result, "index");
    }

    @Test
    void remove() {
        //given
        Notice notice = Mockito.mock(Notice.class);

        //when
        String result = noticeController.remove(notice);

        //then
        assertEquals(result, "index");
    }
}
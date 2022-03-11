package pl.robertprogramista.noticeboard.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.robertprogramista.noticeboard.model.Notice;
import pl.robertprogramista.noticeboard.service.NoticeService;

@Controller
@RequestMapping("/notices")
public class NoticeController {

    private NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    @GetMapping
    String showNotices(Model model, @RequestParam(defaultValue = "false") boolean isSort) {
        model.addAttribute("isSort", isSort);
        model.addAttribute("notices", service.findAll());
        return "index";
    }

    @PostMapping("/update")
    String update(Notice notice) {
        service.update(notice);
        return "index";
    }

    @PostMapping(value = "/create")
    String create(Notice notice) {
        service.create(notice);
        return "index";
    }

    @PostMapping("/remove")
    String remove(Notice notice) {
        service.remove(notice);
        return "index";
    }
}

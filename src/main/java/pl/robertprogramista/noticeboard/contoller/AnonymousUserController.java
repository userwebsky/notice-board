package pl.robertprogramista.noticeboard.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.robertprogramista.noticeboard.service.NoticeService;

@Controller
@RequestMapping("/")
public class AnonymousUserController {

    private final NoticeService service;

    public AnonymousUserController(NoticeService service) {
        this.service = service;
    }

    @GetMapping
    String showNotices(Model model, @RequestParam(defaultValue = "false") boolean isSort) {
        model.addAttribute("isSort", isSort);
        model.addAttribute("notices", service.findAll());
        return "index";
    }
}

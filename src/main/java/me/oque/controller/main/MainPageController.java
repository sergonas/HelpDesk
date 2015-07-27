package me.oque.controller.main;

import me.oque.entity.News;
import me.oque.service.SelectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

/**
 * Controller for main part
 *
 * Created by Dmitry Smorzhok on 11.07.15.
 */
@Controller
@RequestMapping(value = "/")
public class MainPageController {

    @Inject
    private SelectionService selectionService;

    @RequestMapping(method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.put("name", "placeHolder");
        return "mainPage";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews(ModelMap model) {
        return "redirect:/news/page/1";
    }

    @RequestMapping(value = "/news/page/{id}", method = RequestMethod.GET)
    public String getNewsPage(ModelMap model, @PathVariable("id") int id) {
        model.put("currentIndex", id);
        long totalRecords = selectionService.countAll(News.class);
        model.put("pages", Math.ceil(totalRecords / 10.0));
        List<News> newsList = selectionService.listObjectByPage(News.class, id, 10);
        model.put("newsList", newsList);
        return "news";
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public String getFaq(ModelMap model) {
        return "faq";
    }

    @RequestMapping(value = "/createticket", method = RequestMethod.GET)
    public String createTicket(ModelMap model) {
        return "createticket";
    }

    @RequestMapping(value = "/createticket", method = RequestMethod.POST)
    public String createTicketPost(ModelMap model) {
        return "redirect:/viewtickets";
    }

    @RequestMapping(value = "/viewtickets", method = RequestMethod.GET)
    public String viewTickets(ModelMap model) {
        return "viewtickets";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp(ModelMap model) {
        return "signup";
    }

}

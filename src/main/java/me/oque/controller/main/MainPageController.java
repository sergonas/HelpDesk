package me.oque.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for main part
 *
 * Created by Dmitry Smorzhok on 11.07.15.
 */
@Controller
@RequestMapping(value = "/")
public class MainPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.put("name", "placeHolder");
        return "mainPage";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews(ModelMap model) {
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

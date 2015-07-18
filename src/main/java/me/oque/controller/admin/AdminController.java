package me.oque.controller.admin;

import me.oque.dao.SelectionDao;
import me.oque.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Dmitry Smorzhok on 11.07.15.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private SelectionDao selectionDao;

	@RequestMapping(method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		model.addAttribute("user", new UserInfo());
        List<UserInfo> userList = selectionDao.getAll(UserInfo.class);
		model.addAttribute("users", userList);
		return "users";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") UserInfo user, BindingResult result) {

        selectionDao.save(user);

		return "redirect:/admin";
	}

	@RequestMapping(value = "/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {

		selectionDao.deleteById(UserInfo.class, userId);

		return "redirect:/admin";
	}
}
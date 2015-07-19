package me.oque.controller.admin;

import me.oque.entity.UserInfo;
import me.oque.service.UserService;
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
 * Controller for admin part
 *
 * Created by Dmitry Smorzhok on 11.07.15.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		model.addAttribute("user", new UserInfo());
        List<UserInfo> userList = userService.getAll(UserInfo.class);
		model.addAttribute("users", userList);
		return "users";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") UserInfo user, BindingResult result) {

        userService.saveUser(user);

		return "redirect:/admin";
	}

	@RequestMapping(value = "/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {

        userService.deleteById(UserInfo.class, userId);

		return "redirect:/admin";
	}
}
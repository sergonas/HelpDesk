package me.oque.controller.admin;

import me.oque.repo.UserRepository;
import me.oque.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dmitry Smorzhok on 11.07.15.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, BindingResult result) {

		userRepository.save(user);

		return "redirect:/admin";
	}

	@RequestMapping(value = "/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {

		userRepository.delete(userRepository.findOne(userId));

		return "redirect:/admin";
	}
}
package org.cex.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.cex.domain.User;
import org.cex.domain.form.RegisterForm;
import org.cex.service.ItemService;
import org.cex.service.UserService;
import org.cex.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/{id}")
	public String detail(Model model) {
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {

		return "/user/register";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {
		byte[] thedigest = null;
		String md5 = "";
		try {
			byte[] bytesOfMessage = registerForm.getPassword().getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			thedigest = md.digest(bytesOfMessage);
			md5 = new BigInteger(1, thedigest).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		User user = new User();
		user.setEmail(registerForm.getEmail());
		user.setDisplayName(registerForm.getDisplayName());
		user.setPassword(md5);
		user.setStatus(Constant.USER_STATUS_PENDING);

		userService.createUser(user);

		return "redirect:/login/";

	}
}
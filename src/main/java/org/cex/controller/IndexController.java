package org.cex.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cex.domain.Item;
import org.cex.domain.User;
import org.cex.domain.form.LoginForm;
import org.cex.service.ItemService;
import org.cex.service.UserService;
import org.cex.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class IndexController {
	Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/redirect/{id}/")
	public String redirect(@PathVariable("id") int id, HttpServletRequest request, Model model) {

		itemService.updateRatingByItemId(Constant.VIEW_RATING, id);

		return "redirect:" + request.getQueryString();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		return "/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request, Model model) {
		User user = userService.getUserByEmail(loginForm.getEmail());
		logger.info("email : " + loginForm.getEmail());
		String md5 = "";
		if (user != null) {
			byte[] thedigest = null;
			try {
				byte[] bytesOfMessage = loginForm.getPassword().getBytes("UTF-8");
				MessageDigest md = MessageDigest.getInstance("MD5");
				thedigest = md.digest(bytesOfMessage);
				md5 = new BigInteger(1, thedigest).toString(16);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			logger.info("password : " + user.getPassword());
			logger.info("theDigest: " + md5);
			if (user.getPassword().equals(md5)) {
				// valid
				request.getSession().setAttribute("user", user);
				return "redirect:/";
			} else {
				// password not match
				return "redirect:/login";
			}
		} else {

			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void search(@RequestBody String tagArrayString, Model model, HttpServletResponse res) {

		try {
			tagArrayString = java.net.URLDecoder.decode(tagArrayString, "UTF-8");

			logger.info(tagArrayString);

			ObjectMapper mapper = new ObjectMapper();

			// parse JSON
			List<String> tagList = Arrays.asList(mapper.readValue(tagArrayString, String[].class));

			List<Item> itemList = null;
			List<Integer> idList = new ArrayList<Integer>();

			if (tagList.size() > 0 && tagList.size() < Constant.TAG_SIZE + 1) {
				HashMap<Integer, Integer> hitMap = new HashMap<Integer, Integer>();

				for (String tag : tagList) {

					List<Integer> hitItemList = itemService.getItemIdByValue(tag);

					for (int itemId : hitItemList) {
						if (hitMap.get(itemId) != null) {
							hitMap.put(itemId, hitMap.get(itemId) + 1);
						} else {
							hitMap.put(itemId, 1);
						}
					}
				}

				// AND case
				for (int key : hitMap.keySet()) {
					if (hitMap.get(key) >= tagList.size()) {
						idList.add(key);
					}
				}

				if (idList.size() > 0) {

					itemService.updateRatingByItemIds(Constant.SEARCH_RATING, idList);

					itemList = itemService.getItemsByItemId(idList);
				}

			}
			try {
				OutputStream outputStream = res.getOutputStream();

				outputStream.write(mapper.writeValueAsString(itemList).getBytes("UTF-8"));

				outputStream.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException ioe) {
			logger.error(ioe);
		}

	}
}
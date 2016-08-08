package org.cex.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.cex.domain.Item;
import org.cex.domain.RatingHistory;
import org.cex.domain.Request;
import org.cex.domain.Tag;
import org.cex.domain.User;
import org.cex.domain.form.ItemForm;
import org.cex.service.ItemService;
import org.cex.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
	Logger logger = Logger.getLogger(ItemController.class);

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") int id, Model model) {
		Item item = itemService.getItemByItemId(id);

		model.addAttribute("item", item);
		return "/item/detail";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		List<Item> itemList = itemService.getItemsByUserId(user.getUserId());

		model.addAttribute("item", itemList);

		return "/item/list";
	}

	@RequestMapping(value = "/bid", method = RequestMethod.POST)
	public String bid(@RequestParam(value = "id", required = false) int id, Model model) {
		return "/";
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public @ResponseBody String publish(@RequestParam(value = "id", required = false) int id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Item item = itemService.getItemByItemId(id);

		if (item.getUserId() == user.getUserId()) {
			itemService.updateItemStatus(item.getItemId(), Constant.ITEM_STATUS_ACTIVE);
			return "OK";
		}

		return "/";
	}

	@RequestMapping(value = "/clone", method = RequestMethod.POST)
	public @ResponseBody String clone(@RequestParam(value = "id", required = false) int id, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Item item = itemService.getItemByItemId(id);

		if (item.getUserId() == user.getUserId()) {
			itemService.insertItem(item);
			return "OK";
		}

		return "/";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public @ResponseBody String cancel(@RequestParam(value = "id", required = false) int id, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Item item = itemService.getItemByItemId(id);

		if (item.getUserId() == user.getUserId()) {
			itemService.updateItemStatus(item.getItemId(), Constant.ITEM_STATUS_CANCEL);
			return "OK";
		}

		return "/";
	}

	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	public @ResponseBody String rate(@RequestBody String idString, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "{redirect:'" + Constant.loginPage + "'}";
		} else {
			int rawId = Integer.parseInt(idString);
			int id = Math.abs(rawId);

			// getRating log
			RatingHistory rh = itemService.getRatingHistory(user.getUserId(), id);
			logger.info("rh : " + rh);
			if (rh == null) {
				// insert rating log

				itemService.updateRatingByItemId((rawId < 0) ? Constant.CLICK_RATING * -1 : Constant.CLICK_RATING, id);

				RatingHistory rhNew = new RatingHistory();
				rhNew.setDir((rawId < 0) ? Constant.RATING_DOWN : Constant.RATING_UP);
				rhNew.setItemId(id);
				rhNew.setUserId(user.getUserId());

				itemService.insertRatingHistory(rhNew);

			} else {
				// remove rating log
				if (rh.getDir() < 0) {
					itemService.updateRatingByItemId(Constant.CLICK_RATING, id);
				} else {
					itemService.updateRatingByItemId(Constant.CLICK_RATING * -1, id);
				}

				itemService.removeRatingHistory(rh);
			}

			Item item = itemService.getItemByItemId(id);

			logger.info("Item : " + item);
			return "" + item.getRating();
		}

	}

	@RequestMapping(value = "/buyout", method = RequestMethod.POST)
	public void buyout(@RequestParam(value = "id", required = false) int id, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		Item item = itemService.getItemByItemId(id);

		if (item != null) {
			if (item.getStatus().equals(Constant.ITEM_STATUS_ACTIVE)) {
				Request itemRequest = new Request();
				itemRequest.setItemId(item.getItemId());
				itemRequest.setUserId(user.getUserId());

				itemService.insertRequest(itemRequest);
			}
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@ModelAttribute("itemForm") ItemForm itemForm, Model model) {
		return "/item/create";

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createSubmit(@ModelAttribute("itemForm") ItemForm itemForm, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		Item item = new Item();

		item.setUserId(user.getUserId());

		item.setTitle(itemForm.getTitle());

		item.setType(Constant.URL_TYPE);
		item.setUrl(itemForm.getUrl());
		item.setRemark(itemForm.getRemark());

		item.setBidPrice(-1);
		item.setBuyoutPrice(itemForm.getBuyoutPrice());

		// tags
		String tagsString = itemForm.getTags();
		String[] tags = tagsString.split(" ");

		ArrayList<Tag> tagList = new ArrayList<Tag>();

		for (int i = 0; i < tags.length; i++) {
			Tag tag = itemService.getTagByValue(tags[i].trim());
			if (tag == null) {
				tag = new Tag();
				// set id
				tag.setValue(tags[i]);
				tagList.add(tag);
			} else {
				tagList.add(tag);
			}
		}
		item.setTagList(tagList);

		item.setStatus(Constant.ITEM_STATUS_PENDING);

		itemService.insertItem(item);

		logger.debug("Item : " + itemForm);

		return "redirect:/";

	}
}
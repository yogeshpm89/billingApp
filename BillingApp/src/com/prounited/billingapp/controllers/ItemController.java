package com.prounited.billingapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.services.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired ItemService itemService;
	@RequestMapping(value="/add", method= RequestMethod.POST)
	public @ResponseBody String addItem(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = itemService.addItem(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/edit", method= RequestMethod.POST)
	public @ResponseBody String editItem(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = itemService.editItem(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteItem(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = itemService.deleteItem(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.POST)
	public @ResponseBody String getItemDetail(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = itemService.getItemDetail(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public @ResponseBody String getAllItems(HttpServletRequest request, ModelMap model) {
		String sort = request.getParameter(Constants.REQUEST_SORT);
		String filter = request.getParameter(Constants.REQUEST_FILTER);
		String page = request.getParameter(Constants.REQUEST_PAGE);
		String limit = request.getParameter(Constants.REQUEST_LIMIT);
		String query = request.getParameter(Constants.REQUEST_QUERY);
		
		int pageI = 0;
		if (page != null) {
			pageI = Integer.parseInt(page);
		}
		
		int limitI = 0;
		if (limit != null) {
			limitI = Integer.parseInt(limit);
		}
		String responseJson = itemService.getAllItems(sort, filter, query, pageI, limitI);
		return responseJson;
	}
}

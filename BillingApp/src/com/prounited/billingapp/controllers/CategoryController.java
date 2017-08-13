package com.prounited.billingapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.services.CategoryService;


@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired CategoryService categoryService;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public @ResponseBody String getCategories(ModelMap model) {
		String test = "test response";
		return test;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public @ResponseBody String addCategory(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = categoryService.addCategory(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public @ResponseBody String editCategory(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = categoryService.editCategory(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteCategory(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = categoryService.deleteCategory(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public @ResponseBody String getAllCategories(HttpServletRequest request, ModelMap model) {
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
		String responseJson = categoryService.getAllCategories(sort, filter, query, pageI, limitI);
		return responseJson;
	}
	
}

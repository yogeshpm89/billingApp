package com.prounited.billingapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.services.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired AdminService adminService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public @ResponseBody String getAllSettings(HttpServletRequest request, ModelMap model) {
		String responseJson = adminService.getSettings();
		return responseJson;
	}
	
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public @ResponseBody String saveSettings(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = adminService.updateSetting(jsonString);
		return responseJson;
	}
}

package com.prounited.billingapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.services.BillService;


@Controller
@RequestMapping("/bill")
public class BillController {

	@Autowired BillService billService;
	
	@RequestMapping(value="/nextBillNumber", method = RequestMethod.POST)
	public @ResponseBody String getCategories(ModelMap model) {
		String responseString = billService.getNextBillId();
		return responseString;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public @ResponseBody String addBill(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = billService.addBill(jsonString);
		return responseJson;
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public @ResponseBody String getAllBills(HttpServletRequest request, ModelMap model) {
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
		String responseJson = billService.getAllBills(sort, filter, query, pageI, limitI);
		return responseJson;
	}
	
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteCustomer(HttpServletRequest request, ModelMap model) {
		String jsonString = request.getParameter(Constants.REQUEST_JSON);
		String responseJson = billService.deleteBill(jsonString);
		return responseJson;
	}
	
	
	@RequestMapping(value="/reArrangeBillNumbers", method = RequestMethod.POST)
	public @ResponseBody String reArrangeBillNumbers(HttpServletRequest request, ModelMap model) {
		String responseJson = billService.reArrangeBillNumbers();
		return responseJson;
	}
}

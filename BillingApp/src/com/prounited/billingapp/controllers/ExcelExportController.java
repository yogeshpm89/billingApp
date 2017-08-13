package com.prounited.billingapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.Category;
import com.prounited.billingapp.models.Customer;
import com.prounited.billingapp.models.Item;
import com.prounited.billingapp.services.BillService;
import com.prounited.billingapp.services.CategoryService;
import com.prounited.billingapp.services.CustomerService;
import com.prounited.billingapp.services.ItemService;

@Controller
@RequestMapping("/excelExport")
public class ExcelExportController {

	@Autowired BillService billService;
	@Autowired CategoryService categoryService;
	@Autowired CustomerService customerService;
	@Autowired ItemService itemService;
	
	@RequestMapping(value="/download", method = RequestMethod.GET)
	public @ResponseBody ModelAndView addCategory(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String grid = request.getParameter(Constants.REQUEST_EXCEL_EXPORT_GRID);
		 // create some sample data
        //List<Category> categories = new ArrayList<Category>();
        List list = null;
        List colList = null;
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Class entityClass = null;
        switch (grid) {
			case Constants.EXCEL_EXPORT_GRID_BILLS:
				list = billService.getAllBills();
				colList = Constants.getBillColumnList();
				entityClass = Bill.class;
				break;
			case Constants.EXCEL_EXPORT_GRID_CATEGORIES:
				list = categoryService.getAllCategories();
				colList = Constants.getCategoryColumnList();
				entityClass = Category.class;
				break;
			case Constants.EXCEL_EXPORT_GRID_ITEMS:
				list = itemService.getAllItems();
				colList = Constants.getItemColumnList();
				entityClass = Item.class;
				break;
			case Constants.EXCEL_EXPORT_GRID_CUSTOMERS:
				list = customerService.getAllCustomers();
				colList = Constants.getCustomerColumnList();
				entityClass = Customer.class;
				break;
			default:
				break;
		}
        modelMap.put("list", list);
        modelMap.put("colList", colList);
        modelMap.put("grid", grid);
        modelMap.put("entityClass", entityClass);
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("excelView", "modelMap", modelMap);
	}
}

package com.prounited.billingapp.services.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.helpers.Criterion;
import com.prounited.billingapp.helpers.DBSqlQuery;
import com.prounited.billingapp.models.Category;
import com.prounited.billingapp.models.Customer;
import com.prounited.billingapp.models.Item;
import com.prounited.billingapp.services.CustomerService;
import com.prounited.billingapp.services.DBService;
import com.prounited.billingapp.vos.CustomerVO;
import com.prounited.billingapp.vos.ItemVO;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired DBService dbService;
	//11/23/1987
	@Override
	public String addCustomer(String customerJsonString) {
		Gson gson = new Gson();
		Map<String, Object> customerMap = gson.fromJson(customerJsonString, HashMap.class);
		Customer customer = new Customer();
		String result;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		try {
			//customer.setCustomerId(Long.parseLong((String) customerMap.get(Constants.CUSTOMER_ID)));
			customer.setAddress((String) customerMap.get(Constants.CUSTOMER_ADDRESS));
			String bdate = (String) customerMap.get(Constants.CUSTOMER_BIRTH_DATE);
			if (bdate != null && !"".equalsIgnoreCase(bdate)) {
				customer.setBirthDate(Constants.DATE_FORMAT.parse((String) bdate));
			}
			customer.setEmail((String) customerMap.get(Constants.CUSTOMER_EMAIL));
			customer.setFirstName((String) customerMap.get(Constants.CUSTOMER_FIRST_NAME));
			customer.setLastName((String) customerMap.get(Constants.CUSTOMER_LAST_NAME));
			customer.setPhone((String) customerMap.get(Constants.CUSTOMER_PHONE));
			customer.setIsActive("Y");
			customer.setCreateDate(new Date());
			customer.setCreateUser(Constants.SYSTEM_USER);
			customer.setUpdateDate(new Date());
			customer.setUpdateUser(Constants.SYSTEM_USER);
			
			Customer existingCustomer = checkIfCustomerExists(session, customer);
			
			if (existingCustomer != null) {
				resultMap.put("success", true);
				resultMap.put(Constants.CUSTOMER_ID, existingCustomer.getCustomerId());
				resultMap.put("message", "Customer is aleady existing");
			} else 
				customer = (Customer) dbService.saveOrUpdate(session, customer, Customer.class);
				if(customer == null) {
					resultMap.put("success", false);
				} else {
					if (customer.getCustomerId() > 0) {
						resultMap.put("success", true);
						resultMap.put(Constants.CUSTOMER_ID, customer.getCustomerId());
						resultMap.put("message", "Customer added successfully.");
					}
				}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		
		String resultString = gson.toJson(resultMap);
		return resultString;
	}
	
	@Override
	public String editCustomer(String customerJsonString) {
		Gson gson = new Gson();
		Map<String, Object> customerMap = gson.fromJson(customerJsonString, HashMap.class);
		String result;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		Long customerId = Long.parseLong((String) customerMap.get(Constants.CUSTOMER_ID));
		Customer customer = (Customer) session.get(Customer.class, customerId);
		customer.setAddress((String) customerMap.get(Constants.CUSTOMER_ADDRESS));
		try {
			customer.setBirthDate(Constants.DATE_FORMAT.parse((String) customerMap.get(Constants.CUSTOMER_BIRTH_DATE)));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		customer.setEmail((String) customerMap.get(Constants.CUSTOMER_EMAIL));
		customer.setFirstName((String) customerMap.get(Constants.CUSTOMER_FIRST_NAME));
		customer.setLastName((String) customerMap.get(Constants.CUSTOMER_LAST_NAME));
		customer.setPhone((String) customerMap.get(Constants.CUSTOMER_PHONE));
		customer.setIsActive("Y");
		customer.setUpdateDate(new Date());
		customer.setUpdateUser(Constants.SYSTEM_USER);

		try {
			customer = (Customer) dbService.saveOrUpdate(session, customer, Customer.class);
			if (customer == null) {
				resultMap.put("success", false);
			} else {
				if (customer.getCustomerId() > 0) {
					resultMap.put("success", true);
					resultMap.put(Constants.CUSTOMER_ID,customer.getCustomerId());
					resultMap.put("message", "Customer added successfully.");
				}
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		String resultString = gson.toJson(resultMap);
		return resultString;
	}

	@Override
	public String deleteCustomer(String customerJsonString) {
		Gson gson = new Gson();
		Map<String, String> requestMap = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Type stringStringMap = new TypeToken<Map<String, String>>() {
		}.getType();

		requestMap = gson.fromJson(customerJsonString, stringStringMap);
		List<Criterion> criterions = new ArrayList<Criterion>();

		if (requestMap.containsKey(Constants.CUSTOMER_ID)) {
			String customerId = requestMap.get(Constants.CUSTOMER_ID);
			long customerIdL = -1l;
			if (customerId != null) {
				customerIdL = Long.parseLong(customerId);
			}

			if (customerIdL > 0) {
				criterions.add(new Criterion(Constants.CUSTOMER_ID, customerIdL, "long"));
			}
		}
		
		String result;
		Session session = dbService.openSession();
		try {
			if (!criterions.isEmpty()) {
					result = (String) dbService.executeUpdateQuery(session, DBSqlQuery.DELETE_CUSTOMER, criterions);
					if (result == "1") {
						resultMap.put("success", true);
					} else {
						resultMap.put("success", false);
					}
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		String resultString = gson.toJson(resultMap);
		return resultString;
	}

	@Override
	public String getAllCustomerS(String sort, String filter,
			String query, int page, int limit) {
		Gson gson = new GsonBuilder()
		   .setDateFormat(Constants.DATE_FORMAT.toPattern()).create();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Customer> items = new ArrayList<Customer>();
		
		List<Map<String, Object>> filterMapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		List<Map<String, String>> sortMapList = new ArrayList<Map<String, String>>();
		Map<String, String> sortMap = new HashMap<String, String>();
		
		Type listStringObjectMap = new TypeToken<List<Map<String, Object>>>(){}.getType();
		Type listStringMap = new TypeToken<List<Map<String, String>>>(){}.getType();

		filterMapList = gson.fromJson(filter, listStringObjectMap);
		if (filterMapList == null || filterMapList.isEmpty()) {
			filterMap = new HashMap<String, Object>();
		} else {
			//Map<String, Object> tempMap = filterMapList.get(0);
			for (Map tempMap: filterMapList) {
				filterMap.put((String) tempMap.get("property"), tempMap.get("value"));
			}
		}
		sortMapList = gson.fromJson(sort, listStringMap);
		if (sortMapList != null && !sortMapList.isEmpty()) {
			sortMap = sortMapList.get(0);
		} else {
			sortMap.put("direction", Constants.SORT_DESC);
			sortMap.put("property", Constants.UPDATE_DATE);
		}
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		if (query != null && !"".equalsIgnoreCase(query)) {
			if (!filterMap.containsKey("firstName")) {
				filterMap.put("firstName", query);
			}
		}
		
		filterMap.put("isActive", "Y");
		
		if (sortMapList != null && !sortMapList.isEmpty()) sortMap = sortMapList.get(0);
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Customer.class, filterMap);
			if (count > 0) {
				items = dbService.getAll(session, Customer.class, sortMap, filterMap, page, limit);
			}
			resultMap.put("success", true);
			resultMap.put("items", CustomerVO.getCustomerVOs(items));
			resultMap.put("total", count);
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
		} finally {
			dbService.closeSession(session);
		}
		
		String resultString = gson.toJson(resultMap);
		return resultString;
	}
	
	private Customer checkIfCustomerExists(Session session, Customer customer) {
		
		StringBuffer whereCriteria = new StringBuffer("");
		List<Criterion> criterionList = new ArrayList<Criterion>();
		
		if (customer.getAddress() != null && !customer.getAddress().isEmpty()) {
			if (whereCriteria.length() > 0) {
				whereCriteria.append(" AND ");
			}
			whereCriteria.append(" address like :address ");
			criterionList.add(new Criterion("address", customer.getAddress(), "string"));
		}
		if (customer.getBirthDate() != null) {
			if (whereCriteria.length() > 0) {
				whereCriteria.append(" AND ");
			}
			whereCriteria.append(" birthDate like :birthDate ");
			criterionList.add(new Criterion("birthDate", customer.getBirthDate(), "date"));
		}
		if (customer.getEmail() != null && !customer.getEmail().isEmpty()) {
			if (whereCriteria.length() > 0) {
				whereCriteria.append(" AND ");
			}
			whereCriteria.append(" email like :email ");
			criterionList.add(new Criterion("email", customer.getEmail(), "string"));
		}
		if (customer.getFirstName() != null && !customer.getFirstName().isEmpty()) {
			if (whereCriteria.length() > 0) {
				whereCriteria.append(" AND ");
			}
			whereCriteria.append(" firstName like :firstName ");
			criterionList.add(new Criterion("firstName", customer.getFirstName(), "string"));
		}
		if (customer.getLastName() != null && !customer.getLastName().isEmpty()) {
			if (whereCriteria.length() > 0) {
				whereCriteria.append(" AND ");
			}
			whereCriteria.append(" lastName like :lastName ");
			criterionList.add(new Criterion("lastName", customer.getLastName(), "string"));
		}
		if (customer.getPhone() != null && !customer.getPhone().isEmpty()) {
			if (whereCriteria.length() > 0) {
				whereCriteria.append(" AND ");
			}
			whereCriteria.append(" phone like :phone ");
			criterionList.add(new Criterion("phone", customer.getPhone(), "string"));
		}
		
		String whereString = (whereCriteria.length() >0)? " WHERE " + whereCriteria : "";
		String query = "FROM Customer" + whereString;
		System.out.println(query);
		Customer resultCustomer = null;

		
		try {
			
			List<Customer> customerList = dbService.getAll(session, Customer.class, null, criterionList, 0, 1);
			//(List<Customer>) dbService.executeQuery(session, query, criterionList);
			if (!customerList.isEmpty()) {
				resultCustomer = customerList.get(0);
			}
		} catch (Exception e) {
		}
		return resultCustomer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = null;
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("direction", Constants.SORT_DESC);
		sortMap.put("property", Constants.CREATE_DATE);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("isActive", "Y");
		
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Customer.class, filterMap);
			if (count > 0) {
				customers = dbService.getAll(session, Customer.class, sortMap, filterMap, 1, count);
			}
		} catch (Exception e) {
		} finally {
			dbService.closeSession(session);
		}
		return customers;
	}

}

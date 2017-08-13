package com.prounited.billingapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.Customer;

@Service("customerService")
public interface CustomerService {

	String addCustomer(String customerJsonString);
	String editCustomer(String customerJsonString);
	String deleteCustomer(String customerJsonString);
	String getAllCustomerS(String sortMap, String filterMap, String query, int page, int limit);
	public List<Customer> getAllCustomers();
}

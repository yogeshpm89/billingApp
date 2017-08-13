package com.prounited.billingapp.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.prounited.billingapp.models.Bill;

@Service("billService")
public interface BillService {

	public String getNextBillId();
	public String addBill(String requestJson);
	public String getAllBills(String sortMap, String filterMap, String query, int page, int limit);
	public String deleteBill(String billJsonString);
	public String reArrangeBillNumbers();
	public List<Bill> getAllBills();
}

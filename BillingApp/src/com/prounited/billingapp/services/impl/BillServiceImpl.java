/**
 * 
 */
package com.prounited.billingapp.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.helpers.Criterion;
import com.prounited.billingapp.helpers.DBSqlQuery;
import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.BillItem;
import com.prounited.billingapp.models.Category;
import com.prounited.billingapp.models.Item;
import com.prounited.billingapp.services.BillService;
import com.prounited.billingapp.services.DBService;
import com.prounited.billingapp.services.ItemService;
import com.prounited.billingapp.vos.BillVO;
import com.prounited.billingapp.vos.CategoryVO;

/**
 * @author yogeshpm89
 *
 */

@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired DBService dbService;
	@Autowired ItemService itemService;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextBillId() {
		Gson gson = new Gson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		try {
			List<Object> list = (List<Object>) dbService.executeQuery(session, DBSqlQuery.SELECT_NEXT_BILL_ID, null);
			if (list != null) {
				resultMap.put("success", true);
				if (!list.isEmpty()) {
					Long  billId = (Long) list.get(0);
					if (billId == null) {
						resultMap.put(Constants.BILL_ID, 1);
					} else {
						resultMap.put(Constants.BILL_ID, billId+1);
					}
				} else {
					resultMap.put(Constants.BILL_ID, 1);
				}
			} else {
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("error", e.getMessage());
			e.printStackTrace();
		}
		
		String resultString = gson.toJson(resultMap);
		return resultString;
	}

	@Override
	public String addBill(String requestJson) {

		Gson gson = new Gson();
		List<Object> saveObjectList = new ArrayList<Object>(); 
		Bill bill = gson.fromJson(requestJson, Bill.class);
		//Bill bill = new Bill();
		//saveObjectList.add(bill);
		/*bill.setBillId(Long.parseLong((String)billMap.get(Constants.BILL_ID)));
		bill.setAmount(Float.parseFloat((String) billMap.get(Constants.BILL_AMOUNT)));
		bill.setCustomerId(Long.parseLong((String)billMap.get(Constants.CUSTOMER_ID)));*/
		bill.setCreateDate(new Date());
		bill.setCreateUser(Constants.SYSTEM_USER);
		bill.setBillNumber(bill.getBillId());
 		bill.setBillId(0);
 		bill.setIsActive("Y");
		List<Object> resultList = new ArrayList<Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		try {
			
			bill = (Bill) dbService.saveOrUpdate(session, bill, Bill.class);
			
			@SuppressWarnings("unchecked")
			Set<BillItem> billItemsSet = (Set<BillItem>) bill.getBillItems();
			Iterator<BillItem> iterator = billItemsSet.iterator();
	 		while (iterator.hasNext()) {
				BillItem billItem = iterator.next();
				billItem.setBillId(bill.getBillId());
				billItem.setCreateDate(new Date());
				billItem.setCreateUser(Constants.SYSTEM_USER);
				saveObjectList.add(billItem);
				
				if (billItem.getItemId() == 0) {
					String itemJsonString = "";
					itemJsonString = gson.toJson(billItem);
					Item item = new Item();
					item.setCategoryId(billItem.getCategoryId());
					item.setItemName(billItem.getItemName());
					item.setItemDesc(billItem.getItemDesc());
					item.setItemCode(billItem.getItemCode());
					item.setItemQuantity(billItem.getItemQuantity());
					item.setUnitPrice(billItem.getUnitPrice());
					item.setIsActive("Y");
					item.setCreateDate(new Date());
					item.setCreateUser(Constants.SYSTEM_USER);
					item.setUpdateDate(new Date());
					item.setUpdateUser(Constants.SYSTEM_USER);

					item = (Item) dbService.saveOrUpdate(session, item, Item.class);
					
					if (item != null) {
						billItem.setItemid(item.getItemId());
					}
				}
				
					List<Criterion> criterions = new ArrayList<Criterion>();
					criterions.add(new Criterion("itemId", billItem.getItemId(), "long"));
					List<Item> itemList = dbService.getAll(session, Item.class, null, criterions, 0, 1);
					
					Item item = itemList.get(0);
					long itemQuantity = item.getItemQuantity() - billItem.getItemQuantity();
					item.setItemQuantity(itemQuantity);
					item.setUpdateDate(new Date());
					item.setUpdateUser(Constants.SYSTEM_USER);
					saveObjectList.add(item);
			}
			resultList = dbService.bulkSaveOrUpdate(session, saveObjectList);
			if (resultList.size() > 0) {
				resultMap.put("success", true);
				resultMap.put("message", "Bill saved successfully");
			} else {
				resultMap.put("success", false);
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
	public List<Bill> getAllBills() {
		List<Bill> bills = null;
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("direction", Constants.SORT_DESC);
		sortMap.put("property", Constants.CREATE_DATE);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("isActive", "Y");
		
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Bill.class, filterMap);
			if (count > 0) {
				bills = dbService.getAll(session, Bill.class, sortMap, filterMap, 1, count);
			}
		} catch (Exception e) {
		} finally {
			dbService.closeSession(session);
		}
		return bills;
	}
	
	@Override
	public String getAllBills(String sort, String filter, String query,
			int page, int limit) {
		Gson gson = new Gson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Bill> bills = new ArrayList<Bill>();
		
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
			//filterMap = filterMapList.get(0);
			for (Map tempMap: filterMapList) {
				String property = (String) tempMap.get("property");
				if (property.equalsIgnoreCase(Constants.CUSTOMER_ID)) {
					Long customerId = Long.parseLong((String) tempMap.get("value"));
					filterMap.put(property, customerId);
				}
			}
		}
		sortMapList = gson.fromJson(sort, listStringMap);
		
		if (sortMapList != null && !sortMapList.isEmpty()) {
			sortMap = sortMapList.get(0);
		} else {
			sortMap.put("direction", Constants.SORT_DESC);
			sortMap.put("property", Constants.CREATE_DATE);
		}
		
		filterMap.put("isActive", "Y");
		
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Bill.class, filterMap);
			if (count > 0) {
				bills = dbService.getAll(session, Bill.class, sortMap, filterMap, page, limit);
			}
			resultMap.put("success", true);
			resultMap.put("items", BillVO.getBillVOs(bills));
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

	@Override
	public String deleteBill(String billJsonString) {
		Gson gson = new Gson();
		Map<String, String> requestMap = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Type stringStringMap = new TypeToken<Map<String, String>>() {
		}.getType();

		requestMap = gson.fromJson(billJsonString, stringStringMap);
		List<Criterion> criterions = new ArrayList<Criterion>();

		if (requestMap.containsKey(Constants.BILL_ID)) {
			String billId = requestMap.get(Constants.BILL_ID);
			long billIdL = -1l;
			if (billId != null) {
				billIdL = Long.parseLong(billId);
			}

			if (billIdL > 0) {
				criterions.add(new Criterion(Constants.BILL_ID, billIdL, "long"));
			}
		}
		
		String result;
		Session session = dbService.openSession();
		try {
			if (!criterions.isEmpty()) {
					result = (String) dbService.executeUpdateQuery(session, DBSqlQuery.DELETE_BILL, criterions);
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
	public String reArrangeBillNumbers() {
		Gson gson = new Gson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		
		try {
			Map<String, String> sortMap = new HashMap<String, String>();
			sortMap.put("direction", Constants.SORT_ASC);
			sortMap.put("property", Constants.CREATE_DATE);
			
			HashMap<String, Object> filterMap = new HashMap<String, Object>();
			filterMap.put("isActive", "Y");
			
			List<Bill> bills = dbService.getAll(session, Bill.class, sortMap, filterMap, -1, -1);
			
			long number = 0;
			List<Object> saveObjectList = new ArrayList<Object>();
			for (Bill bill: bills) {
				bill.setBillNumber(++number);
				saveObjectList.add(bill);
			}
			List<Object> resultList = new ArrayList<Object>();
			resultList= dbService.bulkSaveOrUpdate(session, saveObjectList);
			if (resultList.size() > 0) {
				resultMap.put("success", true);
				resultMap.put("message", "Bills updated successfully");
			} else {
				resultMap.put("success", false);
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
}

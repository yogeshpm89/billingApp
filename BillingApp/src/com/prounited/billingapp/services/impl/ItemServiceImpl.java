package com.prounited.billingapp.services.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prounited.billingapp.constants.Constants;
import com.prounited.billingapp.helpers.Criterion;
import com.prounited.billingapp.helpers.DBSqlQuery;
import com.prounited.billingapp.models.Customer;
import com.prounited.billingapp.models.Item;
import com.prounited.billingapp.services.DBService;
import com.prounited.billingapp.services.ItemService;
import com.prounited.billingapp.vos.ItemVO;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired DBService dbService;
	
	@Override
	public String addItem(String itemJsonString) {
		Gson gson = new Gson();
		Map<String, Object> itemMap = gson.fromJson(itemJsonString, HashMap.class);
		Item item = new Item();
		
		item.setCategoryId(Long.parseLong((String) itemMap.get(Constants.CATEGORY_ID)));
		item.setItemName((String) itemMap.get(Constants.ITEM_NAME));
		item.setItemDesc((String) itemMap.get(Constants.ITEM_DESC));
		item.setItemCode((String) itemMap.get(Constants.ITEM_CODE));
		item.setItemQuantity(Long.parseLong((String) itemMap.get(Constants.ITEM_QUANTITY)));
		item.setUnitPrice(Float.parseFloat((String)itemMap.get(Constants.ITEM_UNIT_PRICE)));
		item.setIsActive("Y");
		
		item.setCreateDate(new Date());
		item.setCreateUser(Constants.SYSTEM_USER);
		item.setUpdateDate(new Date());
		item.setUpdateUser(Constants.SYSTEM_USER);
		String result;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = dbService.openSession();
		try {
			item = (Item) dbService.saveOrUpdate(session, item, Item.class);
			if (item != null) {
				resultMap.put("success", true);
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
	public String editItem(String itemJsonString) {
		Gson gson = new Gson();
		Map<String, Object> itemMap = gson.fromJson(itemJsonString, HashMap.class);
		Session session = dbService.openSession();
		Long itemId = Long.parseLong((String) itemMap.get(Constants.ITEM_ID));
		Item item = (Item) session.get(Item.class, itemId);
		item.setCategoryId(Long.parseLong((String) itemMap.get(Constants.CATEGORY_ID)));
		item.setItemName((String) itemMap.get(Constants.ITEM_NAME));
		item.setItemDesc((String) itemMap.get(Constants.ITEM_DESC));
		item.setItemCode((String) itemMap.get(Constants.ITEM_CODE));
		item.setItemQuantity(Long.parseLong((String) itemMap.get(Constants.ITEM_QUANTITY)));
		item.setUnitPrice(Float.parseFloat((String)itemMap.get(Constants.ITEM_UNIT_PRICE)));
		item.setIsActive("Y");
		
		item.setUpdateDate(new Date());
		item.setUpdateUser(Constants.SYSTEM_USER);
		String result;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			item = (Item) dbService.saveOrUpdate(session, item, Item.class);
			if (item != null) {
				resultMap.put("success", true);
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
	public String deleteItem(String itemJsonString) {
		Gson gson = new Gson();
		Map<String, String> requestMap = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Type stringStringMap = new TypeToken<Map<String, String>>() {
		}.getType();

		requestMap = gson.fromJson(itemJsonString, stringStringMap);
		List<Criterion> criterions = new ArrayList<Criterion>();

		if (requestMap.containsKey(Constants.ITEM_ID)) {
			String itemId = requestMap.get(Constants.ITEM_ID);
			long itemIdL = -1l;
			if (itemId != null) {
				itemIdL = Long.parseLong(itemId);
			}

			if (itemIdL > 0) {
				criterions.add(new Criterion("itemId", itemIdL, "long"));
			}
		}
		
		String result;
		Session session = dbService.openSession();
		try {
			if (!criterions.isEmpty()) {
					result = (String) dbService.executeUpdateQuery(session, DBSqlQuery.DELETE_ITEM, criterions);
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
	public String getItemDetail(String itemJsonString) {
		Gson gson = new Gson();
		Map<String, String> requestMap = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Type stringStringMap = new TypeToken<Map<String, String>>() {
		}.getType();

		requestMap = gson.fromJson(itemJsonString, stringStringMap);
		List<Criterion> criterions = new ArrayList<Criterion>();

		if (requestMap.containsKey(Constants.ITEM_ID)) {
			String itemId = requestMap.get(Constants.ITEM_ID);
			long itemIdL = -1l;
			if (itemId != null) {
				itemIdL = Long.parseLong(itemId);
			}

			if (itemIdL > 0) {
				criterions.add(new Criterion("itemId", itemIdL, "long"));
			}
		}
		
		Session session = dbService.openSession();
		try {
			if (!criterions.isEmpty()) {
					criterions.add(new Criterion("isActive", "Y", "string"));
					List<Item> result = dbService.getAll(session, Item.class, null, criterions, 0, 1);
					if (result != null && !result.isEmpty()) {
						resultMap.put("success", true);
						resultMap.put("item", ItemVO.getItemVO(result.get(0)));
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
	public String getAllItems(String sort, String filter, String query,
			int page, int limit) {
		Gson gson = new Gson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Item> items = new ArrayList<Item>();
		
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
			filterMap = filterMapList.get(0);
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
			Criterion criterion = new Criterion();
			criterion.setType("string");
			criterion.setValue(query);
			criterions.add(criterion);
			filterMap.put("itemName", query);
		}
		
		filterMap.put("isActive", "Y");
		
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Item.class, filterMap);
			if (count > 0) {
				items = dbService.getAll(session, Item.class, sortMap, filterMap, page, limit);
			}
			resultMap.put("success", true);
			resultMap.put("items", ItemVO.getItemVOs(items));
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
	public List<Item> getAllItems() {
		List<Item> items = null;
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("direction", Constants.SORT_DESC);
		sortMap.put("property", Constants.CREATE_DATE);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("isActive", "Y");
		
		Session session = dbService.openSession();
		try {
			int count = dbService.getAllCount(session, Item.class, filterMap);
			if (count > 0) {
				items = dbService.getAll(session, Item.class, sortMap, filterMap, 1, count);
			}
		} catch (Exception e) {
		} finally {
			dbService.closeSession(session);
		}
		return items;
	}

}

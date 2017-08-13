package com.prounited.billingapp.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.Item;

@Service("itemService")
public interface ItemService {

	String addItem(String itemJsonString);
	String editItem(String itemJsonString);
	String deleteItem(String itemJsonString);
	String getItemDetail(String itemJsonString);
	String getAllItems(String sortMap, String filterMap, String query, int page, int limit);
	public List<Item> getAllItems();
}

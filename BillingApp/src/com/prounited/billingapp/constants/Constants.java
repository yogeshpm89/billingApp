package com.prounited.billingapp.constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class Constants {

	public static final String  SYSTEM_USER =  "system";
	public static final String  REQUEST_SORT =  "sort";
	public static final String  REQUEST_FILTER =  "filter";
	public static final String  REQUEST_PAGE =  "page";
	public static final String  REQUEST_LIMIT =  "limit";
	public static final String  REQUEST_QUERY =  "query";
	
	public static final String  REQUEST_JSON =  "requestJson";
	public static final String  REQUEST_EXCEL_EXPORT_GRID =  "grid";
	public static final String  EXCEL_EXPORT_GRID_BILLS =  "bills";
	public static final String  EXCEL_EXPORT_GRID_CATEGORIES =  "categories";
	public static final String  EXCEL_EXPORT_GRID_ITEMS =  "items";
	public static final String  EXCEL_EXPORT_GRID_CUSTOMERS =  "customers";

	public static final String  SORT_DESC =  "DESC";
	public static final String  SORT_ASC =  "ASC";
	
	public static final String  CREATE_DATE =  "createDate";
	public static final String  CREATE_USER =  "createUser";
	public static final String  UPDATE_DATE =  "updateDate";
	public static final String  UPDATE_USER =  "updateUser";
	
	public static final String  CATEGORY_ID =  "categoryId";
	public static final String  CATEGORY_NAME =  "categoryName";
	public static final String  CATEGORY_DESC =  "categoryDesc";
	
	public static final String  ITEM_ID =  "itemId";
	public static final String  ITEM_NAME =  "itemName";
	public static final String  ITEM_DESC =  "itemDesc";
	public static final String  ITEM_CODE =  "itemCode";
	public static final String  ITEM_QUANTITY=  "itemQuantity";
	public static final String  ITEM_UNIT_PRICE=  "unitPrice";
	
	public static final String  CUSTOMER_ID =  "customerId";
	public static final String  CUSTOMER_FIRST_NAME =  "firstName";
	public static final String  CUSTOMER_BIRTH_DATE =  "birthDate";
	public static final String  CUSTOMER_LAST_NAME =  "lastName";
	public static final String  CUSTOMER_EMAIL =  "email";
	public static final String  CUSTOMER_ADDRESS =  "address";
	public static final String  CUSTOMER_PHONE =  "phone";
	
	public static final String  BILL_ID =  "billId";
	public static final String  BILL_AMOUNT =  "amount";
	public static final String  BILL_ITEMS =  "billItems";
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public static List<String> getBillColumnList() {
		List<String> list = new ArrayList<String>();
		list.add("bill Id");
		list.add("bill Number");
		list.add("customer Id");
		list.add("customer Name");
		list.add("customer Address");
		list.add("final Amount");
		list.add("amount");
		list.add("discount");
		list.add("tax Percentage");
		list.add("tax Amount");
		list.add("create Date");
		list.add("create User");
		list.add("is Active");
		return list;
	}
	
	public static List<String> getCategoryColumnList() {
		List<String> list = new ArrayList<String>();
		list.add("category Id");
		list.add("category name");
		list.add("category desc");
		list.add("create Date");
		list.add("create User");
		list.add("update Date");
		list.add("update User");
		list.add("is Active");
		return list;
	}
	
	public static List<String> getItemColumnList() {
		List<String> list = new ArrayList<String>();
		list.add("category Id");
		list.add("category name");
		list.add("category desc");
		list.add("item Id");
		list.add("item name");
		list.add("item desc");
		list.add("item Code");
		list.add("item quantity");
		list.add("unit price");
		list.add("create Date");
		list.add("create User");
		list.add("update Date");
		list.add("update User");
		list.add("is Active");
		return list;
	}
	
	
	public static List<String> getCustomerColumnList() {
		List<String> list = new ArrayList<String>();
		list.add("customer Id");
		list.add("first name");
		list.add("last name");
		list.add("birth Date");
		list.add("email");
		list.add("address");
		list.add("phone");
		list.add("create Date");
		list.add("create User");
		list.add("update Date");
		list.add("update User");
		list.add("is Active");
		return list;
	}
}

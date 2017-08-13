package com.prounited.billingapp.helpers;

public class DBSqlQuery {

	public static String DELETE_CATEGORY = "UPDATE Category SET isActive = 'N' WHERE categoryId = :categoryId";
	/*public static String DELETE_ITEM = "DELETE FROM Item WHERE itemId = :itemId";*/
	public static String DELETE_ITEM = "UPDATE Item SET isActive = 'N' WHERE itemId = :itemId";
	public static String SELECT_ITEM = "FROM Item WHERE itemId = :itemId and isActive='Y'";
	public static String DELETE_ITEMS_FOR_CATEGORY = "UPDATE Item SET isActive = 'N' WHERE categoryId = :categoryId";
	public static String DELETE_CUSTOMER = "UPDATE Customer SET isActive = 'N' WHERE customerId = :customerId";
	public static String SELECT_NEXT_BILL_ID = "SELECT MAX(billNumber) FROM Bill";
	public static String DELETE_BILL = "UPDATE Bill SET isActive = 'N' WHERE billId = :billId";
}

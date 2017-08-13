//Ext.Ajax.timeout = 120000; // 120 seconds

//var appPath = '/BillingApp/';
Ext.define('BillingApp.service.Service', {
	alternateClassName: ['Service'],
	singleton: true,
	sendRequest: function(url, param, method, successCallback, errorCallback, extraParams) {
		Ext.Ajax.request({
		    url: appPath + url,
		    params: param,
		    method: method,
		    success: function(response){
		        var text = response.responseText;
		        successCallback(response.responseText, extraParams);
		    },
		    failure: function(response){
		    	errorCallback(response.responseText, extraParams);
		    }
		});
	},
	
	GET: 'GET',
	POST: 'POST',
	ERROR_MSG: 'Error occured on Server',
	REQUEST_JSON: 'requestJson',
	ADD_CATEGORY: 'server/category/add',
	EDIT_CATEGORY: 'server/category/edit',
	DELETE_CATEGORY: 'server/category/delete',
	GET_ALL_CATEGORIES: 'server/category/getAll',
	
	ADD_ITEM: 'server/item/add',
	EDIT_ITEM: 'server/item/edit',
	GET_ALL_ITEMS: 'server/item/getAll',
	GET_ITEM_DETAIL: 'server/item/detail',
	DELETE_ITEM: 'server/item/delete',
	
	ADD_CUSTOMER: 'server/customer/add',
	EDIT_CUSTOMER: 'server/customer/edit',
	GET_ALL_CUSTOMERS: 'server/customer/getAll',
	DELETE_CUSTOMER: 'server/customer/delete',
	
	GET_NEXT_BILL_ID: 'server/bill/nextBillNumber',
	REARRANGE_BILL_NUMBERS: 'server/bill/reArrangeBillNumbers',
	ADD_BILL: 'server/bill/add',
	GET_ALL_BILLS: 'server/bill/getAll',
	DELETE_BILL: 'server/bill/delete',
	SAVE_BILL: 'server/billing/saveBill',

	GET_APP_SETTINGS: 'server/admin/getAll',
	SAVE_APP_SETTINGS: 'server/admin/save',
	
	DELETE_CATEGORIES: 'server/billing/deleteCategories',
	
	EXCEL_EXPORT: 'download/excelExport/download'
});
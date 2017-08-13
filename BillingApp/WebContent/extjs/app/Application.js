/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
 
Ext.define('BillingApp.Application', {
    extend: 'Ext.app.Application',
    
    name: 'BillingApp',

    views: [
    		//'BillingApp.view.charts.BarChart',
            'BillingApp.view.navigation.LeftNav',
            'BillingApp.view.navigation.LeftNavTree',
            'BillingApp.view.navigation.BasicTrees',
            'BillingApp.view.mainpanel.MainPanel',
            'BillingApp.view.mainpanel.MainPanelWindow',
            'BillingApp.view.category.CategoryPanel',
            'BillingApp.view.category.CategoryWindow',
            'BillingApp.view.category.CategoryForm',
            'BillingApp.view.category.CategoryGrid',
            'BillingApp.view.category.EditCategoryWindow',
            'BillingApp.view.item.ItemPanel',
            'BillingApp.view.item.ItemWindow',
            'BillingApp.view.item.ItemForm',
            'BillingApp.view.item.ItemGrid',
            'BillingApp.view.item.EditItemWindow',
            'BillingApp.view.customer.CustomerPanel',
            'BillingApp.view.customer.CustomerWindow',
            'BillingApp.view.customer.CustomerForm',
            'BillingApp.view.customer.CustomerGrid',
			'BillingApp.view.customer.CustomerGridWindow',
			'BillingApp.view.customer.EditCustomerWindow',
            'BillingApp.view.bill.CreateBillPanel',
            'BillingApp.view.bill.CreateBillWindow',
            'BillingApp.view.bill.CreateBillButtons',
            'BillingApp.view.bill.BillForm',
            'BillingApp.view.billitem.BillItemForm',
            'BillingApp.view.billitem.BillItemGrid',
            'BillingApp.view.bill.BillsWindow',
            'BillingApp.view.bill.BillGrid',
            'BillingApp.view.admin.AdminWindow',
            'BillingApp.view.admin.AdminForm',
	],
	models: [
		'BillingApp.model.Category',
		'BillingApp.model.Item',
		'BillingApp.model.Customer',
		'BillingApp.model.BillItem',
		'BillingApp.model.Bill'
	],
	controllers: [
		'BillingApp.controller.BillingController'
	],
	
    stores: [
       'BillingApp.store.category.CategoryStore',
       'BillingApp.store.item.ItemStore',
       'BillingApp.store.customer.CustomerStore',
       'BillingApp.store.billitem.BillItemStore',
       'BillingApp.store.bill.BillStore',
       'BillingApp.store.navigation.LeftNavTreeStore'
    ],
    
    requires: [
    	'BillingApp.service.Service',
    	'BillingApp.controller.BillingControllerHelper',
    	'BillingApp.service.BillingAppHelper'
    ],
    
    launch: function () {
        // TODO - Launch the application
    }
});

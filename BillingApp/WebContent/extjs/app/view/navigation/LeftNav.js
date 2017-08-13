Ext.define('BillingApp.view.navigation.LeftNav', {
	extend: 'Ext.container.Container',
	alias: 'widget.leftNav',
	layout: 'fit',
	width: 120,
	defaults: {
		width: 120
	},
	items: [
	        {
	        	xtype: 'button',
	        	itemId: 'newBillButton',
	        	text: 'Create New Bill',
	        	enableToggle: true
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'categoryButton',
	        	text: 'Categories',
	        	enableToggle: true
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'itemButton',
	        	text: 'Items',
	        	enableToggle: true
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'customerButton',
	        	text: 'Customers',
	        	enableToggle: true
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'billsButton',
	        	text: 'Bills',
	        	enableToggle: true
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'adminButton',
	        	text: 'Admin',
	        	enableToggle: true
	        }
	]
	
})
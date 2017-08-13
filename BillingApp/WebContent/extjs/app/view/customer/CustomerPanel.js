Ext.define('BillingApp.view.customer.CustomerPanel', {
	extend: 'Ext.container.Container',
	alias: 'widget.customerPanel',
	layout: 'vbox',
	defaults:{
		padding: 5
	},
	items: [
	        {
	        	xtype: 'customerForm'
	        },
	        {
	        	xtype: 'customerGrid'
	        }
	]
});
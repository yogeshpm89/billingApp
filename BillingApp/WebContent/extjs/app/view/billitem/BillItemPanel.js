Ext.define('BillingApp.view.billitem.BillItemPanel', {
	extend: 'Ext.container.Container',
	alias: 'widget.billItemPanel',
	layout: 'vbox',
	defaults:{
		padding: 5
	},
	items: [
	        {
	        	xtype: 'billItemForm',
	        }
	]
	
})
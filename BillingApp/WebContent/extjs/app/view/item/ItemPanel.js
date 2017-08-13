Ext.define('BillingApp.view.item.ItemPanel', {
	extend: 'Ext.container.Container',
	alias: 'widget.itemPanel',
	layout: 'vbox',
	defaults:{
		padding: 5
	},
	items: [
	        {
	        	xtype: 'itemForm'
	        },
	        {
	        	xtype: 'itemGrid'
	        }
	]
});
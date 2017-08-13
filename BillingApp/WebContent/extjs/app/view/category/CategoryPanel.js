Ext.define('BillingApp.view.category.CategoryPanel', {
	extend: 'Ext.container.Container',
	alias: 'widget.categoryPanel',
	layout: 'vbox',
	defaults:{
		padding: 5
	},
	items: [
	        {
	        	xtype: 'categoryForm'
	        },
	        {
	        	xtype: 'categoryGrid'
	        }
	]
	
})
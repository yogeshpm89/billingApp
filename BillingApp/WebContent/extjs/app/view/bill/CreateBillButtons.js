Ext.define('BillingApp.view.bill.CreateBillButtons', {
	extend: 'Ext.container.Container',
	alias: 'widget.createBillButtons',
	layout: {
		type: 'hbox',
		pack: 'end'
	},
	width: '100%',
	defaults:{
		//padding: 5,
		margin: '5px 5px 5px 0px'
	},
	items: [
	        {
	        	xtype: 'button',
	        	itemId: 'saveBillButton',
	        	text: 'Save'
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'printBillButton',
	        	text: 'Print'
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'saveAndPrintBillButton',
	        	text: 'Save and Print'
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'resetBillButton',
	        	text: 'Reset'
	        },
	        {
	        	xtype: 'button',
	        	itemId: 'cancelBillButton',
	        	text: 'Cancel'
	        }
	]
	
})
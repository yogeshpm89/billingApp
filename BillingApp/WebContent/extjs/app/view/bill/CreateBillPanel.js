Ext.define('BillingApp.view.bill.CreateBillPanel', {
	extend: 'Ext.container.Container',
	alias: 'widget.createBillPanel',
	layout: 'vbox',
	defaults:{
		padding: 5
	},
	config: {
		billTotal: 0
	},
	autoScroll: true,
	items: [
	        {
	        	xtype: 'customerForm',
	        	hideSearchButton: false
	        },
	        {
	        	xtype: 'billForm'
	        },
	        {
	        	xtype: 'billItemForm',
	        	width: '100%'
	        },
	        {
	        	xtype: 'billItemGrid'
	        },
	        {
	        	xtype: 'container',
	        	width: '100%',
	        	layout: {
	        		type: 'hbox',
	        		pack: 'end'
	        	},
	        	items:[
					{
					    xtype: 'textfield',
					    itemId: 'vat',
					    fieldLabel: '<b>Taxes (%)</b>',
					    editable: false,
					    disabled: true
					},
					{
						xtype: 'tbspacer',
						width: 5
					},
					{
					    xtype: 'textfield',
					    itemId: 'vatTotal',
					    fieldLabel: '<b>Taxes (Rs)</b>',
					    editable: false,
					    disabled: true
					}
	        	]
	        },
	        {
	        	xtype: 'container',
	        	width: '100%',
	        	layout: {
	        		type: 'hbox',
	        		pack: 'end'
	        	},
	        	items:[
					{
					    xtype: 'numberfield',
					    itemId: 'billDiscount',
					    fieldLabel: '<b>Discount (Rs) <span style="color:red;">*</span></b> ',
					    value: '0'
					},
					{
						xtype: 'tbspacer',
						width: 5
					},
					{
					    xtype: 'textfield',
					    itemId: 'billTotal',
					    fieldLabel: '<b>Grand Total (Rs)</b>',
					    editable: false,
					    value: '0',
					    disabled: true
					}
	        	]
	        },
	        {
	        	xtype: 'createBillButtons'
	        }
	]
	
})
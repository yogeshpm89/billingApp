Ext.define('BillingApp.view.bill.BillForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.billForm',
	// Fields will be arranged vertically, stretched to full width
	layout : {
		type : 'table',
		columns : 3
	},
	defaults : {
		anchor : '100%',
		padding : '0px 0px 0px 5px'
	},
	// The fields
	defaultType : 'textfield',
	items : [ {
		fieldLabel : 'Bill Number',
		name : 'billId',
		allowBlank : false,
		disabled : true
	}, {
		fieldLabel : 'Customer ID',
		name : 'customerId',
		allowBlank : false,
		disabled : true
	}, {
		xtype : 'datefield',
		fieldLabel : 'Date',
		name : 'billDate',
		format : 'd/m/Y',
		allowBlank : false,
		disabled : true,
		value: new Date()
	} ],
})
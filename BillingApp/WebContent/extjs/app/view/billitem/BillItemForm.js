Ext.define('BillingApp.view.billitem.BillItemForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.billItemForm',
	initComponent: function(){
    	// create the Data Store
        var categoryStore = Ext.create('BillingApp.store.category.CategoryStore', {pageSize: null});
        var itemStore = Ext.create('BillingApp.store.item.ItemStore', {pageSize: null});
    	Ext.apply(this, {
            // Fields will be arranged vertically, stretched to full width
		    layout: {
		    	type: 'table',
		    	columns: 3
		    },
		    defaults: {
		        anchor: '100%',
		        padding: '0px 0px 5px 5px'
		    },
		    // The fields
		    defaultType: 'textfield',
		    items: [{
		    	xtype: 'combo',
		        fieldLabel: 'Category <span style="color:red;">*</span>',
		        store: categoryStore, //'category.CategoryStore',
		        name: 'categoryId',
		        valueField: 'categoryId',
		        displayField: 'categoryName',
		        allowBlank: false,
		        //queryMode: 'remote'
		    },
		    {
		    	//xtype: 'combo',
		        fieldLabel: 'Item <span style="color:red;">*</span>',
		        store: itemStore, //'category.CategoryStore',
		        name: 'billItemId',
		        /*valueField: 'itemId',
		        displayField: 'itemName',*/
		        allowBlank: false,
		        maxLength : 30,
		        /*queryMode: 'local'*/
		        listeners: {
		        	change: function(textField, newValue, oldValue, eOpts ) {
		        		var billItemForm = textField.up('form');
		        		var itemDesc = billItemForm.down('textfield[name=itemDesc]');
		           	 	var itemCode = billItemForm.down('textfield[name=itemCode]');
		           	 	itemDesc.setValue(newValue);
		           	 	itemCode.setValue(newValue);
		        	}
		        }
		    },
		    {
		        fieldLabel: 'Item Description <span style="color:red;">*</span>',
		        name: 'itemDesc',
		        allowBlank: false,
		        maxLength : 30
		        //labelWidth : 120,
		        //disabled: true
		    },
		    {
		        fieldLabel: 'Item Code<span style="color:red;">*</span>',
		        name: 'itemCode',
		        allowBlank: true,
		        maxLength : 30
		        //disabled: true
		    },
		     {
		     	xtype: 'numberfield',
		        fieldLabel: 'Item Quantity <span style="color:red;">*</span>',
		        name: 'billItemQuantity',
		        allowBlank: false,
		        value: 1
		    },
		    /*{
		     	xtype: 'numberfield',
		        fieldLabel: 'Remaining Items',
		        name: 'billItemQuantityAvailable',
		        allowBlank: true,
		        value: 0
		       // disabled: true
		    },*/
		    {
		    	xtype: 'numberfield',
		        fieldLabel: 'Unit Price<span style="color:red;">*</span>',
		        name: 'unitPrice',
		        allowBlank: false,
		        //labelWidth : 120,
		        //disabled: true
		    },
		    {
		    	xtype: 'numberfield',
		        fieldLabel: 'Discount <span style="color:red;">*</span>',
		        name: 'billItemDiscount',
		        allowBlank: false,
		        value: 0
		       // labelWidth : 120
		    }
		    ],
    	});
    	this.callParent();
    },
    
    // Reset and Submit buttons
    buttons: [{
        text: 'Reset',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }, {
        text: 'Add',
        formBind: true, //only enabled once the form is valid
        disabled: true,
        itemId: 'AddBillItemButton'
    }]
})
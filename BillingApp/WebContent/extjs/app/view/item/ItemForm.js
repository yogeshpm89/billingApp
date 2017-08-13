Ext.define('BillingApp.view.item.ItemForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.itemForm',
	initComponent: function(){
    	// create the Data Store
        var store = Ext.create('BillingApp.store.category.CategoryStore');
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
		        fieldLabel: 'Category Name <span style="color:red;">*</span>',
		        store: store, //'category.CategoryStore',
		        name: 'categoryId',
		        valueField: 'categoryId',
		        displayField: 'categoryName',
		        allowBlank: false,
		        labelWidth : 120,
		        queryMode: 'remote'
		    },
		    {
		        fieldLabel: 'Item Name <span style="color:red;">*</span>',
		        name: 'itemName',
		        allowBlank: false,
		        labelWidth : 120
		    },
		    {
		        fieldLabel: 'Item Description <span style="color:red;">*</span>',
		        name: 'itemDesc',
		        allowBlank: false,
		        labelWidth : 120
		    },
		    {
		        fieldLabel: 'Item Code <span style="color:red;">*</span>',
		        name: 'itemCode',
		        labelWidth : 120,
		        allowBlank: true
		    },
		     {
		     	xtype: 'numberfield',
		        fieldLabel: 'Item Quantity <span style="color:red;">*</span>',
		        name: 'itemQuantity',
		        allowBlank: true,
		        labelWidth : 120
		    },
		    {
		    	xtype: 'numberfield',
		        fieldLabel: 'Unit Price <span style="color:red;">*</span>',
		        name: 'unitPrice',
		        allowBlank: false,
		        labelWidth : 120
		    }
		    ],
    	});
    	this.callParent();
    },
    dockedItems: [
	              {
	            	  xtype: 'label',
	            	  dock: 'top',
	            	  padding : '0px 0px 5px 5px',
	            	  html: '<i style="color:red">Note: 1. All fields marked with <span style="color:red;">*</span> are madatory.</i>'
	              }
	 ],
    // Reset and Submit buttons
    buttons: [{
        text: 'Reset',
        itemId: 'ResetItemButton',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }, {
        text: 'Add',
        formBind: true, //only enabled once the form is valid
        disabled: true,
        itemId: 'AddItemButton'
    }]
	
})
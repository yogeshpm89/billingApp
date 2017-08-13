Ext.define('BillingApp.view.category.CategoryForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.categoryForm',
	// Fields will be arranged vertically, stretched to full width
    layout: 'hbox',
    defaults: {
        anchor: '100%',
        padding: '0px 0px 5px 5px'
    },
    // The form will submit an AJAX request to this URL when submitted
    url: '/BillingApp/server/category/add',
    // The fields
    defaultType: 'textfield',
    items: [{
        fieldLabel: 'Category Name <span style="color:red;">*</span>',
        name: 'categoryName',
        allowBlank: false,
        labelWidth : 120
    },{
        fieldLabel: 'Category Description <span style="color:red;">*</span>',
        name: 'categoryDesc',
        allowBlank: false,
        labelWidth : 150
    }],
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
        itemId: 'resetCategoryButton',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }, {
        text: 'Add',
        itemId: 'addCategoryButton',
        formBind: true, //only enabled once the form is valid
        disabled: true
        }
    ]
	
})
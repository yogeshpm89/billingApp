Ext.define('BillingApp.view.admin.AdminForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.adminForm',
	// Fields will be arranged vertically, stretched to full width
    layout: 'vbox',
    height: 400,
    defaults: {
        anchor: '100%',
        padding: '5px 0px 5px 5px'
    },
    // The fields
    defaultType: 'textfield',
    items: [{
        fieldLabel: 'Shop Name',
        name: 'shopName',
        allowBlank: false,
    },{
    	xtype: 'textareafield',
        fieldLabel: 'Shop Address',
        name: 'shopAddress',
        allowBlank: false,
        width: 400,
        height: 80
    },{
        fieldLabel: 'VAT',
        name: 'vat',
        allowBlank: false
    }],

    // Reset and Submit buttons
    buttons: [{
        text: 'Reset',
        handler: function(button) {
        	var adminForm = button.up('adminForm');
	    	var shopName = adminForm.down('textfield[name=shopName]');
	    	var shopAddress = adminForm.down('textfield[name=shopAddress]');
	    	var vat = adminForm.down('textfield[name=vat]');
	    	shopName.setValue(Helper.settingsMap.get('SHOP_NAME'));
	    	shopAddress.setValue(Helper.settingsMap.get('SHOP_ADDRESS'));
	    	vat.setValue(Helper.settingsMap.get('VAT'));
        }
    }, {
        text: 'Save',
        itemId: 'saveAdminButton',
        formBind: true, //only enabled once the form is valid
        disabled: true
        }
    ]
	
})
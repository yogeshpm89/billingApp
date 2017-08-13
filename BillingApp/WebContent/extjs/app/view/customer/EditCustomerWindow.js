Ext.define('BillingApp.view.customer.EditCustomerWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.editCustomerWindow',
	itemId: 'editCustomerWindowItemId',
    title: 'Edit Customer',
    alwaysOnTop: true,
	modal: true,
    layout: 'fit',
    items: [
    {
        xtype: 'customerForm'
    }
    ]
});
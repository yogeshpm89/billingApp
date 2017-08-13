Ext.define('BillingApp.view.customer.CustomerGridWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.customerGridWindow',
	itemId: 'customerGridWindowItemId',
	alwaysOnTop : true,
    title: 'Items',
    height: window.innerHeight/2,
	width: window.innerWidth-320,
    layout: 'fit',
    items: [
    {
        xtype: 'customerGrid',
        itemId: 'searchCustomerGrid'
    }
    ]
});
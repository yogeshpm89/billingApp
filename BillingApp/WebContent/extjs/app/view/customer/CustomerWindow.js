Ext.define('BillingApp.view.customer.CustomerWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.customerWindow',
	itemId: 'customerWindowItemId',
    title: 'Customers',
    height: window.innerHeight,
	width: window.innerWidth-120,
    layout: 'fit',
    items: [
    {
        xtype: 'customerPanel',
        height: window.innerHeight,
		width: window.innerWidth-120,
    }
    ]
});
Ext.define('BillingApp.view.item.ItemWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.itemWindow',
    title: 'Items',
    height: window.innerHeight,
	width: window.innerWidth-120,
    layout: 'fit',
    items: [
    {
        xtype: 'itemPanel',
        height: window.innerHeight,
		width: window.innerWidth-120,
    }
    ]
});
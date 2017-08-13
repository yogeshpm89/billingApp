Ext.define('BillingApp.view.bill.CreateBillWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.createBillWindow',
	itemId: 'createBillWindowItemId',
    title: 'Create Bill',
    height: window.innerHeight,
	width: window.innerWidth-120,
    layout: 'fit',
    draggable : false,
    items: [{
        xtype: 'createBillPanel',
        height: window.innerHeight,
		width: window.innerWidth-120,
    }]
});
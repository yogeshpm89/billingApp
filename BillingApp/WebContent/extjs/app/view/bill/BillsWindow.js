Ext.define('BillingApp.view.bill.BillsWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.billsWindow',
    title: 'Bills',
    height: window.innerHeight,
	width: window.innerWidth-120,
    layout: 'fit',
    items: [{
        xtype: 'billGrid',
        height: window.innerHeight,
		width: window.innerWidth-120,
    }],
    tools: [{
        xtype: 'button',
        text: 'Re-arrange Bill Numbers',
        itemId: 'reArrangeBillNumbers',
        height: 28
    }],
});
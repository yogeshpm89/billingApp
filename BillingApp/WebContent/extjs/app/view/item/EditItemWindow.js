Ext.define('BillingApp.view.item.EditItemWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.editItemWindow',
	alwaysOnTop: true,
	modal: true,
    title: 'Edit Item',
    layout: 'fit',
    items: [
    {
        xtype: 'itemForm',
        layout: {
	    	type: 'table',
	    	columns: 2
	    }
    }
    ]
});
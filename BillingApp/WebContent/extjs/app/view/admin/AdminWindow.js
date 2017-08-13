Ext.define('BillingApp.view.admin.AdminWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.adminWindow',
    title: 'Admin',
    height: window.innerHeight,
	width: window.innerWidth-120,
    layout: 'fit',
    items: [{
        xtype: 'adminForm',
        height: window.innerHeight,
		width: window.innerWidth-120,
    }]
});
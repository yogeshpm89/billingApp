Ext.define('BillingApp.view.category.CategoryWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.categoryWindow',
    title: 'Categories',
    height: window.innerHeight,
	width: window.innerWidth-120,
    layout: 'fit',
    items: [{
        xtype: 'categoryPanel',
        height: window.innerHeight,
		width: window.innerWidth-120,
    }]
});
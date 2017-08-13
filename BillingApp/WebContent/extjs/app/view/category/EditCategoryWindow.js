Ext.define('BillingApp.view.category.EditCategoryWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.editCategoryWindow',
	alwaysOnTop: true,
	modal: true,
    title: 'Edit Category',
    layout: 'fit',
    items: [
    {
        xtype: 'categoryForm',
    }
    ]
});
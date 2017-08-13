Ext.define('BillingApp.view.mainpanel.MainPanelWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.mainPanelWindow',
    title: 'Hello',
    height: '100%',
    width: '100%',
    layout: 'fit',
    items: {
        xtype: 'label',
        text: 'main panel window'
    }
});
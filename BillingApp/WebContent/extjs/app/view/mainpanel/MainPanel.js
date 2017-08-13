Ext.define('BillingApp.view.mainpanel.MainPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.mainPanel',
	height: window.innerHeight,
	width: window.innerWidth-120,
	html: '<div style="text-align:center; width: 100%;"><h1>Boys Zone</h1><span>Version: 1.1.14</span><br><span>Packge Date: 7 July 2016</span></div>',
	bodyStyle: 'background:#f1f1f1; padding:10px;',
	items: [
		{
			xtype: 'mainPanelWindow',
			height: window.innerHeight,
			width: window.innerWidth-120
		}
	]
});
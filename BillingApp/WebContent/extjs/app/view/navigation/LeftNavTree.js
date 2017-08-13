Ext.define('BillingApp.view.navigation.LeftNavTree', {
	extend: 'Ext.tree.Panel',
	alias: 'widget.leftNavTree',
	width: 120,
	height: '100%',
	rootVisible: false,
	useArrows : true,
	cls:'letNavigationTreeCls',
	initComponent: function() {
      	var store = Ext.create('BillingApp.store.navigation.LeftNavTreeStore');
        this.store = store;
		
        this.callParent();
    }
	
})
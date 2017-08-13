Ext.define('BillingApp.view.navigation.BasicTrees', {
    extend: 'Ext.Container',
    xtype: 'basic-trees',
    width: 640,

    layout: {
        type: 'table',
        columns: 2,
        tdAttrs: { style: 'padding: 10px;' }
    },

    defaults: {
        xtype: 'treepanel',
        width: 300,
        height: 200,
        rootVisible: false,
        // Sharing the store synchronizes the views:
    },
    
    initComponent: function() {
    	 var store = Ext.create('BillingApp.store.navigation.Files');
        this.items = [
            {
                title: 'Tree',
		        store: store
            },
            {
                title: 'Tree with No Lines',
                lines: false,
                store: store
            },
            {
                title: 'Tree with Arrows',
                useArrows: true,
                colspan: 2,
                store: store
            }
        ];

        this.callParent();
    }
});
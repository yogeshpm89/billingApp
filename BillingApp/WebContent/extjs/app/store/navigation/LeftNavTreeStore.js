Ext.define('BillingApp.store.navigation.LeftNavTreeStore', {
    extend: 'Ext.data.TreeStore',

    root: {
        //text: 'Menu',
        expanded: true,
        children: [
            {text: 'Create New Bill', id:'newBillButton', iconCls: 'no-icon',defaultValue: 'treenode-no-icon'},
            {text: 'Categories', id:'categoryButton', iconCls: 'no-icon'},
            {text: 'Items', id:'itemButton', iconCls: 'no-icon'},
            {text: 'Customers', id:'customerButton', iconCls: 'no-icon'},
            {text: 'Bills', id:'billsButton', iconCls: 'no-icon'},
            {text: 'Admin', id:'adminButton', iconCls: 'no-icon'},
        ]
    }
});
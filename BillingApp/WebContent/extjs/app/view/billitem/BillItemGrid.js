Ext.define('BillingApp.view.billitem.BillItemGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.billItemGrid',
	width: '100%',
	height: 295,
    initComponent: function(){
    	// create the Data Store
        var store = Ext.create('BillingApp.store.billitem.BillItemStore');
    	Ext.apply(this, {
    		border : true,
    		columnLines : true,
    		autoScroll: true,
            store: store,
            columns: [
            	{xtype: 'rownumberer'},
            	{ text: 'Category',  dataIndex: 'categoryName',  flex: 1},
		        { text: 'Item Name',  dataIndex: 'itemName',  flex: 1},
		        { text: 'Item Quantity', dataIndex: 'itemQuantity', flex: 1},
		        { text: 'Total Amount (Rs)', dataIndex: 'totalAmount', flex: 1},
		        { text: 'Item Discount (%)', dataIndex: 'itemDiscount', flex: 1},
		        { text: 'Final Amount (Rs)', dataIndex: 'finalAmount', flex: 1, summaryType: 'sum'},
		        {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/delete16.png',  // Use a URL in the icon config
		                tooltip: 'delete',
		                itemId: 'deleteCustomerImage',
		                handler: function(grid, rowIndex, colIndex) {
		                	var rec = grid.getStore().getAt(rowIndex);
		                	grid.getStore().remove(rec);
		                	BillingControllerHelper.updateBillTotal();
		                }
		            }]
		        }
		    ],
            /*dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: store,
		        dock: 'bottom',
		        displayInfo: true
		    }],*/
    	});
    	this.callParent();
    }
})
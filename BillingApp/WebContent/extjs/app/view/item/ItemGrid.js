Ext.define('BillingApp.view.item.ItemGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.itemGrid',
	
    
      initComponent: function(){
    	// create the Data Store
        var store = Ext.create('BillingApp.store.item.ItemStore');
        Ext.apply(this, {
        	border : true,
	        store: store, // 'category.CategoryStore',
			width: '100%',
			height: 400,
			columns: [
	 		        {
						text : 'Category Name',
						dataIndex : 'categoryId',
						flex : 1,
						renderer: function(value, metaData, record, rowIndex, colIndex, store, view, html) {
							var category = record.get('categoryVO');
							if (!Ext.isEmpty(category)) {
								value = category.categoryName;
							}	
							return value;
						}
					},
		        { text: 'Item Name',  dataIndex: 'itemName',  flex: 1},
		        { text: 'Item Description', dataIndex: 'itemDesc', flex: 2},
		        { text: 'Item Code',  dataIndex: 'itemCode',  flex: 1},
		        { text: 'Item Quantity',  dataIndex: 'itemQuantity',  flex: 1},
		        { text: 'Unit Price',  dataIndex: 'unitPrice',  flex: 1},
		        {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/edit16.png',  // Use a URL in the icon config
		                tooltip: 'edit',
		                itemId: 'editItemImage',
		                handler: function(grid, rowIndex, colIndex) {
		                	var editItemWindow = Ext.create('BillingApp.view.item.EditItemWindow');
		                	var itemForm = editItemWindow.down('itemForm');
		                	var rec = grid.getStore().getAt(rowIndex);
		                	itemForm.loadRecord(rec);
		                	
		                	var AddItemButton = itemForm.down('button[itemId=AddItemButton]');
		                	AddItemButton.setText('Edit');
		                	
		                	var ResetItemButton =  itemForm.down('button[itemId=ResetItemButton]');
		                	ResetItemButton.hide();
		                	
		                	editItemWindow.show();
		                }
		            }]
		        },
		        {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/delete16.png',  // Use a URL in the icon config
		                tooltip: 'delete',
		                handler: function(grid, rowIndex, colIndex) {
		                    var rec = grid.getStore().getAt(rowIndex);
		                    var itemId = rec.get('itemId');
		                    BillingControllerHelper.deleteItem(itemId);
		                }
		            }]
		        }
		    ],
			dockedItems: [{
        		xtype: 'container',
        		layout: 'hbox',
        		items: [{
        			xtype: 'button',
        			itemId: 'excelExport',
        			text: 'Export',
        			grid: 'items'
        		}]
        	},{
		        xtype: 'pagingtoolbar',
		        store: store, // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }]
        });
        this.callParent();
      }
})
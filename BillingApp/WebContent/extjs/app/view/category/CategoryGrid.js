Ext.define('BillingApp.view.category.CategoryGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.categoryGrid',
	width: '100%',
	height: 400,
	
    
    initComponent: function(){
    	// create the Data Store
        var store = Ext.create('BillingApp.store.category.CategoryStore');
    	Ext.apply(this, {
    		border : true,
            store: store,
            columns: [
		        { text: 'Category Name',  dataIndex: 'categoryName',  flex: 1},
		        { text: 'Category Description', dataIndex: 'categoryDesc', flex: 2},
		        {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/edit16.png',  // Use a URL in the icon config
		                tooltip: 'edit',
		                itemId: 'editItemImage',
		                handler: function(grid, rowIndex, colIndex) {
		                	var editCategoryWindow = Ext.create('BillingApp.view.category.EditCategoryWindow');
		                	var categoryForm = editCategoryWindow.down('categoryForm');
		                	var rec = grid.getStore().getAt(rowIndex);
		                	categoryForm.loadRecord(rec);
		                	
		                	var addCategoryButton = categoryForm.down('button[itemId=addCategoryButton]');
		                	addCategoryButton.setText('Edit');
		                	
		                	var resetCategoryButton =  categoryForm.down('button[itemId=resetCategoryButton]');
		                	resetCategoryButton.hide();
		                	editCategoryWindow.show();
		                }
		            }]
		        },
		        {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/delete16.png',  // Use a URL in the icon config
		                tooltip: 'Edit',
		                handler: function(grid, rowIndex, colIndex) {
		                	Ext.getBody().mask('Loading....');
		                    var rec = grid.getStore().getAt(rowIndex);
		                    var categoryId = rec.get('categoryId');
		                    BillingControllerHelper.deleteCategory(categoryId);
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
            			grid: 'categories'
            		}]
            	}, {
		        xtype: 'pagingtoolbar',
		        store: store,
		        dock: 'bottom',
		        displayInfo: true
		    }],
    	});
    	this.callParent();
    }
})
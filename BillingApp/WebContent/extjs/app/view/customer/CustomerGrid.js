Ext.define('BillingApp.view.customer.CustomerGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.customerGrid',
	width: '100%',
	height: 400,
	selModel: 'cellmodel',
    initComponent: function(){
    	// create the Data Store
        var store = Ext.create('BillingApp.store.customer.CustomerStore');
    	Ext.apply(this, {
    		border : true,
            store: store,
            columns: [
		        { text: 'First Name',  dataIndex: 'firstName',  flex: 1},
		        { text: 'Last Name',  dataIndex: 'lastName',  flex: 1},
		        { text: 'Birth Date', dataIndex: 'birthDate', flex: 1, 
		        	formatter: 'date("d/m/Y")'
		        },
		        { text: 'Email',  dataIndex: 'email',  flex: 1},
		        { text: 'Address',  dataIndex: 'address',  flex: 1},
		        { text: 'Phone',  dataIndex: 'phone',  flex: 1},
		        { 
		        	text: 'Shopping Amount (Rs)',  
		        	dataIndex: 'shoppingAmount',  
		        	flex: 1,
					renderer: function(value, metaData, record, row, col, store, gridView) {
						/*var customerId = record.get("customerId");
						var firstName = record.get("firstName");
     					var lastName = record.get("lastName");
     					var address = record.get("address");*/
			           	return '<a href="#" onclick="BillingControllerHelper.showBills(' + row + ');">' + value + '</a>';
		            }
		        }, {
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/edit16.png',  // Use a URL in the icon config
		                tooltip: 'edit',
		                itemId: 'editItemImage',
		                handler: function(grid, rowIndex, colIndex) {
		                	var editCustomerWindow = Ext.create('BillingApp.view.customer.EditCustomerWindow');
		                	var customerForm = editCustomerWindow.down('customerForm');
		                	var rec = grid.getStore().getAt(rowIndex);
		                	customerForm.loadRecord(rec);
		                	
		                	var AddCustomerButton = customerForm.down('button[itemId=AddCustomerButton]');
		                	AddCustomerButton.setText('Edit');
		                	
		                	var ResetCustomerButton =  customerForm.down('button[itemId=ResetCustomerButton]');
		                	ResetCustomerButton.hide();
		                	
		                	editCustomerWindow.show();
		                }
		            }]
		        },{
		            xtype:'actioncolumn',
		            width: 25,
		            items: [{
		                icon: 'resources/images/delete16.png',  // Use a URL in the icon config
		                tooltip: 'delete',
		                itemId: 'deleteCustomerImage',
		                handler: function(grid, rowIndex, colIndex) {
		                	var rec = grid.getStore().getAt(rowIndex);
		                    var customerId = rec.get('customerId');
		                	BillingControllerHelper.deleteCustomer(customerId);
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
        			grid: 'customers'
        		}]
        	}, {
		        xtype: 'pagingtoolbar',
		        store: store, // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }]
    	});
    	this.callParent();
    }
})